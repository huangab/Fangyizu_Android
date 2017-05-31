package com.fangku.fyz.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;

/**
 * 全局定时器
 * Created by   jie.wang
 * Date: 2016/8/18
 * Time: 15:42
 */
public class TimeCount extends CountDownTimer {

    private TextView mTextView;

    public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        mTextView = textView;
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新发送");
        mTextView.setClickable(true);
        mTextView.setBackgroundResource(R.drawable.fillet_background);
        if (MyApplication.time_num / 1000 == 1) {
            MyApplication.time_num = Static_Conmom.TIME_SIZE * 1000;
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        mTextView.setBackgroundResource(R.drawable.fillet_gray);
        mTextView.setText("" + millisUntilFinished / 1000 + "秒");
        MyApplication.time_num = (int) millisUntilFinished;

    }

}
