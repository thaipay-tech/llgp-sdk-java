package com.lianlian.global.payment.sdk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;

    private String description;

    private String unit_price;

    private String quantity;

    private String category;

    private String show_url;
}
