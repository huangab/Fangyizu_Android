package com.fangku.fyz.widget;/**
 * Created by 67343 on 2016/9/8.
 */

import android.content.Context;
import android.util.Log;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.bean.Bean_getHead_result;
import com.fangku.fyz.constant.Url_final;

/**
 * 用于图片上传
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class UpLodionImages {

    ImageUPLinstent imageUPLinstent;
    Context context;
    int size;
    boolean[] isOk;


    public UpLodionImages(Context context, int size) {
        this.context = context;
        this.size = size;
        isOk = new boolean[size];
    }

    public void doUp(String mShowAddress, int position) {
        Log.i("test", "UpLodionImages1");
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.uploadFile(Url_final.UPLOAD_HEAD_IMG, mShowAddress, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("test", "onSuccess_image:" + result);
                if (result != null) {
                    Bean_getHead_result imgdata = JsonMananger.jsonToBean(result, Bean_getHead_result.class);
                    if (("200").equals(imgdata.getCode())) {
                        Log.i("test", "size:" + size + "  position:" + position);

                        isOk[position - 1] = true;
                        imageUPLinstent.getImgaeID(imgdata.getData().getSysId(), isOk, position);
//                        if (position == size) {
//                            imageUPLinstent.getImgaeID(imgdata.getData().getSysId(), true);
//                        } else {
//                            imageUPLinstent.getImgaeID(imgdata.getData().getSysId(), false);
//                        }

                    }
                }
            }

            @Override
            public void onFailed(Exception e) {
                Log.i("test", "onFailed_imgae:" + e.getMessage());
                ToastUtil.showShort(context.getApplicationContext(), "图片上传失败：" + e.getMessage());
            }
        });
    }

    public void setImageUPLinstent(ImageUPLinstent imageUPLinstent) {
        this.imageUPLinstent = imageUPLinstent;
    }

    public interface ImageUPLinstent {
        public void getImgaeID(String imagID, boolean[] isLast, int position);
    }
}
