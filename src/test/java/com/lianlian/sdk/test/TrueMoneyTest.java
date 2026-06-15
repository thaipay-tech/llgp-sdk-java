package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.dto.Address;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.request.QRPromptReq;
import com.lianlian.global.payment.sdk.request.TrueMoneyReq;
import com.lianlian.global.payment.sdk.response.QRPromptResp;
import com.lianlian.global.payment.sdk.response.TrueMoneyResp;
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
public class TrueMoneyTest extends BaseTest {

    @Test
    public void apply() {

        TrueMoneyReq request = TrueMoneyReq.builder().version("v1")
                .service(Service.TRUE_MONEY)
                .merchant_id(merchantId)
                .merchant_order_id("18315667431892295610")
                .order_amount("12.00")
                .order_currency(Currency.THB.name())
                .order_desc("Box Order")
                .payment_method("NORMAL_ALL_TM")
                .notify_url("https://test-mp.trendingstar.tech/fun-pos/unionPay/notify/lianlian/thaipay")
                .redirect_url("http://localhost:8080/packages/box-pages/mystery-box/result")
                .customer(Customer.builder().merchant_user_id("macz001").full_name("macz").build())
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<TrueMoneyResp> applyResult = payClient.execute(request);
        System.out.println(JSON.toJSONString(applyResult, SerializerFeature.PrettyFormat));
    }
}
