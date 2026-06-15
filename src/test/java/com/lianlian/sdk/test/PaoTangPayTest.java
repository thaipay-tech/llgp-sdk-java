package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.PaoTangPayReq;
import com.lianlian.global.payment.sdk.response.PaoTangPayResp;
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
public class PaoTangPayTest extends BaseTest {

    @Test
    public void apply() {

        PaoTangPayReq request = PaoTangPayReq.builder().version("v1")
                .service(Service.PAO_TANG_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-paotang-001")
                .order_amount("28.80")
                .order_currency(Currency.THB.name())
                .order_desc("paotang pay test")
                .payment_method("PAO_TANG")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz008").full_name("paotang user").build())
                .products(Arrays.asList(
                        Product.builder().name("paotang product").description("test product").unit_price("28.80")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<PaoTangPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
