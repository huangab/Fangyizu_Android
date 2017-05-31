package com.fangku.commonlibrary.global;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.utils.AppUtil;
import com.fangku.commonlibrary.utils.StorageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * 全局异常处理类，保存异常信息到SD卡或者上传服务器（开发调试的时候请关闭）
 * 在Application中全局注册调用（CustomCrash.getInstance().setCustomCrashInfo(this);）
 * Created by chenwei.li
 * Date: 2016/1/29
 * Time: 23:26
 */
public class CustomCrash implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CustomCrash";
    private static final int TYPE_SAVE_SDCARD = 1; //崩溃日志保存本地SDCard  --建议开发模式使用
    private static final int TYPE_SAVE_REMOTE = 2; //崩溃日志保存远端服务器 --建议生产模式使用

    private int type_save = 1;  //崩溃保存日志模式 默认为2，采用保存Web服务器
    private static final String CRASH_SAVE_SDPATH = StorageUtil.LOG_DIR; //崩溃日志SD卡保存路径
    private static final String CARSH_LOG_DELIVER = "服务器上传错误日志URL";
    private static CustomCrash instance = new CustomCrash();
    private Context mContext;

    private CustomCrash() {
    }

    /**
     * @return
     */
    public static CustomCrash getInstance() {
        return instance;
    }

    /*
     * (non-Javadoc) 进行重写捕捉异常
     *
     * @see
     * java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang
     * .Thread, java.lang.Throwable)
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (type_save == TYPE_SAVE_SDCARD) {
            // 1,保存信息到sdcard中
            saveToSdcard(mContext, ex);
        } else if (type_save == TYPE_SAVE_REMOTE) {
            // 2,异常崩溃信息投递到服务器
            saveToServer(mContext, ex);
        }
        // 3,应用准备退出
        showToast(mContext, "很抱歉,程序发生异常,即将退出.");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ActivityManager.getInstance().removeAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    /**
     * 设置自定异常处理类
     *
     * @param pContext
     */
    public void setCustomCrashInfo(Context pContext) {
        this.mContext = pContext;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 保存异常信息到sdcard中
     *
     * @param pContext
     * @param ex       异常信息对象
     */
    private void saveToSdcard(Context pContext, Throwable ex) {
        String fileName = null;
        StringBuffer sBuffer = new StringBuffer();
        // 添加异常信息
        sBuffer.append(getExceptionInfo(ex));
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file1 = new File(CRASH_SAVE_SDPATH);
            if (!file1.exists()) {
                file1.mkdir();
            }
            fileName = file1.toString() + File.separator + paserTime(System.currentTimeMillis()) + ".log";
            File file2 = new File(fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file2);
                fos.write(sBuffer.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进行把数据投递至服务器
     *
     * @param pContext
     * @param ex       崩溃异常
     */
    private void saveToServer(Context pContext, Throwable ex) {
        final String carsh_log = getExceptionInfo(ex);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("crash_log", carsh_log);
                // TODO: 2016/2/29 这里做服务器上传工作


            }
        }).start();
    }

    /**
     * 获取并且转化异常信息
     * 同时可以进行投递相关的设备，用户信息
     *
     * @param ex
     * @return 异常信息的字符串形式
     */
    private String getExceptionInfo(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("---------Crash Log Begin---------\n");
        //获取APP信息
        stringBuffer.append("系统版本：" + AppUtil.getVersionName(mContext) + "\n");
        //获取SDK信息
        stringBuffer.append("SDK版本：" + Build.VERSION.SDK_INT + "\n");
        //获取手机型号
        stringBuffer.append("手机型号：" + Build.MODEL + "\n");
        //获取异常信息
        stringBuffer.append("错误日志：" + sw.toString() + "\n");
        stringBuffer.append("---------Crash Log End---------\n");
        return stringBuffer.toString();
    }

    /**
     * 进行弹出框提示
     *
     * @param pContext
     * @param msg
     */
    private void showToast(final Context pContext, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(pContext, msg, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 将毫秒数转换成yyyy-MM-dd-HH-mm-ss的格式
     *
     * @param milliseconds
     * @return
     */
    private String paserTime(long milliseconds) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String times = format.format(new Date(milliseconds));

        return times;
    }
}