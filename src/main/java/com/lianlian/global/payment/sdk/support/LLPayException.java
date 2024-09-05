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

    public LLPayException(Throwable cause) {

        super(cause);
    }

    public int getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(int errorCode) {

        this.errorCode = errorCode;
    }
}
