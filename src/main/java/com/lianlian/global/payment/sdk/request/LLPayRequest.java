package com.lianlian.global.payment.sdk.request;

import com.lianlian.global.payment.sdk.response.LLPayResponse;
import com.lianlian.global.payment.sdk.support.Service;

/**
 * @author thaipay
 * @since 1.0
 */
public interface LLPayRequest<T extends LLPayResponse> {

    /**
     * sub response class
     *
     * @return response class
     */
    Class<T> acquireRespCls();

    /**
     * validate parameters
     *
     * @return error message
     */
    default String validate() {

        return "";
    }

    /**
     * request service
     *
     * @return service value
     */
    Service service();
}
