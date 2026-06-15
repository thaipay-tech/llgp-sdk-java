package com.lianlian.global.payment.sdk.request;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.response.AccountApplyResp;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Apply Request - Checkout mode
 * <p>
 * Used for Direct Debit account binding via checkout page.
 * The user will input account_no and id_number (KBank) on the checkout page.
 * No need to pass account_no or id_number in this request.
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountApplyReq implements LLPayRequest<AccountApplyResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String request_id;
    private String merchant_order_id;
    /**
     * Optional. If not specified, all available Direct Debit methods will be shown on checkout page.
     */
    private String payment_method;
    private Customer customer;
    private String notify_url;
    private String redirect_url;
    private String cancel_url;


    @Override
    public Class<AccountApplyResp> acquireRespCls() {

        return AccountApplyResp.class;
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
        if (StringUtils.isEmpty(merchant_order_id) ||
                merchant_order_id.length() > 64) {
            return "parameter [merchant_order_id] invalid";
        }
        // payment_method is optional for checkout mode
        if (!StringUtils.isEmpty(payment_method) &&
                !payment_method.matches(RegexConst.PAYMENT_TYPE)) {
            return "parameter [payment_method] invalid";
        }
        if (customer == null || StringUtils.isEmpty(customer.getMerchant_user_id()) || StringUtils.isEmpty(customer
                .getFull_name())) {
            return "parameter [customer] invalid";
        }
        if (JSON.toJSONString(customer).length() > 1024) {
            return "parameter [customer] info too long";
        }
        if (StringUtils.isEmpty(notify_url) ||
                notify_url.length() > 256) {
            return "parameter [notify_url] invalid";
        }
        return null;
    }
}
