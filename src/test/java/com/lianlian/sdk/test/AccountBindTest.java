package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.request.AccountBindReq;
import com.lianlian.global.payment.sdk.response.AccountBindResp;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * Account Bind Test - Direct API mode
 *
 * @author thaipay
 * @since 1.0
 */
public class AccountBindTest extends BaseTest {

    /**
     * SCB Direct Debit account binding
     */
    @Test
    public void bindSCB() {

        AccountBindReq request = AccountBindReq.builder().version("v1")
                .service(Service.ACCOUNT_BIND)
                .merchant_id(merchantId)
                .store_id(store_id)
                .request_id("BIND-SCB-" + System.currentTimeMillis())
                .merchant_order_id("llgp-sdk-java-test-bind-scb-001")
                .payment_method("DIRECT_DEBIT_SCB")
                .account_no("1234567890")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz001").full_name("Somchai Test").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountBindResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    /**
     * KTB Direct Debit account binding (synchronous)
     */
    @Test
    public void bindKTB() {

        AccountBindReq request = AccountBindReq.builder().version("v1")
                .service(Service.ACCOUNT_BIND)
                .merchant_id(merchantId)
                .store_id(store_id)
                .request_id("BIND-KTB-" + System.currentTimeMillis())
                .merchant_order_id("llgp-sdk-java-test-bind-ktb-001")
                .payment_method("DIRECT_DEBIT_KTB")
                .account_no("1112223334")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .customer(Customer.builder().merchant_user_id("macz002").full_name("Somrak Test").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountBindResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    /**
     * KBank Direct Debit account binding (requires id_number)
     */
    @Test
    public void bindKBank() {

        AccountBindReq request = AccountBindReq.builder().version("v1")
                .service(Service.ACCOUNT_BIND)
                .merchant_id(merchantId)
                .store_id(store_id)
                .request_id("BIND-KBANK-" + System.currentTimeMillis())
                .merchant_order_id("llgp-sdk-java-test-bind-kbank-001")
                .payment_method("DIRECT_DEBIT_KBANK")
                .account_no("1032921686")
                .id_number("3509007442759")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz003").full_name("Somporn Test").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountBindResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }
}
