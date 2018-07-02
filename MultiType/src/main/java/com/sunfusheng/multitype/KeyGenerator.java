package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

/**
 * @author sunfusheng on 2018/7/2.
 */
public interface KeyGenerator<T, R> {
    R apply(@NonNull T t) throws Exception;
}
