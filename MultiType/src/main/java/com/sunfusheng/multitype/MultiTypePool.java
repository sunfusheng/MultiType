package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.sunfusheng.multitype.Preconditions.checkNotNull;

/**
 * @author sunfusheng on 2018/6/30.
 */
@SuppressWarnings("unchecked")
public class MultiTypePool {

    @NonNull
    private Map<Class, MultiType> typeMap = new HashMap<>();
    private ItemViewBinder<?, ?> defaultBinder;

    public <T, K> void register(@NonNull Class<? extends T> clazz, KeyGenerator<T, K> keyGenerator, K key, @NonNull ItemViewBinder<T, ?> binder) {
        checkNotNull(clazz);
        checkNotNull(binder);
        MultiType multiType = typeMap.get(clazz);
        if (multiType == null) {
            multiType = new MultiType();
            typeMap.put(clazz, multiType);
        }
        multiType.setKeyGenerator(keyGenerator);
        multiType.register(key, binder);
    }

    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        register(clazz, null, null, binder);
    }

    public void registerDefaultBinder(@NonNull ItemViewBinder<?, ?> defaultBinder) {
        this.defaultBinder = defaultBinder;
    }

    @NonNull
    public ItemViewBinder<?, ?> getViewBinder(@NonNull Object model) {
        ItemViewBinder<?, ?> binder;
        MultiType multiType = typeMap.get(model.getClass());
        if (multiType != null) {
            binder = multiType.getViewBinder(model);
            if (binder != null) {
                return binder;
            }
        }

        binder = getDefaultViewBinder(model);
        if (binder != null) {
            return binder;
        }
        throw new BinderNotFoundException(model.getClass());
    }

    @NonNull
    public Map<Class, MultiType> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(@NonNull Map<Class, MultiType> typeMap) {
        if (typeMap.size() > 0) {
            this.typeMap.putAll(typeMap);
        }
    }

    public ItemViewBinder<?, ?> getDefaultViewBinder(@NonNull Object model) {
        if (defaultBinder != null) {
            return defaultBinder;
        }
        return typeMap.get(DefaultBinderClass.class).getViewBinder(model);
    }

    public ItemViewBinder<?, ?> getDefaultBinder() {
        return defaultBinder;
    }

    public void setDefaultBinder(ItemViewBinder<?, ?> defaultBinder) {
        this.defaultBinder = defaultBinder;
    }
}
