package com.lianlian.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.request.AccountBindQueryReq;
import com.lianlian.global.payment.sdk.response.AccountBindQueryResp;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayResult;
import com.lianlian.global.payment.sdk.support.Service;
import org.junit.Test;

/**
 * Account Bind Query Test
 *
 * @author thaipay
 * @since 1.0
 */
public class AccountBindQueryTest extends BaseTest {

    /**
     * Query binding status by request_id
     */
    @Test
    public void query() {

        AccountBindQueryReq request = AccountBindQueryReq.builder().version("v1")
                .service(Service.ACCOUNT_BIND_QUERY)
                .merchant_id(merchantId)
                .store_id(store_id)
                .request_id("BIND-SCB-1717808000000")
                .build();

        DefaultLLPayClient payClient = new DefaultLLPayClient(GlobalConst.SERVER_URL_UAT, merchantPrivateKey, lianPayPublicKey);
        LLPayResult<AccountBindQueryResp> result = payClient.execute(request);
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }
}
