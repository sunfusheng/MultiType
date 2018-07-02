package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

/**
 * @author sunfusheng on 2018/7/2.
 */
public class MultiTypeRegistry extends MultiTypePool {

    private static final MultiTypeRegistry instance = new MultiTypeRegistry();

    private MultiTypeRegistry() {
        register(DefaultBinderClass.class, new DefaultBinder());
    }

    public static MultiTypeRegistry getInstance() {
        return instance;
    }

    @Override
    public <T, K> void register(@NonNull Class<? extends T> clazz, KeyGenerator<T, K> keyGenerator, K key, @NonNull ItemViewBinder<T, ?> binder) {
        super.register(clazz, keyGenerator, key, binder);
    }

    @Override
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        super.register(clazz, binder);
    }

    @Override
    public void registerDefaultBinder(@NonNull ItemViewBinder<?, ?> defaultBinder) {
        super.registerDefaultBinder(defaultBinder);
    }
}
