package com.fangku.commonlibrary.global;

import android.content.Context;

/**
 * 全局类
 * Created by chenwei.li
 * Date: 2016/1/29
 * Time: 23:26
 */
public class CommonGlobal {

	private static Context applicationContext;

	public static Context getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(Context ctx) {
		applicationContext = ctx;
	}
}
