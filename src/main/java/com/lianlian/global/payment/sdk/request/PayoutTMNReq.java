package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.PayoutTMNResp;
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
public class PayoutTMNReq implements LLPayRequest<PayoutTMNResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_order_id;
    private String order_amount;
    private String order_currency;
    /**
     * TrueMoney account number
     */
    private String payee_account;
    private String order_info;
    private String notify_url;
    private String memo;

    @Override
    public Class<PayoutTMNResp> acquireRespCls() {

        return PayoutTMNResp.class;
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
        if (StringUtils.isEmpty(payee_account) ||
                !payee_account.matches(RegexConst.MOBILE_NO)) {
            return "parameter [payee_account] invalid";
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
