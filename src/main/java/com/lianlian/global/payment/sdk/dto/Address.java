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
public class Address {

    private String street_name;
    private String house_number;
    private String district;
    private String city;
    private String state;
    private String country;
    private String postal_code;
}
