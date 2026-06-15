package com.lianlian.global.payment.sdk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Bind Query Response
 * <p>
 * Returns current binding status and account information.
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBindQueryResp implements LLPayResponse {

    private String merchant_id;
    private String link_account_id;
    private String link_status;
    private String payment_method;
    private String account_no;
    private String failure_code;
    private String failure_reason;
}
