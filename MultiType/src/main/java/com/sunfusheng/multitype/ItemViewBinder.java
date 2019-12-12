package com.sunfusheng.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author sunfusheng on 2018/6/30.
 */
public abstract class ItemViewBinder<T, VH extends ViewHolder> {

    @NonNull
    protected abstract VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item);

    protected void onBindViewHolder(@NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, item);
    }

    protected long getItemId(@NonNull T item) {
        return RecyclerView.NO_ID;
    }

    protected void onViewAttachedToWindow(@NonNull VH holder) {
    }

    protected void onViewDetachedFromWindow(@NonNull VH holder) {
    }

    protected void onViewRecycled(@NonNull VH holder) {
    }
}
