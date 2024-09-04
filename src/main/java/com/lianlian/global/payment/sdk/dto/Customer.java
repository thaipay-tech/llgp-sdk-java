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
public class Customer {

    private String merchant_user_id;

    private String customer_type;

    private String first_name;

    private String last_name;

    private String full_name;

    private String gender;

    private String id_type;

    private String id_no;

    private String email;

    private String phone;

    private String company;

    private Address address;
}
