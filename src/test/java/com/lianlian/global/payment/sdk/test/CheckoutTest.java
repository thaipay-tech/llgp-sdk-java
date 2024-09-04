package com.lianlian.global.payment.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.LLPayClient;
import com.lianlian.global.payment.sdk.dto.Address;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.CheckoutReq;
import com.lianlian.global.payment.sdk.response.CheckoutResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Profiles;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author thaipay
 * @since 1.0
 */
public class CheckoutTest extends BaseTest {

    @Test
    public void checkout() {

        LLPayClient llPayClient = new DefaultLLPayClient(Profiles.UAT,
                merchantPrivateKey, lianPayPublicKey);

        CheckoutReq request = CheckoutReq.builder().version("v1")
                .service(Service.CHECKOUT)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0824002")
                .order_amount("1.89")
                .order_currency(Currency.THB.name())
                .order_desc("checkout test")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz001").full_name("macz")
                        .address(Address.builder().country("CN").state("Zhejiang").city("hangzhou")
                                .district("binjiang").postal_code("310051").street_name("yueda")
                                .house_number("79").build()).build())
                .products(Arrays.asList(
                        Product.builder().name("cap").description("big size").unit_price("12.3")
                                .quantity("12").category("red").show_url("http://111.taobao.com/").build(),
                        Product.builder().name("hat").description("small size").unit_price("11.3")
                                .quantity("10").category("gray").show_url("http://222.taobao.com/").build()))
                .build();

        LLPayResult<CheckoutResp> response = llPayClient.execute(request);
        System.out.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));
    }
}
