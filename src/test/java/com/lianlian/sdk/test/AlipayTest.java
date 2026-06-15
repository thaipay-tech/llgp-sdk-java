package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.AlipayReq;
import com.lianlian.global.payment.sdk.response.AlipayResp;
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
public class AlipayTest extends BaseTest {

    @Test
    public void apply() {

        AlipayReq request = AlipayReq.builder().version("v1")
                .service(Service.ALIPAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-alipay-001")
                .order_amount("10.50")
                .order_currency(Currency.THB.name())
                .order_desc("alipay online test")
                .payment_method("ALIPAY_CN")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz001").full_name("macz").build())
                .products(Arrays.asList(
                        Product.builder().name("product1").description("test product").unit_price("10.50")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AlipayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
