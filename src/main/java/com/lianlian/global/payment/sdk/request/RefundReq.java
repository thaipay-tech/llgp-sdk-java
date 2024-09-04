package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.RefundResp;
import com.lianlian.global.payment.sdk.support.Currency;
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
public class RefundReq implements LLPayRequest<RefundResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_refund_id;
    private String merchant_order_id;
    private String refund_amount;
    private String refund_currency;
    private String refund_reason;
    private String notify_url;

    /**
     * for payment types that channel not supported refund,
     * these parameters required. Such as: QR_PROMPT, MOBILE_BANKING, COUNTER_PAY
     */
    private String bank_code;
    private String account_no;
    private String account_name;

    @Override
    public Class<RefundResp> acquireRespCls() {

        return RefundResp.class;
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
        if (StringUtils.isEmpty(merchant_id) || !merchant_id.matches(RegexConst.MERCHANT_ID)) {
            return "parameter [merchant_id] invalid";
        }
        if (StringUtils.isEmpty(merchant_order_id) || merchant_order_id.length() > 64) {
            return "parameter [merchant_order_id] invalid";
        }
        if (StringUtils.isEmpty(merchant_refund_id) || merchant_refund_id.length() > 64) {
            return "parameter [merchant_refund_id] invalid";
        }
        if (StringUtils.isEmpty(refund_amount) || !refund_amount.matches(RegexConst.AMOUNT_GT_ONE)) {
            return "parameter [refund_amount] invalid";
        }
        if (!Currency.resolve(refund_currency)) {
            return "parameter [refund_currency] invalid";
        }
        if (StringUtils.isEmpty(refund_reason) || refund_reason.length() > 256) {
            return "parameter [merchant_refund_id] invalid";
        }
        if (!StringUtils.isEmpty(notify_url) && notify_url.length() > 256) {
            return "parameter [notify_url] invalid";
        }

        if (!StringUtils.isEmpty(bank_code) && !bank_code.matches(RegexConst.BANK_CODE)) {
            return "parameter [bank_code] invalid";
        }
        if (!StringUtils.isEmpty(account_name) && account_name.length() > 60) {
            return "parameter [notify_url] invalid";
        }
        if (!StringUtils.isEmpty(account_no) && !account_no.matches(RegexConst.BANK_ACCOUNT_NO)) {
            return "parameter [notify_url] invalid";
        }
        return null;
    }
}
