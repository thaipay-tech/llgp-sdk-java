package com.lianlian.global.payment.sdk.support;

import java.io.Serializable;
import java.util.Objects;

/**
 * @param <K>
 * @param <V>
 * @author lianlian
 */
public final class Pair<K, V> extends CloneSupport<Pair<K, V>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final K key;
    private final V value;

    private Pair(K key, V value) {

        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair<K, V> of(K key, V value) {

        return new Pair<>(key, value);
    }

    public K getKey() {

        return this.key;
    }

    public V getValue() {

        return this.value;
    }

    @Override
    public String toString() {

        return "Pair [key=" + key + ", value=" + value + "]";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }
        if (o instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(getKey(), pair.getKey()) &&
                    Objects.equals(getValue(), pair.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
}
