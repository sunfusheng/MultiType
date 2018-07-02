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
import com.sunfusheng.multitype.sample.model.Video;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class VideoBinder extends ItemViewBinder<Video, VideoBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Video item) {
        holder.vName.setText(item.name);
        holder.vSize.setText(item.size);
        holder.vImage.centerCrop().load(item.image, R.color.placeholder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vName;
        TextView vSize;
        GlideImageView vImage;

        ViewHolder(View view) {
            super(view);
            vName = view.findViewById(R.id.name);
            vSize = view.findViewById(R.id.size);
            vImage = view.findViewById(R.id.image);
        }
    }
}
