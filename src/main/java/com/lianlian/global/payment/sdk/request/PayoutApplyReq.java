package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.PayoutApplyResp;
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
@AllArgsConstructor
@NoArgsConstructor
public class PayoutApplyReq implements LLPayRequest<PayoutApplyResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_order_id;
    private String order_amount;
    private String order_currency;

    private String order_info;
    private String payee_bankcard_account;
    private String payee_bankcard_account_name;
    private String payee_bank_code;
    private String notify_url;
    private String memo;

    @Override
    public Class<PayoutApplyResp> acquireRespCls() {

        return PayoutApplyResp.class;
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
        if (StringUtils.isEmpty(order_amount) ||
                !order_amount.matches(RegexConst.AMOUNT_GT_ONE)) {
            return "parameter [order_amount] invalid";
        }
        if (!StringUtils.equals(order_currency, Currency.THB.name())) {
            return "parameter [order_currency] invalid";
        }
        if (StringUtils.isEmpty(order_info) || order_info.length() > 500) {
            return "parameter [order_info] invalid";
        }
        if (StringUtils.isEmpty(payee_bankcard_account) ||
                !payee_bankcard_account.matches(RegexConst.BANK_ACCOUNT_NO)) {
            return "parameter [payee_bankcard_account] invalid";
        }
        if (StringUtils.isEmpty(payee_bankcard_account_name) ||
                payee_bankcard_account_name.length() > 60) {
            return "parameter [payee_bankcard_account_name] invalid";
        }
        if (StringUtils.isEmpty(payee_bank_code) ||
                !payee_bank_code.matches(RegexConst.BANK_CODE)) {
            return "parameter [payee_bank_code] invalid";
        }
        if (StringUtils.isEmpty(notify_url) || notify_url.length() > 256) {
            return "parameter [notify_url] invalid";
        }
        if (!StringUtils.isEmpty(memo) && memo.length() > 256) {
            return "parameter [memo] invalid";
        }
        return "";
    }
}
