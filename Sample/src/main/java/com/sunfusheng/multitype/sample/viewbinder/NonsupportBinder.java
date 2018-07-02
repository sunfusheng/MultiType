package com.sunfusheng.multitype.sample.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.multitype.ItemViewBinder;
import com.sunfusheng.multitype.sample.R;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class NonsupportBinder extends ItemViewBinder<Object, NonsupportBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Object item) {
        holder.vTitle.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
        holder.vTitle.setText("【暂不支持该数据类型】");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;

        ViewHolder(View view) {
            super(view);
            vTitle = view.findViewById(R.id.title);
        }
    }
}
