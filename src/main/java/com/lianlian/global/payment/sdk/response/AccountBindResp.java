package com.lianlian.global.payment.sdk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Bind Response
 * <p>
 * Returns binding result including link_account_id and link_url (for SCB/KBank).
 * KTB returns final status directly without link_url.
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBindResp implements LLPayResponse {

    private String merchant_id;
    private String link_account_id;
    private String link_status;
    private String payment_method;
    private String link_url;
    private String failure_code;
    private String failure_reason;
}
