package com.lianlian.global.payment.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlian.global.payment.sdk.request.LLPayRequest;
import com.lianlian.global.payment.sdk.response.LLPayResponse;
import com.lianlian.global.payment.sdk.support.*;
import com.lianlian.global.payment.sdk.utils.HttpClientUtils;
import com.lianlian.global.payment.sdk.utils.SignUtils;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thaipay
 * @since 1.0
 */
@Slf4j
public final class DefaultLLPayClient implements LLPayClient {

    private String serverUrl;
    private String merchantPrivateKey;
    private String lianPayPublicKey;

    public DefaultLLPayClient(Profiles profile, String merchantPrivateKey, String lianPayPublicKey) {

        if (profile == Profiles.PROD) {
            this.serverUrl = GlobalConst.SERVER_URL_PROD;
        } else if (profile == Profiles.UAT) {
            this.serverUrl = GlobalConst.SERVER_URL_UAT;
        }
        this.merchantPrivateKey = merchantPrivateKey;
        this.lianPayPublicKey = lianPayPublicKey;
    }

    public DefaultLLPayClient(String serverUrl, String merchantPrivateKey, String lianPayPublicKey) {

        this.serverUrl = serverUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.lianPayPublicKey = lianPayPublicKey;
    }

    @Override
    public <T extends LLPayResponse> LLPayResult<T> execute(LLPayRequest<T> request) {

        String errorMsg = request.validate();
        if (!StringUtils.isEmpty(errorMsg)) {
            return new LLPayResult(RespError.BAD_REQUEST, errorMsg);
        }
        Map<String, String> headerMap = new HashMap<String, String>(3) {{
            put(GlobalConst.SIGN_TYPE, GlobalConst.SIGN_TYPE_RSA);
            put(GlobalConst.HTTP_HEADER_CONTENT_TYPE, GlobalConst.CONTENT_TYPE_JSON);
        }};
        String originalRequestBody = JSON.toJSONString(request, new ServiceValueFilter());
        String requestBody = SignUtils.jsonArgs2String(originalRequestBody);
        String bodyWithSignature;

        // add request signature
        try {
            bodyWithSignature = SignUtils.sign(requestBody, this.merchantPrivateKey);
        } catch (Exception e) {
            log.warn("signature for request failure. caused={}", e.getMessage());
            return new LLPayResult(RespError.SIGNATURE_FAILURE, "signature for request failure");
        }
        headerMap.put(GlobalConst.SIGNATURE, bodyWithSignature);

        // call remote payment service
        Pair<Map<String, String>, String> respPair;
        LLPayResult resultResp;
        try {
            Service service = request.service();
            if (Service.isQuery(service)) {
                Map<String, String> params = JSONObject.parseObject(originalRequestBody, Map.class);
                respPair = HttpClientUtils.doGet(this.serverUrl, headerMap, params);
            } else {
                respPair = HttpClientUtils.doPost(this.serverUrl, headerMap, originalRequestBody);
            }
            resultResp = JSON.parseObject(respPair.getValue(), LLPayResult.class);
            JSONObject data = (JSONObject) resultResp.getData();
            if (data != null) {
                resultResp.setData(data.toJavaObject(request.acquireRespCls()));
            }
        } catch (LLPayException e) {
            return new LLPayResult<>(e.getErrorCode(), "call lianlian payment failure");
        }

        // verify response signature
        String respSign;
        try {

            if (!respPair.getKey().containsKey(GlobalConst.SIGNATURE)) {
                return resultResp;
            }
            respSign = respPair.getKey().get(GlobalConst.SIGNATURE);
            if (!SignUtils.verify(SignUtils.jsonArgs2String(JSON.toJSONString(resultResp.getData())),
                    respSign, lianPayPublicKey)) {
                log.warn("verify response signature failure. respBody={}, signature={}, lianlian public key={}",
                        resultResp.getData(), respSign, lianPayPublicKey);
                return new LLPayResult(RespError.SIGNATURE_FAILURE, "check response signature failure");
            }
        } catch (Exception e) {
            log.warn("verify response signature exception. caused={}", e.getMessage());
        }
        return resultResp;
    }
}
