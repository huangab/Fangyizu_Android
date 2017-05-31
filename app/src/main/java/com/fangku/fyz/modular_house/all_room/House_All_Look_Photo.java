package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.modular_me.real.Me_Real;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by   jie.wang
 * Date: 2016/8/25
 * Time: 10:54
 */
public class House_All_Look_Photo extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.rl_title_top)
    RelativeLayout mRlTitleTop;
    @Bind(R.id.iv_heyue_look_photo)
    ImageView mIvHeyueLookPhoto;

    PhotoViewAttacher mAttacher;


    private String mtype;

    public static void launch(Context mContext, String shenfenzheng) {
        Intent mIntent = new Intent(mContext, House_All_Look_Photo.class);
        mIntent.putExtra("type", shenfenzheng);


        mContext.startActivity(mIntent);

    }

    @Override
    public int bindLayout() {
        return R.layout.common_look_photo;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("查看大图");
        mImageView.setVisibility(View.GONE);
        Intent intent = getIntent();
        mtype = intent.getStringExtra("type");

        if (mtype.equals("正")) {
            if (Me_Real.front != null) {
                mIvHeyueLookPhoto.setImageBitmap(Me_Real.front);
            }
        } else {
            if (Me_Real.back != null) {
                mIvHeyueLookPhoto.setImageBitmap(Me_Real.back);
            }
        }

        mAttacher = new PhotoViewAttacher(mIvHeyueLookPhoto);

    }

    @Override
    public void getData() {

    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
