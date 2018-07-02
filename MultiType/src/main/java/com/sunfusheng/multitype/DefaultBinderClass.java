package com.sunfusheng.multitype;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author sunfusheng on 2018/7/2.
 */
public class DefaultBinderClass<T> implements Serializable {
    public T item;
    public String desc = "";

    public DefaultBinderClass() {
    }

    public void setItem(@NonNull T item) {
        this.item = item;
        this.desc = BinderNotFoundException.getBinderNotFoundDesc(item.getClass());
    }
}
