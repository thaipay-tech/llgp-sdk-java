package com.lianlian.global.payment.sdk.support;

/**
 * @author thaipay
 * @since 1.0
 */
public enum Currency {

    /**
     * THB in Thailand
     */
    THB,

    USD,

    VND,

    AED,

    GBP,

    EUR,

    JPY,

    AUD,

    NZD,

    HKD,

    SGD,

    CHF,

    BDT,

    BND,

    CAD,

    CNY,

    DKK,

    IDR,

    INR,

    KRW,

    KWD,

    LKR,

    MOP,

    MYR,

    NOK,

    NPR,

    OMR,

    PHP,

    PKR,

    QAR,

    RUB,

    SAR,

    SEK,

    TWD,

    ZAR,

    BHD;

    public static boolean resolve(String name) {

        for (Currency curr : values()) {
            if (curr.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
