package com.fangku.commonlibrary.utils.security;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @author bangwei.yang
 * @version 1.0
 * 
 */
public class MD5 {

	/**
	 * 对数据进行MD5加密
	 * 
	 * @param data
	 *            明文
	 * @return
	 */
	public static String encrypt(String data) {
		// 返回结果
		String result = "";
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(data.getBytes("UTF-8"));
			byte[] array = md.digest();

			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				if (Integer.toHexString(0xFF & array[i]).length() == 1) {
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & array[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & array[i]));
				}
			}
			// 16位加密，从第9位到25位
			result = md5StrBuff.substring(8, 24).toString().toUpperCase();
		} catch (Exception e) {
			Log.e("MD5", "加密失败，原因：" + e.getMessage());
		}

		return TextUtils.isEmpty(result) ? data : result;
	}

	/**
	 * 对数据进行MD5加密
	 * 
	 * @param data
	 *            明文
	 * @return
	 */
	public static String md5(String data) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					data.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString().toUpperCase();
	}
}
