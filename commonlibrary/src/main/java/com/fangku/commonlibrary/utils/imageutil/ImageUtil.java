package com.fangku.commonlibrary.utils.imageutil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangku.commonlibrary.R;

/**
 * 加载图片工具类  使用glide框架
 * Created by jie.wang
 * Date: 2016/10/16
 * Time:  17:44
 */
public class ImageUtil {

    //下载小图url
    static final String SMALLIMAGEPATH = "http://59.61.87.126:8888/fyz/api/sys/picture/downloadSmallPic/";

    //下载大图url
    static final String BIGIMAGEPATH = "http://59.61.87.126:8888/fyz/api/sys/picture/downloadBigPic/";


    /**
     * 使用gif的连接 则自动在imageview上加载gif图
     * 在列表页尽量使用裁剪后的图片，在查看大图模式下才加载完整的图片。
     */
// TODO: 2016/10/16 传入的url可以是网络地址,文件,uri,资源
    // TODO: 2016/10/16  如果需要传入文件请直接调用原方法
    //加载圆图的话  必须去掉centerCrop中心裁减  否则不会是圆的


    //从文件中加载图片   只加了一种 根据有需要再添加
    public static void loadFile(String url , ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(com.fangku.commonlibrary.R.mipmap.refresh_black)
                .error(com.fangku.commonlibrary.R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .skipMemoryCache(true)//true为不做内存缓存 默认为false缓存
                .into(imageView);


    }

    //从资源文件中加载图片
    public static void loadDrawable(int id, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(id)
                .placeholder(com.fangku.commonlibrary.R.mipmap.refresh_black)
                .error(com.fangku.commonlibrary.R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);

    }



    /**
     * 加载小  圆   图
     *
     * @param url
     * @param imageView
     */
    public static void loadSmallImageCircle(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(SMALLIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
//                .centerCrop()//中心裁剪,缩放填充至整个ImageView 如果加了这句 就不会是圆形的
//              .priority(Priority.HIGH)//高优先级
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }

    /**
     * 加载大   圆   图
     *
     * @param url
     * @param imageView
     */
    public static void loadBigImageCircle(String url, final ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(BIGIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()
//                .centerCrop()//中心裁剪,缩放填充至整个ImageView 如果加了这句 就不会是圆形的
                .transform(new GlideCircleTransform(imageView.getContext()))
//                .priority(Priority.HIGH)//高优先级
                .into(imageView);
    }


    /**
     * 加载小图
     *
     * @param url
     * @param imageView
     */
    public static void loadSmallImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(SMALLIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .centerCrop()//中心裁剪,缩放填充至整个ImageView
//                .priority(Priority.HIGH)//高优先级
                .into(imageView);
    }


    /**
     * 加载小图（指定像素大小）
     *
     * @param url
     * @param imageView
     * @param width
     * @param high
     */
    public static void loadSmallImageWSize(String url, ImageView imageView, int width, int high) {
        Glide.with(imageView.getContext())
                .load(SMALLIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .centerCrop()//中心裁剪,缩放填充至整个ImageView
                .override(width, high)//todo 设置最终显示的图片像素为width*high,注意:这个是像素,而不是控件的宽高
                .into(imageView);
    }


    /**
     * 加载大图，适合浏览大图（无加载在内存缓存中）
     *
     * @param url
     * @param imageView //
     */
    public static void loadBigImageNoCache(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(BIGIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .skipMemoryCache(true)//true为不做内存缓存 默认为false缓存
                .into(imageView);
    }


    /**
     * 加载大图(加载在内存缓存中)
     *
     * @param url
     * @param imageView
     */
    public static void loadBigImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(BIGIMAGEPATH + url)
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);
    }


    /**
     * 加载大图（指定宽高 有缓存）
     *
     * @param url
     * @param imageView
     * @param width
     * @param high
     */
    public static void loadBigImageWithSize(String url, ImageView imageView, int width, int high) {
        Glide.with(imageView.getContext())
                .load(BIGIMAGEPATH + url)
                .override(width, high)//todo 设置最终显示的图片像素为width*high,注意:这个是像素,而不是控件的宽高
                .centerCrop()
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .into(imageView);
    }

    /**
     * 加载大图图   没卵用
     * 通过设置缩略图,我们可以在显示目标图片之前先展示一个第分辨率或者其他图片,当全分辨率的目标图片在后台加载完成后,
     * Glide会自动切换显示全像素的目标图片.
     * <p>
     * 设置缩略图有2种方式:
     * 通过thumbnail(float)指定0.0f~1.0f的原始图像大小,例如全像素的大小是500*500,如果设置为thumbnail为0.1f,
     * 即目标图片的10%,显示的缩略图大小就是50*50;
     */
    public static void small_And_Big(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .placeholder(R.mipmap.refresh_black)
                .error(R.mipmap.default_error)
                .centerCrop()//中心裁剪,缩放填充至整个ImageView
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//保存最终图片
                .thumbnail(0.1f)//10%的原图大小
                .into(imageView);
    }



    /**
     * 清除内存中的缓存 必须在UI线程中调用  当加载过大图片之后,记得清理一下内存缓存  虽然不知道这方法有没有卵用
     *
     * @param context
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘中的缓存 必须在后台线程中调用，建议同时clearMemory()  一般不使用
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }


}
