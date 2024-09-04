package com.lianlian.global.payment.sdk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountQueryResp implements LLPayResponse {

    private String amount;
    private String frozen_amount;
    private String total_amount;

    private String merchant_id;
}
