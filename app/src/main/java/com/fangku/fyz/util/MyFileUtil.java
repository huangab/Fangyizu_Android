package com.fangku.fyz.util;/**
 * Created by 67343 on 2016/8/14.
 */

import android.content.Context;

import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class MyFileUtil {
    private Bean_Huose_Add mBeanData = new Bean_Huose_Add();

    //写数据
    public static void writeFile(Context context, String fileName, Bean_Huose_Add beanData) throws IOException {

        FileOutputStream out = null;
        out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        out.write(JsonMananger.beanToJson(beanData).getBytes("UTF-8"));
        out.close();
    }

    //读数据
    public Bean_Huose_Add readFile(Context context,String fileName) throws IOException {
        String res = "";
        FileInputStream in = null;
        ByteArrayOutputStream bout = null;
        byte[] buf = new byte[1024];
        bout = new ByteArrayOutputStream();
        int length = 0;
        in = context.openFileInput(fileName); //获得输入流
        while ((length = in.read(buf)) != -1) {
            bout.write(buf, 0, length);
        }
        byte[] content = bout.toByteArray();
        res = new String(content, "UTF-8"); //设置文本框为读取的内容
        in.close();
        bout.close();


        mBeanData = JsonMananger.jsonToBean(res, Bean_Huose_Add.class);

        return mBeanData;

    }

    public void deleteFile(){
    }

}
