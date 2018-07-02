package com.sunfusheng.multitype.sample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class News {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_BIG_IMAGE = 1;
    public static final int TYPE_RIGHT_IMAGE = 2;
    public static final int TYPE_THREE_IMAGES = 3;

    public String title;
    public List<String> images;
    public int type;

    public int getType() {
        return type;
    }

    public News(String title) {
        this.title = title;
        this.type = TYPE_TEXT;
    }

    public News(String title, String url1, boolean showBigImage) {
        this.title = title;
        this.images = new ArrayList<>();
        this.images.add(url1);
        this.type = showBigImage ? TYPE_BIG_IMAGE : TYPE_RIGHT_IMAGE;
    }

    public News(String title, String url1, String url2, String url3) {
        this.title = title;
        this.images = new ArrayList<>();
        this.images.add(url1);
        this.images.add(url2);
        this.images.add(url3);
        this.type = TYPE_THREE_IMAGES;
    }
}
