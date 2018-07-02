package com.sunfusheng.multitype.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    private RecyclerView recyclerView;
    private MultiTypeAdapter multiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initMultiType();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initMultiType() {
        // 全局注册
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
        MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());
        MultiTypeRegistry.getInstance().registerDefaultBinder(new NonsupportBinder());

        multiTypeAdapter = new MultiTypeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multiTypeAdapter);

        // 局部注册，注意局部会覆盖全局
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new BigImageBinder());
//        multiTypeAdapter.register(News.class, News::getType, News.TYPE_THREE_IMAGES, new BigImageBinder());
        multiTypeAdapter.register(Music.class, new MusicBinder());
        multiTypeAdapter.register(Video.class, new VideoBinder());

        multiTypeAdapter.setItems(ModelUtils.getData());
        multiTypeAdapter.notifyDataSetChanged();
    }
}
