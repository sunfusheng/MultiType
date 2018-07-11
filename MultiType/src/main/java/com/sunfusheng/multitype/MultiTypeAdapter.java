package com.sunfusheng.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import static com.sunfusheng.multitype.Preconditions.checkNotNull;


/**
 * @author sunfusheng on 2018/6/30.
 */
@SuppressWarnings("unchecked")
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

    @NonNull
    private List<?> items;
    @NonNull
    private MultiTypePool typePool;

    private LayoutInflater inflater;
    private ItemViewBinder viewBinder;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public MultiTypeAdapter() {
        this(Collections.emptyList());
    }

    public MultiTypeAdapter(@NonNull List<?> items) {
        this(items, new MultiTypePool());
    }

    public MultiTypeAdapter(@NonNull List<?> items, @NonNull MultiTypePool pool) {
        checkNotNull(items);
        checkNotNull(pool);
        this.items = items;
        this.typePool = pool;
        this.typePool.setTypeMap(MultiTypeRegistry.getInstance().getTypeMap());
        this.typePool.setDefaultBinder(MultiTypeRegistry.getInstance().getDefaultBinder());
    }

    public <T, K> void register(@NonNull Class<? extends T> clazz, KeyGenerator<T, K> keyGenerator, K key, @NonNull ItemViewBinder<T, ?> binder) {
        typePool.register(clazz, keyGenerator, key, binder);
    }

    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        typePool.register(clazz, binder);
    }

    public void registerDefaultBinder(@NonNull ItemViewBinder<?, ?> defaultBinder) {
        typePool.registerDefaultBinder(defaultBinder);
    }

    @NonNull
    public List<?> getItems() {
        return items;
    }

    public void setItems(@NonNull List<?> items) {
        checkNotNull(items);
        this.items = items;
    }

    @Override
    public final int getItemViewType(int position) {
        viewBinder = typePool.getViewBinder(items.get(position));
        return viewBinder.hashCode();
    }

    @NonNull
    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return viewBinder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        viewBinder.onBindViewHolder(holder, items.get(position), payloads);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(items.get(position));
                }
            });
        }

        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                if (onItemLongClickListener != null) {
                    return onItemLongClickListener.onItemLongClick(items.get(position));
                }
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        Object item = items.get(position);
        ItemViewBinder binder = typePool.getViewBinder(item);
        return binder.getItemId(item);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        ItemViewBinder viewBinder = getItemViewBinderByViewHolder(holder);
        if (viewBinder != null) {
            viewBinder.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        ItemViewBinder viewBinder = getItemViewBinderByViewHolder(holder);
        if (viewBinder != null) {
            viewBinder.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        ItemViewBinder viewBinder = getItemViewBinderByViewHolder(holder);
        if (viewBinder != null) {
            viewBinder.onViewRecycled(holder);
        }
    }

    private ItemViewBinder getItemViewBinderByViewHolder(@NonNull ViewHolder holder) {
        if (holder.getLayoutPosition() < items.size()) {
            return typePool.getViewBinder(items.get(holder.getLayoutPosition()));
        }
        return null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Object item);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Object item);
    }
}
