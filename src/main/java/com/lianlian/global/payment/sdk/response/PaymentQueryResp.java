package com.lianlian.global.payment.sdk.response;

import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentQueryResp implements LLPayResponse {

    private String merchant_id;
    private String merchant_order_id;
    private String order_id;
    private String order_status;
    private String order_amount;
    private String order_currency;
    private String order_desc;
    private String product_code;
    private String payment_method;
    private Customer customer;
    private List<Product> products;
    private String create_time;
    private String complete_time;
}


