package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.request.AlipayStoreReq;
import com.lianlian.global.payment.sdk.response.AlipayStoreResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class AlipayStoreTest extends BaseTest {

    @Test
    public void apply4MerchantScan() {

        AlipayStoreReq request = AlipayStoreReq.builder().version("v1")
                .service(Service.ALIPAY_STORE)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-alipaystore-001")
                .order_amount("25.00")
                .order_currency(Currency.THB.name())
                .order_info("alipay store test - merchant scan")
                .payment_type("MERCHANT_SCAN")
                .buyer_identity_code("28123456789012345")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AlipayStoreResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void apply4CustomerScan() {

        AlipayStoreReq request = AlipayStoreReq.builder().version("v1")
                .service(Service.ALIPAY_STORE)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-alipaystore-002")
                .order_amount("30.00")
                .order_currency(Currency.THB.name())
                .order_info("alipay store test - customer scan")
                .payment_type("CUSTOMER_SCAN")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AlipayStoreResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
