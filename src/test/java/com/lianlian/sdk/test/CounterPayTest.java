package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.CounterPayReq;
import com.lianlian.global.payment.sdk.response.CounterPayResp;
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
public class CounterPayTest extends BaseTest {

    @Test
    public void apply() {

        CounterPayReq request = CounterPayReq.builder().version("v1")
                .service(Service.COUNTER_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-counter-001")
                .order_amount("50.00")
                .order_currency(Currency.THB.name())
                .order_desc("counter pay test")
                .payment_method("COUNTER_PAY")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz007").full_name("counter user").build())
                .products(Arrays.asList(
                        Product.builder().name("counter product").description("test product").unit_price("50.00")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<CounterPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
