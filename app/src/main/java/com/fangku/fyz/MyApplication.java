package com.fangku.fyz;

import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseApplication;
import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.widget.CircularAnim;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.TimeCount;

import cn.sharesdk.framework.ShareSDK;


/**
 * Create by: jie.wang
 * Date: 2016-06-17
 * time: 09:41
 */
public class MyApplication extends BaseApplication {
    public static String[] stringItems = {};//房产名集合

    public static String[] houseSysID = {};//房产ID

    public static String[] hydropowerItems = {"表数输错", "上次表数输错", "换表", "越界"};


    public static boolean isLogin;//

    public static boolean isFirst = false;//是否是第一次登录


    // 计时器时间
    public static int time_num = Static_Conmom.TIME_SIZE * 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.isDebug = true;//正式上线之后设为false将不显示log

        CircularAnim.init(600, 650, R.color.background_color);
        ShareSDK.initSDK(this);

    }


    /**
     * 运行短信计时器
     *
     * @param textView
     */
    public static void startTime(TextView textView) {
        if (time_num / 1000 == 1) {
            time_num = Static_Conmom.TIME_SIZE * 1000;
        }
        TimeCount time = new TimeCount(time_num, 1000, textView);
        time.start();

    }


}
