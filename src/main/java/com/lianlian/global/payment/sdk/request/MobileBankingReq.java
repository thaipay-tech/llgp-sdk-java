package com.lianlian.global.payment.sdk.request;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.response.MobileBankingResp;
import com.lianlian.global.payment.sdk.support.Currency;
import com.lianlian.global.payment.sdk.support.RegexConst;
import com.lianlian.global.payment.sdk.support.Service;
import com.lianlian.global.payment.sdk.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author thaipay
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileBankingReq implements LLPayRequest<MobileBankingResp> {

    private String version;
    private Service service;
    private String merchant_id;
    private String merchant_order_id;
    private String order_amount;
    private String order_currency;
    private String order_desc;

    private String payment_method;
    private Customer customer;
    private List<Product> products;
    private String notify_url;

    private String app_callback_url;
    private String mobile_number;

    @Override
    public Class acquireRespCls() {

        return MobileBankingResp.class;
    }

    @Override
    public Service service() {

        return this.service;
    }

    @Override
    public String validate() {

        if (this.service == null) {
            return "parameter [service] blank";
        }
        if (StringUtils.isEmpty(merchant_id) ||
                !merchant_id.matches(RegexConst.MERCHANT_ID)) {
            return "parameter [merchant_id] invalid";
        }
        if (StringUtils.isEmpty(merchant_order_id) ||
                merchant_order_id.length() > 64) {
            return "parameter [merchant_order_id] invalid";
        }
        if (StringUtils.isEmpty(order_amount) ||
                !order_amount.matches(RegexConst.AMOUNT_GT_ONE)) {
            return "parameter [order_amount] invalid";
        }
        if (!Currency.resolve(order_currency)) {
            return "parameter [order_currency] invalid";
        }
        if (StringUtils.isEmpty(order_desc) ||
                order_desc.length() > 256) {
            return "parameter [order_desc] invalid";
        }

        if (!Arrays.asList("KPLUS_NOTIFICATION", "EASY_APP_BILL", "EASY_APP_CREDIT", "EASY_APP_ALL",
                "KMA_NOTIFICATION", "KMA_DEEPLINK").contains(payment_method)) {
            return "parameter [payment_method] invalid";
        }
        if (customer == null || StringUtils.isEmpty(customer.getMerchant_user_id()) || StringUtils.isEmpty(customer
                .getFull_name())) {
            return "parameter [customer] invalid";
        }
        if (JSON.toJSONString(customer).length() > 1024) {
            return "parameter [customer] info too long";
        }
        if (products != null && products.size() > 0 && JSON.toJSONString(products).length() > 4000) {
            return "parameter [products] info too long";
        }
        if (StringUtils.isEmpty(notify_url) ||
                notify_url.length() > 256) {
            return "parameter [notify_url] invalid";
        }
        if (!StringUtils.isEmpty(app_callback_url) && app_callback_url.length() > 256) {
            return "parameter [app_callback_url] invalid";
        }
        if (Arrays.asList("KMA_NOTIFICATION", "KPLUS_NOTIFICATION").contains(payment_method) && (StringUtils.isEmpty
                (mobile_number) || !mobile_number.matches(RegexConst.MOBILE_NO))) {
            return "parameter [mobile_number] invalid";
        }

        return null;
    }

}
