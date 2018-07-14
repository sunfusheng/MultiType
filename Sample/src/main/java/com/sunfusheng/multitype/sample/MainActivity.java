package com.sunfusheng.multitype.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sunfusheng.FirUpdater;
import com.sunfusheng.FirUpdaterUtils;
import com.sunfusheng.multistate.LoadingState;
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
import com.sunfusheng.wrapper.RecyclerViewWrapper;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        RecyclerViewWrapper.OnRefreshListener,
        RecyclerViewWrapper.OnLoadMoreListener,
        MultiTypeAdapter.OnItemClickListener,
        MultiTypeAdapter.OnItemLongClickListener {

    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private RecyclerViewWrapper recyclerViewWrapper;
    private List<Object> items;
    private int page = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview_wrapper);

        init();
        initMultiType();
    }

    private void init() {
        setTitle(getString(R.string.app_name_with_version, FirUpdaterUtils.getVersionName(this)));

        new FirUpdater(this)
                .apiToken("3c57fb226edf7facf821501e4eba08d2")
                .appId("5b3ec988548b7a3d7bd77c8f")
                .checkVersion();

        recyclerViewWrapper = findViewById(R.id.recyclerViewWrapper);
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
//        recyclerViewWrapper.register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
//        recyclerViewWrapper.register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
//        recyclerViewWrapper.register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new BigImageBinder());
//        recyclerViewWrapper.register(News.class, News::getType, News.TYPE_THREE_IMAGES, new BigImageBinder());
        recyclerViewWrapper.register(Music.class, new MusicBinder());
        recyclerViewWrapper.register(Video.class, new VideoBinder());

        recyclerViewWrapper.setOnItemClickListener(this);
        recyclerViewWrapper.setOnItemLongClickListener(this);
        recyclerViewWrapper.setOnRefreshListener(this);
        recyclerViewWrapper.setOnLoadMoreListener(this);

        mainHandler.postDelayed(() -> {
            if (new Random().nextInt(5) == 0) {
                recyclerViewWrapper.setLoadingState(LoadingState.ERROR);
            } else {
                items = ModelUtils.getDataSource();
                recyclerViewWrapper.setItems(items);
            }
        }, 1500);
    }

    @Override
    public void onRefresh() {
        mainHandler.postDelayed(() -> {
            page = -1;
            items = ModelUtils.getDataSource();
            recyclerViewWrapper.setItems(items);
        }, 1500);
    }

    @Override
    public void onLoadMore() {
        mainHandler.postDelayed(() -> {
            page++;
            if (page == 6) {
                recyclerViewWrapper.setLoadMoreSuccessTip("没有更多数据了");
                recyclerViewWrapper.setLoadMoreEmpty();
            } else {
                items.addAll(ModelUtils.getTestDataSource(page, 15));
                recyclerViewWrapper.setItems(items);
            }
        }, 1500);
    }

    @Override
    public void onItemClick(Object item) {
        Toast.makeText(this, "OnItemClick: " + item.getClass().getSimpleName() + ".class", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(Object item) {
        Toast.makeText(this, "OnItemLongClick: " + item.getClass().getSimpleName() + ".class", Toast.LENGTH_SHORT).show();
        return true;
    }
}
