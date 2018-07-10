package com.sunfusheng.multistate;

import android.view.View;

/**
 * @author by sunfusheng on 2018/7/6.
 */
public class LoadingStateDelegate {
    private View views[] = new View[4];

    public LoadingStateDelegate(View loadingView, View successView, View errorView, View emptyView) {
        views[LoadingState.LOADING] = loadingView;
        views[LoadingState.SUCCESS] = successView;
        views[LoadingState.ERROR] = errorView;
        views[LoadingState.EMPTY] = emptyView;
    }

    public void setLoadingState(@LoadingState int state) {
        if (state < 0 || state >= views.length) {
            return;
        }

        for (View view : views) {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }

        if (views[state] != null) {
            views[state].setVisibility(View.VISIBLE);
        }
    }

    public void setLoadingView(View loadingView) {
        views[LoadingState.LOADING] = loadingView;
    }

    public void setSuccessView(View successView) {
        views[LoadingState.SUCCESS] = successView;
    }

    public void setErrorView(View errorView) {
        views[LoadingState.ERROR] = errorView;
    }

    public void setEmptyView(View emptyView) {
        views[LoadingState.EMPTY] = emptyView;
    }

    public View getLoadingView() {
        return views[LoadingState.LOADING];
    }

    public View getSuccessView() {
        return views[LoadingState.SUCCESS];
    }

    public View getErrorView() {
        return views[LoadingState.ERROR];
    }

    public View getEmptyView() {
        return views[LoadingState.EMPTY];
    }
}
