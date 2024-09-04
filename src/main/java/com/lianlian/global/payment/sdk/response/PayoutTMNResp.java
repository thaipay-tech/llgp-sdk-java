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
public class PayoutTMNResp implements LLPayResponse {

    private String merchant_id;
    private String merchant_order_id;
    private String order_id;
    private String confirm_code;
}
