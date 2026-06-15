package com.lianlian.global.payment.sdk.support;

/**
 * @author thaipay
 * @since 1.0
 */
public enum Service {

    /**
     * for checkout pay
     */
    CHECKOUT("llpth.checkout.apply"),

    /**
     * for QR prompt pay
     */
    QR_PROMPT("llpth.thaiqr.pay"),

    /**
     * for alipay online
     */
    ALIPAY("llpth.alipay.pay"),

    /**
     * for alipay offline store
     */
    ALIPAY_STORE("llpth.alipay.offline.pay"),

    WECHAT_PAY("llpth.wechatpay.pay"),

    BANKCARD("llpth.bankcard.pay"),

    MOBILE_BANKING("llpth.mobile.banking.pay"),

    TRUE_MONEY("llpth.truemoney.pay"),

    SHOPEE_PAY("llpth.shopeepay.pay"),

    LINE_PAY("llpth.linepay.pay"),

    COUNTER_PAY("llpth.counter.pay"),

    PAO_TANG_PAY("llpth.paotang.pay"),

    DIRECT_DEBIT("llpth.direct-debit.pay"),

    /**
     * for account binding (Direct API mode)
     */
    ACCOUNT_BIND("llpth.account.bind"),

    /**
     * for account binding (Checkout page mode)
     */
    ACCOUNT_APPLY("llpth.account.apply"),

    /**
     * for account binding status query
     */
    ACCOUNT_BIND_QUERY("llpth.account.query"),


    /**
     * for unified refund service
     */
    REFUND("llpth.refund.apply"),

    /**
     * for payment and refund query
     */
    PAYMENT_QUERY("llpth.payment.query"),
    REFUND_QUERY("llpth.refund.query"),
    ESLIP_QUERY("llpth.qr-prompt.eslip.query"),

    /**
     * for payout
     */
    PAYOUT("llpth.disbursal.instant.apply"),
    PAYOUT_TMN("llpth.disbursal.instant.apply.truemoney"),

    PAYOUT_ACK("llpth.disbursal.instant.confirm"),
    PAYOUT_QUERY("llpth.disbursal.instant.query"),
    ACCOUNT_QUERY("llpth.disbursal.instant.query.balance");


    private final String code;

    Service(String code) {

        this.code = code;
    }

    public static boolean isQuery(Service service) {

        return service.name().endsWith("QUERY");
    }

    public String value() {

        return this.code;
    }
}
