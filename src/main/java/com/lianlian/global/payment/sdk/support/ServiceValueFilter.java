package com.lianlian.global.payment.sdk.support;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * @author thaipay
 * @since 1.0
 */
public class ServiceValueFilter implements ValueFilter {

    @Override
    public Object process(Object o, String s, Object o1) {

        if (o1 instanceof Service) {
            return ((Service) o1).value();
        }
        return o1;
    }
}
