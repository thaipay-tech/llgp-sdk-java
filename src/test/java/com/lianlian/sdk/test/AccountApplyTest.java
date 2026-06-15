package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.request.AccountApplyReq;
import com.lianlian.global.payment.sdk.response.AccountApplyResp;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * Account Apply Test - Checkout mode
 *
 * @author thaipay
 * @since 1.0
 */
public class AccountApplyTest extends BaseTest {

    /**
     * Checkout account binding - specify payment method
     */
    @Test
    public void applyWithPaymentMethod() {

        AccountApplyReq request = AccountApplyReq.builder().version("v1")
                .service(Service.ACCOUNT_APPLY)
                .merchant_id(merchantId)
                .request_id("APPLY-" + System.currentTimeMillis())
                .merchant_order_id("llgp-sdk-java-test-apply-001")
                .payment_method("SCB_DIRECT_DEBIT")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .cancel_url("https://www.yezhou.cc/callback/cancel.php")
                .customer(Customer.builder().merchant_user_id("macz001").full_name("Somchai Test").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountApplyResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    /**
     * Checkout account binding - all available methods (payment_method not specified)
     */
    @Test
    public void applyAllMethods() {

        AccountApplyReq request = AccountApplyReq.builder().version("v1")
                .service(Service.ACCOUNT_APPLY)
                .merchant_id(merchantId)
                .request_id("APPLY-ALL-" + System.currentTimeMillis())
                .merchant_order_id("llgp-sdk-java-test-apply-all-001")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .cancel_url("https://www.yezhou.cc/callback/cancel.php")
                .customer(Customer.builder().merchant_user_id("macz002").full_name("Somrak Test").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountApplyResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }
}
