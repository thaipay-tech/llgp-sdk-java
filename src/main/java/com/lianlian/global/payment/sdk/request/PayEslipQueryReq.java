package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.PayEslipQueryResp;
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
public class PayEslipQueryReq implements LLPayRequest<PayEslipQueryResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String ref_no;

    @Override
    public Class<PayEslipQueryResp> acquireRespCls() {

        return PayEslipQueryResp.class;
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
        if (StringUtils.isEmpty(ref_no) ||
                !ref_no.matches("^[a-zA-Z0-9]{30,128}$")) {
            return "parameter [ref_no] invalid";
        }
        return null;
    }
}
