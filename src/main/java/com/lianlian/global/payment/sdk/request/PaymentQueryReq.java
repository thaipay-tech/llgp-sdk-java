package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.PaymentQueryResp;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentQueryReq implements LLPayRequest<PaymentQueryResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_order_id;

    @Override
    public Class<PaymentQueryResp> acquireRespCls() {

        return PaymentQueryResp.class;
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
        if (StringUtils.isEmpty(merchant_order_id) ||
                merchant_order_id.length() > 64) {
            return "parameter [merchant_order_id] invalid";
        }
        return null;
    }
}
