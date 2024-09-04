package com.lianlian.global.payment.sdk;

import com.lianlian.global.payment.sdk.request.LLPayRequest;
import com.lianlian.global.payment.sdk.response.LLPayResponse;
import com.lianlian.global.payment.sdk.support.LLPayResult;

/**
 * @author thaipay
 * @since 1.0
 */
public interface LLPayClient {

    <T extends LLPayResponse> LLPayResult<T> execute(LLPayRequest<T> request);
}
