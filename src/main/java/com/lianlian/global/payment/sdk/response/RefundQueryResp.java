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
public class RefundQueryResp implements LLPayResponse {

    private String merchant_id;
    private String merchant_refund_id;
    private String merchant_order_id;
    private String order_id;
    private String refund_order_id;
    private String refund_status;
    private String refund_amount;
    private String refund_currency;
    private String refund_reason;
    private String notify_url;
    private String create_time;
    private String complete_time;
}
