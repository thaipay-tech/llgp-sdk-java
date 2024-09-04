package com.lianlian.global.payment.sdk.support;

/**
 * @param <T>
 * @author lianlian
 */
public class CloneSupport<T> implements Cloneable<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T clone() {

        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new LLPayException(e);
        }
    }

}
