package com.sunfusheng.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author sunfusheng on 2018/7/2.
 */
public class DefaultBinder extends ItemViewBinder<Object, DefaultBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_binder_default, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Object item) {
        holder.vDesc.setText(BinderNotFoundException.getBinderNotFoundDesc(item.getClass()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vDesc;

        ViewHolder(View view) {
            super(view);
            vDesc = view.findViewById(R.id.desc);
        }
    }
}
