package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.ShopeePayReq;
import com.lianlian.global.payment.sdk.response.ShopeePayResp;
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
public class ShopeePayTest extends BaseTest {

    @Test
    public void apply() {

        ShopeePayReq request = ShopeePayReq.builder().version("v1")
                .service(Service.SHOPEE_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-shopee-001")
                .order_amount("35.00")
                .order_currency(Currency.THB.name())
                .order_desc("shopeepay test")
                .payment_method("SHOPEEPAY_TH")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .redirect_url("https://www.yezhou.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("macz005").full_name("shopee user").build())
                .products(Arrays.asList(
                        Product.builder().name("shopee product").description("test product").unit_price("35.00")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<ShopeePayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
