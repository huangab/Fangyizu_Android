package com.fangku.commonlibrary.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fangku.commonlibrary.R;


/**
 * 自定义等待框
 * Created by   jie.wang
 * Date: 2016/9/29
 * Time: 15:49
 */
public class LoadingDialog {


    private ProgressDialog mProgressDialog;


    /**
     * 显示对话框
     *
     * @param context
     */
    public void show(Context context) {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
        mProgressDialog = new ProgressDialog(context, R.style.LodingDialog);
        mProgressDialog.setCanceledOnTouchOutside(false);//false:dialog弹出后 点击屏幕，dialog不消失；点击物理返回键dialog消失
        mProgressDialog.setCancelable(true);//true:dialog弹出后 点击屏幕或物理返回键，dialog会消失
        mProgressDialog.show();
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_progress,
                null);
        mProgressDialog.setContentView(v);


    }

    /**
     * 显示对话框，自定义文字
     *
     * @param context
     * @param msg
     */
    public void show(Context context, String msg) {
        mProgressDialog = new ProgressDialog(context, R.style.LodingDialog);
        mProgressDialog.setCanceledOnTouchOutside(false);//false:dialog弹出后 点击屏幕，dialog不消失；点击物理返回键dialog消失
        mProgressDialog.setCancelable(true);//true:dialog弹出后 点击屏幕或物理返回键，dialog会消失
        mProgressDialog.show();

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_progress,
                null);
        TextView txvMessage = (TextView) v
                .findViewById(R.id.txv_dialog_progress_content);

//        mProgressDialog.setContentView(v);
        txvMessage.setText(msg);

        mProgressDialog.setContentView(v);

    }


    /**
     * 关闭对话框
     */
    public void cancel() {
        if (mProgressDialog != null) {
//                    handler.removeCallbacks(runnable);//销毁的时候移除线程
            mProgressDialog.dismiss();

        }
    }

    /**
     * 延迟0.5秒关闭对话框 失效中
     */
    public void cancel500() {
        if (mProgressDialog != null) {

            mProgressDialog.dismiss();

        }
    }


    /**
     * 设为不可取消  true为可取消  false为不可取消
     */
    public void back_able(boolean b) {
        if (mProgressDialog != null) {
            mProgressDialog.setCancelable(b);//不可点击取消
        }
    }


}
