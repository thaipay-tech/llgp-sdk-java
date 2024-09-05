package com.lianlian.global.payment.sdk.support;

import java.io.Serializable;

/**
 * @author thaipay
 * @since 1.0
 */
public class LLPayResult<R> implements Serializable {

    private static final int SUCCESS_CODE = 200000;

    private int code;

    private String message;

    private String trace_id;

    private R data;


    public LLPayResult() {

        this.code = SUCCESS_CODE;
        this.message = "Success";
    }

    public LLPayResult(int code, String message) {

        this.code = code;
        this.message = message;
    }

    public boolean success() {

        return SUCCESS_CODE == this.code;
    }

    public boolean fail() {

        return !success();
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getTrace_id() {

        return trace_id;
    }

    public void setTrace_id(String trace_id) {

        this.trace_id = trace_id;
    }

    public R getData() {

        return data;
    }

    public void setData(R data) {

        this.data = data;
    }
}
