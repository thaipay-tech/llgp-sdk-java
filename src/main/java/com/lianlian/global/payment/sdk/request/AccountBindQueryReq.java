package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.AccountBindQueryResp;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Bind Query Request
 * <p>
 * Query binding status by original request_id.
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBindQueryReq implements LLPayRequest<AccountBindQueryResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String store_id;
    private String request_id;


    @Override
    public Class<AccountBindQueryResp> acquireRespCls() {

        return AccountBindQueryResp.class;
    }

    @Override
    public Service service() {

        return this.service;
    }

    @Override
    public String validate() {

        if (this.service == null) {
            return "parameter [service] blank";
        }
        if (StringUtils.isEmpty(merchant_id) ||
                !merchant_id.matches(RegexConst.MERCHANT_ID)) {
            return "parameter [merchant_id] invalid";
        }
        if (StringUtils.isEmpty(request_id) ||
                request_id.length() > 64) {
            return "parameter [request_id] invalid";
        }
        return null;
    }
}
