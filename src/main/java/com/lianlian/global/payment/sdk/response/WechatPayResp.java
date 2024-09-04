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
public class WechatPayResp implements LLPayResponse {

    private String order_id;
    private String order_status;
    private String order_amount;
    private String order_currency;
    private String link_url;
    private String create_time;
    private String pay_params;
}
