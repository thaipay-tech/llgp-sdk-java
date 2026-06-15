package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.request.*;
import com.lianlian.global.payment.sdk.response.*;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class QueryTest extends BaseTest {

    @Test
    public void query4Pay() {

        PaymentQueryReq queryReq = PaymentQueryReq.builder()
                .version("v1")
                .service(Service.PAYMENT_QUERY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0828001").build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PaymentQueryResp> applyResult = payClient.execute(queryReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void query4Refund() {

        RefundQueryReq queryReq = RefundQueryReq.builder()
                .version("v1")
                .service(Service.REFUND_QUERY)
                .merchant_id(merchantId)
                .merchant_refund_id("llgp-sdk-java-test-0828002").build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<RefundQueryResp> applyResult = payClient.execute(queryReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void query4Payout() {

        PayoutQueryReq queryReq = PayoutQueryReq.builder()
                .version("v1")
                .service(Service.PAYOUT_QUERY)
                .merchant_id(merchantId)
                .merchant_order_id("payout-sdk-test-0824002").build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PayoutQueryResp> applyResult = payClient.execute(queryReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void query4PayoutAccount() {

        AccountQueryReq queryReq = AccountQueryReq.builder()
                .version("v1")
                .service(Service.ACCOUNT_QUERY)
                .merchant_id(merchantId).build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountQueryResp> applyResult = payClient.execute(queryReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
    @Test
    public void queryQREslip() {

        PayEslipQueryReq req = PayEslipQueryReq.builder()
                .version("v1")
                .service(Service.ESLIP_QUERY)
                .merchant_id(merchantId)
                .ref_no("0041000600000101030040220015086102624APM027235102TH910458A6").build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PayEslipQueryResp> applyResult = payClient.execute(req);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

}
