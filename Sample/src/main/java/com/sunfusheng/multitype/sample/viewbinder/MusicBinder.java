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
import com.sunfusheng.multitype.sample.model.Music;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class MusicBinder extends ItemViewBinder<Music, MusicBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_music, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Music item) {
        holder.vName.setText(item.name);
        holder.vAuthor.setText(item.author);
        holder.vImage.centerCrop().load(item.image);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView vName;
        TextView vAuthor;
        GlideImageView vImage;

        ViewHolder(View view) {
            super(view);
            vName = view.findViewById(R.id.name);
            vAuthor = view.findViewById(R.id.author);
            vImage = view.findViewById(R.id.image);
        }
    }
}
