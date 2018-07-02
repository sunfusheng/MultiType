package com.sunfusheng.multitype.sample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunfusheng on 2018/7/1.
 */
public class ModelUtils {

    public static List<Object> getData() {
        List<Object> items = new ArrayList<>();

        items.add(new News("2018俄罗斯世界杯宣传海报",
                "http://pic.qiantucdn.com/58pic/28/66/48/52f58PICM58PICzBhEjrJk858PICm_PIC2018.jpg!/fw/1024/watermark/url/L2ltYWdlcy93YXRlcm1hcmsvZGF0dS5wbmc=/repeat/true/crop/0x1009a0a4036",
                "http://img4.imgtn.bdimg.com/it/u=2862536439,3018788903&fm=11&gp=0.jpg",
                "http://pic151.nipic.com/file/20180103/12286942_142402682000_2.jpg"));

        items.add(new Video("中国宣传片",
                "1.8G",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530454015736&di=be3f8215420c6f32be1eb0ea3aed9c1a&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe824b899a9014c083545a438007b02087bf4f435.jpg"));

        items.add(new Video("美国景点介绍",
                "821MB",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530454122892&di=d4a55cf862189cfc3e720e7cb8bfaf98&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20150715%2Ftooopen_sy_134075129349.jpg"));

        items.add(new Video("迪拜宣传片",
                "45.8MB",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530454180333&di=3f00f17830a8b4a109f2a719e2b50423&imgtype=0&src=http%3A%2F%2Fb3-q.mafengwo.net%2Fs9%2FM00%2F10%2F5C%2FwKgBs1fP7wOAYAc6AAi1cAtmRt462.jpeg"));

        items.add("123456");

        items.add(123456);

        items.add(new Music("旧时光-专辑《逝年》",
                "夏小虎",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530450422467&di=04d62e0a8dd5fcfe7533bd28a9129609&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160618%2F1466214312233416.jpg"));

        items.add(new Music("逝年-专辑《逝年》",
                "夏小虎",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530450422467&di=04d62e0a8dd5fcfe7533bd28a9129609&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160618%2F1466214312233416.jpg"));

        items.add(new Music("行歌-专辑《一如年少模样》",
                "陈鸿宇",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530452096075&di=e714e728df351d577615cd22df70cf12&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20151215%2F1450148087684531.jpg"));

        items.add(new Music("成都",
                "赵雷",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530452096075&di=e714e728df351d577615cd22df70cf12&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20151215%2F1450148087684531.jpg"));

        items.add(new News("【2018年世界杯】"));

        items.add(new News("法国4-3淘汰阿根廷，姆巴佩一战封神，梅西告别世界杯",
                "http://t10.baidu.com/it/u=758157162,2792065992&fm=173&app=25&f=JPEG?w=572&h=348&s=9AB160810A730A92703D6D5B0300F091",
                false));

        items.add(new News("乌拉圭2-1淘汰葡萄牙，卡瓦尼梅开二度",
                "http://5b0988e595225.cdn.sohucs.com/images/20180701/6cb7570ec33d4537a8b7a69b4b8e6344.jpeg",
                false));

        items.add(new News("梅西与C罗的失落",
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1964293080,4172758237&fm=173&app=25&f=JPEG?w=600&h=338&s=01A20DB95A5723D02F01F4CF0300B080",
                true));

        items.add(new News("2018俄罗斯世界杯宣传海报",
                "http://pic.qiantucdn.com/58pic/28/66/48/52f58PICM58PICzBhEjrJk858PICm_PIC2018.jpg!/fw/1024/watermark/url/L2ltYWdlcy93YXRlcm1hcmsvZGF0dS5wbmc=/repeat/true/crop/0x1009a0a4036",
                "http://img4.imgtn.bdimg.com/it/u=2862536439,3018788903&fm=11&gp=0.jpg",
                "http://pic151.nipic.com/file/20180103/12286942_142402682000_2.jpg"));

        items.add(new Music("旧时光-专辑《逝年》",
                "夏小虎",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530450422467&di=04d62e0a8dd5fcfe7533bd28a9129609&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160618%2F1466214312233416.jpg"));

        items.add(new Music("行歌-专辑《一如年少模样》",
                "陈鸿宇",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530452096075&di=e714e728df351d577615cd22df70cf12&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20151215%2F1450148087684531.jpg"));

        items.add(new News("梅西与C罗的失落",
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1964293080,4172758237&fm=173&app=25&f=JPEG?w=600&h=338&s=01A20DB95A5723D02F01F4CF0300B080",
                true));

        items.add(new Video("迪拜宣传片",
                "45.8MB",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530454180333&di=3f00f17830a8b4a109f2a719e2b50423&imgtype=0&src=http%3A%2F%2Fb3-q.mafengwo.net%2Fs9%2FM00%2F10%2F5C%2FwKgBs1fP7wOAYAc6AAi1cAtmRt462.jpeg"));

        return items;
    }
}
