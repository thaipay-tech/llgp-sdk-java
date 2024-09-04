package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.RefundQueryResp;
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
public class RefundQueryReq implements LLPayRequest<RefundQueryResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_refund_id;

    @Override
    public Class<RefundQueryResp> acquireRespCls() {

        return RefundQueryResp.class;
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
        if (StringUtils.isEmpty(merchant_refund_id) ||
                merchant_refund_id.length() > 64) {
            return "parameter [merchant_refund_id_id] invalid";
        }
        return null;
    }
}
