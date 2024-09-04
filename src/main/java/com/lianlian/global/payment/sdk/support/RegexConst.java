package com.lianlian.global.payment.sdk.support;

/**
 * @author thaipay
 * @since 1.0
 */
public final class RegexConst {

    public static final String MERCHANT_ID = "^14\\d{16}";

    /**
     * amount greater than 1
     */
    public static final String AMOUNT_GT_ONE = "^[1-9](\\d{1,8})?(\\.(\\d{1,2}))?$";

    public static final String BANK_ACCOUNT_NO = "\\d{10,18}";

    public static final String BANK_CODE = "\\d{3}";

    public static final String MOBILE_NO = "\\d{10,12}$";

    public static final String PAYMENT_TYPE = "^[A-Z_]+$";
}
