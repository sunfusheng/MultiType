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
public class ThreeImagesBinder extends ItemViewBinder<News, ThreeImagesBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_three_images, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull News item) {
        holder.vTitle.setText(item.title);
        holder.vImage1.centerCrop().load(item.images.get(0), R.color.placeholder);
        holder.vImage2.centerCrop().load(item.images.get(1), R.color.placeholder);
        holder.vImage3.centerCrop().load(item.images.get(2), R.color.placeholder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;
        GlideImageView vImage1;
        GlideImageView vImage2;
        GlideImageView vImage3;

        ViewHolder(View view) {
            super(view);
            vTitle = view.findViewById(R.id.title);
            vImage1 = view.findViewById(R.id.image1);
            vImage2 = view.findViewById(R.id.image2);
            vImage3 = view.findViewById(R.id.image3);
        }
    }
}
