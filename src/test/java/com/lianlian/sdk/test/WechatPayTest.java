package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.WechatPayReq;
import com.lianlian.global.payment.sdk.response.WechatPayResp;
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
public class WechatPayTest extends BaseTest {

    @Test
    public void apply4H5() {

        WechatPayReq request = WechatPayReq.builder().version("v1")
                .service(Service.WECHAT_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-wechat-001")
                .order_amount("15.00")
                .order_currency(Currency.THB.name())
                .order_desc("wechat pay H5 test")
                .payment_method("H5")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .customer(Customer.builder().merchant_user_id("macz002").full_name("wechat user").build())
                .products(Arrays.asList(
                        Product.builder().name("wechat product").description("test product").unit_price("15.00")
                                .quantity("1").category("general").show_url("http://example.com/").build()))
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<WechatPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void apply4MerchantScan() {

        WechatPayReq request = WechatPayReq.builder().version("v1")
                .service(Service.WECHAT_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-wechat-002")
                .order_amount("20.00")
                .order_currency(Currency.THB.name())
                .order_desc("wechat pay merchant scan test")
                .payment_method("MERCHANT_SCAN")
                .auth_code("131234567890123456")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .customer(Customer.builder().merchant_user_id("macz003").full_name("scan user").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<WechatPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }

    @Test
    public void apply4InApp() {

        WechatPayReq request = WechatPayReq.builder().version("v1")
                .service(Service.WECHAT_PAY)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-wechat-003")
                .order_amount("18.50")
                .order_currency(Currency.THB.name())
                .order_desc("wechat pay in-app test")
                .payment_method("INAPP_PAYMENT")
                .appid("wx1234567890abcdef")
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")
                .notify_url("https://www.yezhou.cc/callback/notify.php")
                .customer(Customer.builder().merchant_user_id("macz004").full_name("inapp user").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<WechatPayResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
