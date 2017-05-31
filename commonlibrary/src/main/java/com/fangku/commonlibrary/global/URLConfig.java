package com.fangku.commonlibrary.global;

/**
 * 配合ImageUtil使用，使得方法只需传递一个图片ID便可加载图片
 * Created by chenwei.li
 * Date: 2016-01-10
 * Time: 19:55
 */
public interface URLConfig {

    //下载小图
    public static final String SMALLIMAGEPATH = "http://59.61.87.126:8888/fyz/api/sys/picture/downloadSmallPic/";

    //下载大图
    public static final String BIGIMAGEPATH = "http://59.61.87.126:8888/fyz/api/sys/picture/downloadBigPic/";

}
