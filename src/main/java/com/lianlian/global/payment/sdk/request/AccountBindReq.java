package com.lianlian.global.payment.sdk.request;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.response.AccountBindResp;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Bind Request - Direct API mode
 * <p>
 * Used for Direct Debit account binding via API.
 * Supported payment methods: SCB_DIRECT_DEBIT, DIRECT_DEBIT_KTB, DIRECT_DEBIT_KBANK
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBindReq implements LLPayRequest<AccountBindResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String store_id;
    private String request_id;
    private String merchant_order_id;
    private String payment_method;
    private String account_no;
    /**
     * Payer's ID number. Required for DIRECT_DEBIT_KBANK only (max 20 chars).
     */
    private String id_number;
    private Customer customer;
    private String notify_url;
    private String redirect_url;
    private String cancel_url;


    @Override
    public Class<AccountBindResp> acquireRespCls() {

        return AccountBindResp.class;
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
        if (StringUtils.isEmpty(payment_method) ||
                !payment_method.matches(RegexConst.PAYMENT_TYPE)) {
            return "parameter [payment_method] invalid";
        }
        if (StringUtils.isEmpty(account_no) ||
                !account_no.matches(RegexConst.BANK_ACCOUNT_NO)) {
            return "parameter [account_no] invalid";
        }
        // id_number is required for DIRECT_DEBIT_KBANK
        if ("DIRECT_DEBIT_KBANK".equals(payment_method)) {
            if (StringUtils.isEmpty(id_number) || id_number.length() > 20) {
                return "parameter [id_number] required for DIRECT_DEBIT_KBANK";
            }
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
