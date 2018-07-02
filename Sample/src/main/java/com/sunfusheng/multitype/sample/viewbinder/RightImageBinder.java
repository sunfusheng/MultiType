package com.sunfusheng.multitype.sample.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.GlideImageView;
import com.sunfusheng.multitype.ItemViewBinder;
import com.sunfusheng.multitype.sample.R;
import com.sunfusheng.multitype.sample.model.News;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class RightImageBinder extends ItemViewBinder<News, RightImageBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_right_image, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull News item) {
        holder.vTitle.setText(item.title);
        holder.vImage.centerCrop().load(item.images.get(0), R.color.placeholder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;
        GlideImageView vImage;

        ViewHolder(View view) {
            super(view);
            vTitle = view.findViewById(R.id.title);
            vImage = view.findViewById(R.id.image);
        }
    }
}
