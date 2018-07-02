# MultiType

### 多类型列表使用

```java
// 全局注册
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());
MultiTypeRegistry.getInstance().register(Music.class, new MusicBinder());
MultiTypeRegistry.getInstance().register(Video.class, new VideoBinder());

// 注册不支持类型
MultiTypeRegistry.getInstance().registerDefaultBinder(new NonsupportBinder());

// 局部注册
MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
multiTypeAdapter.register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());
multiTypeAdapter.register(Music.class, new MusicBinder());
multiTypeAdapter.register(Video.class, new VideoBinder());

// 设置数据
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(multiTypeAdapter);

multiTypeAdapter.setItems(@NonNull List<?> items);
multiTypeAdapter.notifyDataSetChanged();
```