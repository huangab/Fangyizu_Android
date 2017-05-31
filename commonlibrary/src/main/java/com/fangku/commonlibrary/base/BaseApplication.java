package com.fangku.commonlibrary.base;

import android.app.Application;

import com.fangku.commonlibrary.global.CommonGlobal;
import com.fangku.commonlibrary.utils.StorageUtil;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;


/**
 * 基类Application
 * Created by chenwei.li
 * Date: 2016/3/16
 * Time: 9:55
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置上下文对象
        CommonGlobal.setApplicationContext(this);

        //创建项目所需文件夹
        StorageUtil.createDir(this);


        //配置Okhttp,持久化cookie // 旧版cookie包
//        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        //TODO: 2016/10/9  新尝试的cookie包
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar)
                .build();

        //初始化okhttp实例
        OkHttpUtils.initClient(okHttpClient);

        //全局异常捕获(开发模式下不开启)
//        CustomCrash.getInstance().setCustomCrashInfo(this);


    }
}
