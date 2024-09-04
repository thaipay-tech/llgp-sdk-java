package com.lianlian.global.payment.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Address;
import com.lianlian.global.payment.sdk.dto.Card;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.BankcardReq;
import com.lianlian.global.payment.sdk.response.BankcardResp;
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
public class BankcardTest extends BaseTest {

    @Test
    public void apply() {

        BankcardReq request = BankcardReq.builder().version("v1")
                .service(Service.BANKCARD)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-java-test-0825001")
                .order_amount("1.89")
                .order_currency(Currency.THB.name())
                .order_desc("qr test")
                .payment_method("CARD")
                .card(Card.builder().holder_name("holder name").card_no("4417704007846849").bank_code("014").brand
                        ("VISA").card_type("C").cvv2("123").exp_month("12").exp_year("25")
                        .build())
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

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<BankcardResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
