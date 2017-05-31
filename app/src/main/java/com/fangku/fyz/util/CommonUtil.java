package com.fangku.fyz.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fangku.commonlibrary.utils.StorageUtil;
import com.fangku.fyz.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包含一些常用方法
 * Created by 67343 on 2016/7/12.
 */
public class CommonUtil {


    /**
     * 淡入淡出效果(目前不使用)
     *
     * @param activity
     */
    public static void show(Activity activity) {
        activity.overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果

    }

    /**
     * 从下到上的控件动画
     */
    public static TranslateAnimation show_in_bottom() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(150);
        return mShowAction;
    }

    /**
     * 从上到下的控件动画(未修改  有问题)
     */
    public static TranslateAnimation show_in_top() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(150);
        return mShowAction;
    }


    // 正确格式为：以字母与数字穿插，长度在8-20 --已验证

    /**
     * 强密码校验
     *
     * @param inputString
     * @return
     */
    public static boolean isHardPassword(String inputString) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$");
        Matcher macher = pattern.matcher(inputString);
        return macher.find();
    }


    /**
     * BITMAP转换为文件路径
     *
     * @param bitmap
     */
    public static File saveBitmapFile(Bitmap bitmap) {
        File file = new File(StorageUtil.IMAGE_DIR + System.currentTimeMillis() + "head.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bos);//质量 最高为100
            bos.flush();
            bos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 明文显示密码
     */
    public static void PasswordTrue(CheckBox box, EditText editText1) {


        if (box.isChecked()) {
            box.setChecked(false);
        } else {
            box.setChecked(true);
        }
        if (box.isChecked()) {

            //设置为明文显示
            editText1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText1.setSelection(editText1.length());

        } else {
            //设置为秘闻显示
            editText1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText1.setSelection(editText1.length());

        }

    }


    /**
     * 手机号中间加空格
     */
    public static String phone_set_null(String old_phone) {

        String new_phone = old_phone.substring(0, 3) + " " + old_phone.substring(3, 7) + " " + old_phone.substring(7);

        return new_phone;
    }


    /**
     * 日期算法  用于快速计算租期
     * (传入月数 和开始时间 , 传入的格式必须为xxxx-xx-xx
     * 返回 开始时间+月数=结束时间 )
     *
     * @param size
     */
    //// TODO: 2016/10/5 目前是按照自然月计算  已计算月底为28 29 30 31时候出现不够30天的问题  未测试10/16
    public static String setTime(String size, String startTime) {
        startTime = startTime.replace("-", "");
        int nian = Integer.valueOf(startTime.substring(0, 4));
        int yue = Integer.valueOf(startTime.substring(4, 6));
        int ri = Integer.valueOf(startTime.substring(6, 8));
        yue = yue + Integer.valueOf(size);
        String newyue = yue + "";


        if (yue < 10) {
            newyue = "0" + yue;
        }
        if (yue > 12) {
            if (yue % 12 == 11) {
                newyue = "11";
            } else if (yue % 12 == 0) {
                newyue = "12";
            } else if (yue % 12 == 10) {
                newyue = "10";
            } else {
                newyue = "0" + yue % 12;
            }
            nian = nian + yue / 12;
        }

        if (newyue.equals("02") && ri > 29) {
            ri = 29;
        }
        if (newyue.equals("04") && newyue.equals("06") && newyue.equals("09") && newyue.equals("11") && ri > 30) {
            ri = 30;
        }


        String endTime = nian + "-" + newyue + "-" + ri;

        return endTime;
    }


}
