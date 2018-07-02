package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunfusheng on 2018/6/30.
 */
@SuppressWarnings("unchecked")
public class MultiType<T, K> {

    @NonNull
    private Map<Object, ItemViewBinder<?, ?>> binderMap = new HashMap<>();
    private KeyGenerator<T, K> keyGenerator;

    public void setKeyGenerator(KeyGenerator<T, K> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @NonNull
    public ItemViewBinder<?, ?> getViewBinder(@NonNull Object model) {
        try {
            T concreteModel = (T) model;
            if (keyGenerator == null) {
                ItemViewBinder<?, ?> binder = binderMap.get(null);
                if (binder != null) {
                    return binder;
                }
            } else {
                K key = keyGenerator.apply(concreteModel);
                if (key != null && binderMap.containsKey(key)) {
                    return binderMap.get(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new BinderNotFoundException(model.getClass());
    }

    @NonNull
    public ItemViewBinder<?, ?> getViewBinderByHashCode(int hashCode, @NonNull Class<? extends T> clazz) {
        for (ItemViewBinder binder : binderMap.values()) {
            if (hashCode == binder.hashCode()) {
                return binder;
            }
        }
        throw new BinderNotFoundException(clazz);
    }

    public void register(K key, @NonNull ItemViewBinder<?, ?> binder) {
        binderMap.put(key, binder);
    }

    public void register(@NonNull ItemViewBinder<?, ?> binder) {
        binderMap.put(null, binder);
    }
}
