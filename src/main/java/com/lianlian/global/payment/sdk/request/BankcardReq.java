package com.lianlian.global.payment.sdk.request;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.dto.Card;
import com.lianlian.global.payment.sdk.dto.Customer;
import com.lianlian.global.payment.sdk.dto.Product;
import com.lianlian.global.payment.sdk.response.BankcardResp;
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
public class BankcardReq implements LLPayRequest<BankcardResp> {

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
    private Card card;
    private String notify_url;
    private String redirect_url;


    @Override
    public Class acquireRespCls() {

        return BankcardResp.class;
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

        if (!Arrays.asList("CARD", "CREDIT_CARD", "DEBIT_CARD").contains(payment_method)) {
            return "parameter [payment_method] invalid";
        }
        if (card == null) {
            return "parameter [card] required";
        }
        String cardCheck = card.validate();
        if (!StringUtils.isEmpty(cardCheck)) {
            return cardCheck;
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
        if (StringUtils.isEmpty(redirect_url) ||
                redirect_url.length() > 256) {
            return "parameter [redirect_url] invalid";
        }
        return null;
    }

}
