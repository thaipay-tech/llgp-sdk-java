package com.lianlian.global.payment.sdk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Apply Response - Checkout mode
 * <p>
 * Returns link_url pointing to the checkout page.
 * The user completes account binding on the checkout page (valid for 35 minutes).
 *
 * @author thaipay
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountApplyResp implements LLPayResponse {

    private String merchant_id;
    private String link_account_id;
    private String link_status;
    private String link_url;
}
