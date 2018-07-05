# MultiType [ ![Download](https://api.bintray.com/packages/sfsheng0322/maven/MultiType/images/download.svg) ](https://bintray.com/sfsheng0322/maven/MultiType/_latestVersion)

为实现RecyclerView显示多种类型数据，包括各种Headers、Footers，参考[drakeet](https://github.com/drakeet)的扛鼎之作[MultiType](https://github.com/drakeet/MultiType)后，
着重优化了一对多的显示，即一种数据类型对应多种布局的情况，例如新闻数据类型（News.class）就可能显示
文本、左图、右图、大图、多图等多种item布局，面对这种情况就要根据数据中的类型字段（你指定属性的get()方法）去区分，
这个库是通过Java8的新特性传递方法的引用来解决这个问题的。

该库不仅可以显示同一种数据类型对应不同的layout，还可以显示不同数据类型对应不同的layout，如果你没有
通过[MultiTypeRegistry](https://github.com/sfsheng0322/MultiType/blob/master/MultiType/src/main/java/com/sunfusheng/multitype/MultiTypeRegistry.java)全局注册或
通过[MultiTypeAdapter](https://github.com/sfsheng0322/MultiType/blob/master/MultiType/src/main/java/com/sunfusheng/multitype/MultiTypeAdapter.java)局部注册过你的数据类型，
这个库不会让应用崩掉，会返回默认的提示layout，当然你也可以注册自己的默认数据类型，具体使用参考下面。

### Sample预览图

<img src="/resources/res1.png">

### MultiType使用

compile 'com.sunfusheng:MultiType:<latest-version>'

```java
// 全局注册
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
MultiTypeRegistry.getInstance().register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());
MultiTypeRegistry.getInstance().register(Music.class, new MusicBinder());
MultiTypeRegistry.getInstance().register(Video.class, new VideoBinder());

// 注册默认或不支持类型
MultiTypeRegistry.getInstance().registerDefaultBinder(new NonsupportBinder());

// 局部注册，局部注册会覆盖全局的
MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
multiTypeAdapter.register(News.class, News::getType, News.TYPE_TEXT, new TextBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_BIG_IMAGE, new BigImageBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_RIGHT_IMAGE, new RightImageBinder());
multiTypeAdapter.register(News.class, News::getType, News.TYPE_THREE_IMAGES, new ThreeImagesBinder());
multiTypeAdapter.register(Music.class, new MusicBinder());
multiTypeAdapter.register(Video.class, new VideoBinder());

// 初始化
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(multiTypeAdapter);

// 设置数据
multiTypeAdapter.setItems(@NonNull List<?> items);
multiTypeAdapter.notifyDataSetChanged();
```

### 个人微信公众号

<img src="http://ourvm0t8d.bkt.clouddn.com/wx_gongzhonghao.png">

### 打点赏给作者加点油^_^

<img src="http://ourvm0t8d.bkt.clouddn.com/wx_shoukuanma.png" >

### 关于我

[GitHub: sfsheng0322](https://github.com/sfsheng0322)  

[个人邮箱: sfsheng0322@126.com](https://mail.126.com/)
  
[个人博客: sunfusheng.com](http://sunfusheng.com/)
  
[简书主页](http://www.jianshu.com/users/88509e7e2ed1/latest_articles)
  
[新浪微博](http://weibo.com/u/3852192525) 