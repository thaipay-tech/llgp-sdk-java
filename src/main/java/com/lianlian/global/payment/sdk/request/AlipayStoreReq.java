package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.AlipayStoreResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlipayStoreReq implements LLPayRequest<AlipayStoreResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_order_id;
    private String order_amount;
    private String order_currency;
    private String order_info;

    private String payment_type;
    private String buyer_identity_code;
    private String notify_url;

    @Override
    public Class acquireRespCls() {

        return AlipayStoreResp.class;
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
        if (!Currency.resolve(order_currency)) {
            return "parameter [order_currency] invalid";
        }
        if (StringUtils.isEmpty(order_info) ||
                order_info.length() > 256) {
            return "parameter [order_info] invalid";
        }
        if (!Arrays.asList("MERCHANT_SCAN", "DYNAMIC_CODE").contains(payment_type)) {
            return "parameter [payment_type] invalid";
        }
        if (StringUtils.equals(payment_type, "MERCHANT_SCAN") && !buyer_identity_code.matches("^28\\d{16}$")) {
            return "parameter [buyer_identity_code] invalid";
        }

        if (!StringUtils.isEmpty(notify_url) &&
                notify_url.length() > 256) {
            return "parameter [notify_url] invalid";
        }
        return null;
    }

}
