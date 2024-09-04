package com.lianlian.global.payment.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.request.RefundReq;
import com.lianlian.global.payment.sdk.response.RefundResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class RefundTest extends BaseTest {

    @Test
    public void refund() {

        RefundReq refundReq = RefundReq.builder()
                .version("v1")
                .service(Service.REFUND)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0828001")
                .merchant_refund_id("llgp-sdk-java-test-0828002")
                .refund_amount("1.89")
                .refund_currency(Currency.THB.name())
                .refund_reason("order info test")
                .account_no("0830443596")
                .account_name("MaczLian")
                .bank_code("014")
                .notify_url("https://www.lianlianpay.co.th/callback")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<RefundResp> result = payClient.execute(refundReq);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }
}
