package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

/**
 * @author sunfusheng on 2018/6/30.
 */
@SuppressWarnings("ALL")
public final class Preconditions {

    private Preconditions() {
    }

    public static @NonNull <T> T checkNotNull(@NonNull final T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return object;
    }

}
