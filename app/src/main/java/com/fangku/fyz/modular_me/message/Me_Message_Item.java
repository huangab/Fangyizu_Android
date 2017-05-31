package com.fangku.fyz.modular_me.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 消息详情
 * Created by   jie.wang
 * Date: 2016/8/22
 * Time: 19:33
 */
public class Me_Message_Item extends MyBaseActivity {


    String mName;
    String mTitle;
    String mDate;
    String mTime;
    String mText;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_mesage_item_title)
    TextView mTvMesageItemTitle;
    @Bind(R.id.tv_mesage_item_name)
    TextView mTvMesageItemName;
    @Bind(R.id.tv_mesage_item_time)
    TextView mTvMesageItemTime;
    @Bind(R.id.tv_mesage_item_text)
    TextView mTvMesageItemText;

    @Override
    public int bindLayout() {
        return R.layout.me_message_text;
    }


    public static void launch(Context mContext, String name, String title, String date, String time, String text) {
        Intent mIntent = new Intent(mContext, Me_Message_Item.class);
        mIntent.putExtra("name", name);
        mIntent.putExtra("title", title);
        mIntent.putExtra("date", date);
        mIntent.putExtra("time", time);
        mIntent.putExtra("text", text);


        mContext.startActivity(mIntent);

    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mTime = intent.getStringExtra("time");
        mText = intent.getStringExtra("text");
        mTvMesageItemName.setText(mName);
        mTvTitle.setText("详情");
        mTvMesageItemTitle.setText(mTitle);
        mTvMesageItemText.setText(mText);
        mTvMesageItemTime.setText(mDate + "  " + mTime);


    }

    @Override
    public void getData() {


    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
