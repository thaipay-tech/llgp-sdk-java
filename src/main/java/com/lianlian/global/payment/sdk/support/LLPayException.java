package com.lianlian.global.payment.sdk.support;

/**
 * @author thaipay
 * @since 1.0
 */
public class LLPayException extends RuntimeException{

    /**
     * @see RespError
     */
    private int errorCode;

    public LLPayException() {

    }

    public LLPayException(int errorCode) {

        this.errorCode = errorCode;
    }

    public LLPayException(String message) {

        super(message);
    }

    public LLPayException(String message, Throwable cause) {

        super(message, cause);
    }

    public LLPayException(Throwable cause) {

        super(cause);
    }

    public LLPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(int errorCode) {

        this.errorCode = errorCode;
    }
}
