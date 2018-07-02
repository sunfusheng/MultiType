package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class BinderNotFoundException extends RuntimeException {

    BinderNotFoundException(@NonNull Class<?> clazz) {
        super(getBinderNotFoundDesc(clazz));
    }

    public static String getBinderNotFoundDesc(@NonNull Class<?> clazz) {
        return "Have you registered this type of the 【{className}.class】 in the MultiTypeAdapter/MultiTypeRegistry?"
                .replace("{className}", clazz.getSimpleName());
    }
}
