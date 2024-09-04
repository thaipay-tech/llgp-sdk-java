package com.lianlian.global.payment.sdk.dto;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String holder_name;
    private String card_no;
    private String card_type;
    private String bank_code;
    private String brand;
    private String exp_year;
    private String exp_month;
    private String cvv2;
    private Address billing_address;

    public String validate() {

        if (StringUtils.isEmpty(holder_name) ||
                holder_name.length() > 128) {
            return "parameter [holder_name] invalid";
        }
        if (StringUtils.isEmpty(card_no) ||
                !card_no.matches(RegexConst.BANK_ACCOUNT_NO)) {
            return "parameter [card_no] invalid";
        }
        if (StringUtils.isEmpty(card_type) ||
                !Arrays.asList("C", "D").contains(card_type)) {
            return "parameter [card_type] invalid";
        }
        if (!StringUtils.isEmpty(bank_code) &&
                !bank_code.matches(RegexConst.BANK_CODE)) {
            return "parameter [bank_code] invalid";
        }
        if (!StringUtils.isEmpty(brand) && brand.length() > 32) {
            return "parameter [brand] invalid";
        }
        if (StringUtils.isEmpty(exp_month) || !exp_month.matches("^\\d{2}$")) {
            return "parameter [exp_month] invalid";
        }
        if (StringUtils.isEmpty(exp_year) || !exp_year.matches("^\\d{2}$")) {
            return "parameter [exp_year] invalid";
        }
        if (!StringUtils.isEmpty(cvv2) && !cvv2.matches("^\\d{3}$")) {
            return "parameter [cvv2] invalid";
        }
        if (billing_address != null && JSON.toJSONString(billing_address).length() > 2000) {
            return "parameter [billing_address] too long";
        }
        return "";
    }
}
