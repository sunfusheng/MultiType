package com.sunfusheng.multitype.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunfusheng.FirUpdater;
import com.sunfusheng.FirUpdaterUtils;
import com.sunfusheng.multistate.LoadingState;
import com.sunfusheng.multistate.MultiStateView;
import com.sunfusheng.multitype.MultiTypeAdapter;
import com.sunfusheng.multitype.MultiTypeRegistry;
import com.sunfusheng.multitype.sample.model.ModelUtils;
import com.sunfusheng.multitype.sample.model.Music;
import com.sunfusheng.multitype.sample.model.News;
import com.sunfusheng.multitype.sample.model.Video;
import com.sunfusheng.multitype.sample.viewbinder.BigImageBinder;
import com.sunfusheng.multitype.sample.viewbinder.MusicBinder;
import com.sunfusheng.multitype.sample.viewbinder.NonsupportBinder;
import com.sunfusheng.multitype.sample.viewbinder.RightImageBinder;
import com.sunfusheng.multitype.sample.viewbinder.TextBinder;
import com.sunfusheng.multitype.sample.viewbinder.ThreeImagesBinder;
import com.sunfusheng.multitype.sample.viewbinder.VideoBinder;

public class MainActivity extends AppCompatActivity {
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    private MultiTypeAdapter multiTypeAdapter;
    private MultiStateView multiStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview_wrapper);
        setTitle(getString(R.string.app_name_with_version, FirUpdaterUtils.getVersionName(this)));

        new FirUpdater(this)
                .apiToken("3c57fb226edf7facf821501e4eba08d2")
                .appId("5b3ec988548b7a3d7bd77c8f")
                .checkVersion();

        multiStateView = findViewById(R.id.multiStateView);

        initMultiType();
        initMultiState();
    }

    private void initMultiType() {
        // 全局注册
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());

        // 注册默认或不支持类型
        MultiTypeRegistry.getInstance().registerDefaultBinder(new NonsupportBinder());

        // 局部注册，局部注册会覆盖全局的
        multiTypeAdapter = new MultiTypeAdapter();
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new BigImageBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_THREE_IMAGES, new BigImageBinder());
        multiTypeAdapter.register(Music.class, new MusicBinder());
        multiTypeAdapter.register(Video.class, new VideoBinder());

        multiTypeAdapter.setOnItemClickListener(item -> {
            Toast.makeText(this, "OnItemClick: " + item.getClass().getSimpleName() + ".class", Toast.LENGTH_SHORT).show();
        });

        multiTypeAdapter.setOnItemLongClickListener(item -> {
            Toast.makeText(this, "OnItemLongClick: " + item.getClass().getSimpleName() + ".class", Toast.LENGTH_SHORT).show();
            return true;
        });

        // 初始化
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multiTypeAdapter);
    }

    // 设置数据
    private void setData() {
        multiStateView.setLoadingState(LoadingState.LOADING);
        mainHandler.postDelayed(() -> {
            multiStateView.setLoadingState(LoadingState.SUCCESS);
            multiTypeAdapter.setItems(ModelUtils.getData());
            multiTypeAdapter.notifyDataSetChanged();
        }, 1000);
    }

    private void initMultiState() {
        View errorView = multiStateView.getErrorView();
        View vRetry = errorView.findViewById(R.id.retry);
        vRetry.setOnClickListener(v -> setData());

        multiStateView.setLoadingState(LoadingState.LOADING);
        mainHandler.postDelayed(() -> {
            multiStateView.setLoadingState(LoadingState.ERROR);
        }, 1000);
    }

}
