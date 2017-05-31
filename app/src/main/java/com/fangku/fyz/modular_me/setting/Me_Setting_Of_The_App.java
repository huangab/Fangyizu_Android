package com.fangku.fyz.modular_me.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by   jie.wang
 * Date: 2016/8/11
 * Time: 10:07
 */
public class Me_Setting_Of_The_App extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.imageView)
    ImageView mImageView;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Setting_Of_The_App.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_setting_of_the_app;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mTvTitle.setText("关于");
        mImageView.setVisibility(View.GONE);



    }

    @Override
    public void getData() {

    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }

// 架包里的popwindows实现方法
//                View inflate = View.inflate(Me_Setting_Of_The_App.this, R.layout.pop_double, null);
//                bubblePopup = new BubblePopup(Me_Setting_Of_The_App.this, inflate);
//                bubblePopup.anchorView(mOf12w33)//控件
//                        .gravity(Gravity.BOTTOM)
//                        .showAnim(new SlideTopEnter())
//                        .autoDismiss(false)//是否自动消失
//                        .show();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
