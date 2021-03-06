package com.sunfusheng.wrapper;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunfusheng.multistate.LoadingState;
import com.sunfusheng.multistate.MultiStateView;
import com.sunfusheng.multitype.ItemViewBinder;
import com.sunfusheng.multitype.KeyGenerator;
import com.sunfusheng.multitype.MultiTypeAdapter;
import com.sunfusheng.multitype.MultiTypeRegistry;

import java.util.List;

/**
 * @author sunfusheng on 2018/7/11.
 */
public class RecyclerViewWrapper extends FrameLayout {
    private MultiStateView multiStateView;
    private View loadingView;
    private View normalView;
    private View errorView;
    private View emptyView;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ClassicsHeader headerView;
    private ClassicsFooter footerView;

    private MultiTypeAdapter multiTypeAdapter;
    private OnRefreshListener onRefreshListener;
    private OnLoadMoreListener onLoadMoreListener;

    private boolean hasRegisterGlobalType;
    private boolean isRefreshing;
    private boolean isLoadingMore;

    static {
        //Header提示
        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = "正在刷新...";//"刷新成功";
        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败，请重试";

        //Footer提示
        ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";
        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载...";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = "正在加载...";//"加载成功";
        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败，请重试";
        ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";
    }

    public RecyclerViewWrapper(Context context) {
        this(context, null);
    }

    public RecyclerViewWrapper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_multistateview, this);
        multiStateView = findViewById(R.id.multiStateView);
        loadingView = multiStateView.getLoadingView();
        normalView = multiStateView.getNormalView();
        errorView = multiStateView.getErrorView();
        emptyView = multiStateView.getEmptyView();
        refreshLayout = normalView.findViewById(R.id.refreshLayout);
        recyclerView = normalView.findViewById(R.id.recyclerView);

        multiTypeAdapter = new MultiTypeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(multiTypeAdapter);

        setLoadingState(LoadingState.LOADING);
        refreshLayout.setEnableAutoLoadMore(true);

        headerView = (ClassicsHeader) refreshLayout.getRefreshHeader();
        footerView = (ClassicsFooter) refreshLayout.getRefreshFooter();
        headerView.setFinishDuration(0);
        footerView.setFinishDuration(0);
        setHeaderBackgroundColor(R.color.color_text_first);
        setHeaderTextColor(R.color.color_text_white);

        View vRetry = errorView.findViewById(R.id.retry);
        if (vRetry != null) {
            vRetry.setOnClickListener(v -> {
                setLoadingState(LoadingState.LOADING);
                if (onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
            });
        }

        refreshLayout.setOnRefreshListener(it -> {
            isRefreshing = it.getState() == RefreshState.Refreshing;
            if (isRefreshing && onRefreshListener != null) {
                onRefreshListener.onRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(it -> {
            isLoadingMore = it.getState() == RefreshState.Loading;
            if (isLoadingMore && onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore();
            }
        });
    }

    public <T, K> void register(@NonNull Class<? extends T> clazz, KeyGenerator<T, K> keyGenerator, K key, @NonNull ItemViewBinder<T, ?> binder) {
        multiTypeAdapter.register(clazz, keyGenerator, key, binder);
    }

    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        multiTypeAdapter.register(clazz, binder);
    }

    public void registerDefaultBinder(@NonNull ItemViewBinder<?, ?> defaultBinder) {
        multiTypeAdapter.registerDefaultBinder(defaultBinder);
    }

    public void setItems(@NonNull List<?> items, boolean notifyDataSetChanged) {
        if (!hasRegisterGlobalType) {
            hasRegisterGlobalType = true;
            multiTypeAdapter.register(MultiTypeRegistry.getInstance());
        }

        if (items == null || items.size() == 0) {
            setLoadingState(LoadingState.EMPTY);
        } else {
            multiTypeAdapter.setItems(items, notifyDataSetChanged);
            setLoadingState(LoadingState.SUCCESS);
        }

        if (isRefreshing) {
            isRefreshing = false;
            refreshLayout.finishRefresh();
        }

        if (isLoadingMore) {
            isLoadingMore = false;
            refreshLayout.finishLoadMore();
        }
    }

    public void setItems(@NonNull List<?> items) {
        setItems(items, true);
    }

    public List<?> getItems() {
        return multiTypeAdapter.getItems();
    }

    public void setLoadingState(@LoadingState int loadingState) {
        multiStateView.setLoadingState(loadingState);
    }

    public View setLoadingLayout(@LayoutRes int layoutResID) {
        return multiStateView.setLoadingLayout(layoutResID);
    }

    public View setErrorLayout(@LayoutRes int layoutResID) {
        return multiStateView.setErrorLayout(layoutResID);
    }

    public View setEmptyLayout(@LayoutRes int layoutResID) {
        return multiStateView.setEmptyLayout(layoutResID);
    }

    public void setErrorViewListener(OnClickListener listener) {
        multiStateView.setErrorViewListener(listener);
    }

    public void setEmptyViewListener(OnClickListener listener) {
        multiStateView.setEmptyViewListener(listener);
    }

    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public View getNormalView() {
        return normalView;
    }

    public View getErrorView() {
        return errorView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public MultiTypeAdapter getMultiTypeAdapter() {
        return multiTypeAdapter;
    }

    public int getLoadingState() {
        return multiStateView.getLoadingState();
    }

    public void setOnItemClickListener(MultiTypeAdapter.OnItemClickListener onItemClickListener) {
        multiTypeAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(MultiTypeAdapter.OnItemLongClickListener onItemLongClickListener) {
        multiTypeAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setHeaderBackgroundColor(@ColorRes int resId) {
        refreshLayout.setPrimaryColorsId(resId);
    }

    public void setHeaderTextColor(@ColorRes int resId) {
        if (headerView != null) {
            headerView.setAccentColor(getContext().getResources().getColor(resId));
        }
    }

    public void enableRefresh(boolean enabled) {
        refreshLayout.setEnableRefresh(enabled);
    }

    public void enableLoadMore(boolean enabled) {
        refreshLayout.setEnableLoadMore(enabled);
    }

    public void setRefreshSuccessTip(String tip) {
        ClassicsHeader.REFRESH_HEADER_FINISH = TextUtils.isEmpty(tip) ? "刷新成功" : tip;
    }

    public void setLoadMoreSuccessTip(String tip) {
        ClassicsFooter.REFRESH_FOOTER_FINISH = TextUtils.isEmpty(tip) ? "加载成功" : tip;
    }

    public void setRefreshError() {
        refreshLayout.finishRefresh(false);
    }

    public void setLoadMoreError() {
        refreshLayout.finishLoadMore(false);
    }

    public void setLoadMoreEmpty() {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }
}
