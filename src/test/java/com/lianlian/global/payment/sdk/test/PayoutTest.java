package com.lianlian.global.payment.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.request.PayoutAckReq;
import com.lianlian.global.payment.sdk.request.PayoutApplyReq;
import com.lianlian.global.payment.sdk.request.PayoutTMNReq;
import com.lianlian.global.payment.sdk.response.PayoutAckResp;
import com.lianlian.global.payment.sdk.response.PayoutApplyResp;
import com.lianlian.global.payment.sdk.response.PayoutTMNResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class PayoutTest extends BaseTest {

    @Test
    public void apply() {

        PayoutApplyReq applyReq = PayoutApplyReq.builder()
                .version("v1")
                .service(Service.PAYOUT)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0828004")
                .order_amount("4.16")
                .order_currency(Currency.THB.name())
                .order_info("order info test")
                .payee_bankcard_account("0830443596")
                .payee_bankcard_account_name("MaczLian")
                .payee_bank_code("014")
                .notify_url("https://www.lianlianpay.co.th/callback")
                .memo("test memo")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PayoutApplyResp> applyResult = payClient.execute(applyReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void apply4TMN() {

        PayoutTMNReq applyReq = PayoutTMNReq.builder()
                .version("v1")
                .service(Service.PAYOUT_TMN)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0824012")
                .order_amount("41.14")
                .order_currency(Currency.THB.name())
                .order_info("order info test")
                .payee_account("0830443596")
                .notify_url("https://www.lianlianpay.co.th/callback")
                .memo("test memo")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PayoutTMNResp> applyResult = payClient.execute(applyReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void confirm() {

        PayoutAckReq ackReq = PayoutAckReq.builder()
                .version("v1")
                .service(Service.PAYOUT_ACK)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0828004")
                .confirm_code("284707")
                .notify_url("https://www.lianlianpay.co.th/callback").build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PayoutAckResp> applyResult = payClient.execute(ackReq);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
