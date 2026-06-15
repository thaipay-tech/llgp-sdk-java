package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.DirectDebitPayReq;
import com.lianlian.global.payment.sdk.response.DirectDebitPayResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author thaipay
 * @since 1.0
 */
public class DirectDebitPayTest extends BaseTest {

    @Test
    public void apply() {

        DirectDebitPayReq request = DirectDebitPayReq.builder().version("v1")
                .service(Service.DIRECT_DEBIT)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-directdebit-001")
                .order_amount("66.00")
                .order_currency(Currency.THB.name())
                .order_desc("direct debit pay test")
                .payment_method("DIRECT_DEBIT")
                .link_account_id("LINK_ACC_123456789")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz009").full_name("direct debit user").build())
                .products(Arrays.asList(
                        Product.builder().name("direct debit product").description("test product").unit_price("66.00")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<DirectDebitPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
