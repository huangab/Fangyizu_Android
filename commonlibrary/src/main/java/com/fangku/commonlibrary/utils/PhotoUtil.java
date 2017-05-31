package com.fangku.commonlibrary.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;


/**
 * 相机，相册相关工具类
 * Created by Lichenwei
 * Date: 2016-01-06
 * Time: 00:19
 */
public class PhotoUtil {

    private static final String TAG = PhotoUtil.class.getSimpleName();

    private PhotoUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 打开手机相册选取多张图片
     * @param activity 上下文对象
     * @param data 当前存储图片的数据集
     * @param requestSize 选取照片数量
     * @param requestCode 回调请求码
     * 在onActivityResult方法里接收图片地址集合，记得获取前先对data进行clear清空，避免数据重复
     * List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
     */
    public static void openAlbumSelectPhotos(Activity activity, ArrayList<String> data, int requestSize, int requestCode) {
        MultiImageSelector.create(activity)
                .showCamera(true) //是否显示相机
                .count(requestSize) // 图片最大选择数
                .multi() // 多选模式
                .origin(data) // 默认已选择图片,只有在选择模式为多选时有效
                .start(activity, requestCode);
    }

    /**
     * 打开手机相册选取单张图片
     *
     * @param activity
     * @param requestCode 在onActivityResult方法里接收图片地址,取集合第一张
     *                    List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
     */
    public static void openAlbumSelectPhoto(Activity activity, int requestCode) {
        MultiImageSelector.create(activity)
                .showCamera(true) //是否显示相机
                .single() // 单选模式
                .start(activity, requestCode);
    }

    /**
     * 打开手机摄像头拍照
     * @param activity
     * @param filePath
     * @param requestCode
     * @return
     */
    public static boolean takePhoto(Activity activity,final String filePath, final int requestCode) {

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            Uri outputFileUri = Uri.fromFile(file);
            //将照片路径存放到指定的文件路径下
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        }
        try {
            activity.startActivityForResult(intent, requestCode);

        } catch (final ActivityNotFoundException e) {
            return false;
        }
        return true;
    }


    /**
     * 图片裁剪
     *
     * @param activity
     * @param filePath
     * @param outputX
     * @param outputY
     */
    public static void startCorpImage(Activity activity, String filePath, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 保存图片
     *
     * @param filePath
     * @param bitmap
     * @param isDelete
     */
    public static void saveBitmap(String filePath, Bitmap bitmap, boolean isDelete) {

        File file = new File(filePath);
        // 若存在即删除-默认只保留一张
        if (isDelete) {
            if (file.exists()) {
                file.delete();
            }
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 计算压缩比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int h = options.outHeight;
        int w = options.outWidth;
        int inSampleSize = 0;
        if (h > reqHeight || w > reqWidth) {
            float ratioW = (float) w / reqWidth;
            float ratioH = (float) h / reqHeight;
            inSampleSize = (int) Math.min(ratioH, ratioW);
        }
        inSampleSize = Math.max(1, inSampleSize);
        return inSampleSize;
    }

    /**
     * 压缩Bitmap
     * @param filePath
     * @param reqWidth 压缩宽度（默认1M=1024）
     * @param reqHeight 压缩高度（默认1M=1024）
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

}


