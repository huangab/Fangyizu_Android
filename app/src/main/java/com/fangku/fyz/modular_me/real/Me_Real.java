package com.fangku.fyz.modular_me.real;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.all_room.House_All_Look_Photo;
import com.fangku.fyz.modular_me.AlphaFilter;
import com.fangku.fyz.util.MyBaseActivity;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 身份认证
 * Created by   jie.wang
 * Date: 2016/7/15
 * Time: 15:21
 */
public class Me_Real extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.ll_me_real_takephoto)
    LinearLayout mLlMeRealTakephoto;
    @Bind(R.id.iv_real_front)
    ImageView mIvRealFront;
    @Bind(R.id.iv_real_back)
    ImageView mIvRealBack;
    @Bind(R.id.et_real_num)
    EditText mEtRealNum;
    @Bind(R.id.et_real_name)
    EditText mEtRealName;
    @Bind(R.id.rb_me_real_man)
    RadioButton mRbMeRealMan;
    @Bind(R.id.rb_me_real_woman)
    RadioButton mRbMeRealWoman;
    @Bind(R.id.rg_me_real_father)
    RadioGroup mRgMeRealFather;
    @Bind(R.id.et_real_birthday)
    EditText mEtRealBirthday;
    @Bind(R.id.et_real_dizhi)
    EditText mEtRealDizhi;
    @Bind(R.id.et_real_gonganju)
    EditText mEtRealGonganju;
    @Bind(R.id.et_real_time)
    EditText mEtRealTime;
    @Bind(R.id.bt_me_real_ok)
    Button mBtMeRealOk;

    public static Bitmap front;
    public static Bitmap back;

    AlphaFilter alphaFilter = new AlphaFilter();

    boolean is;  //true为正面,false为反面

    Bitmap b;   //用于显示占位图
    @Bind(R.id.sv_real)
    ScrollView mSvReal;


    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Real.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_real;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("身份认证");
        mSvReal.smoothScrollTo(0, 0);
        b = BitmapFactory.decodeResource(getResources(), R.mipmap.shenfengzheng_zhengmian);
        mIvRealFront.setImageBitmap(b);
        b = BitmapFactory.decodeResource(getResources(), R.mipmap.shenfenzheng_fangmian);
        mIvRealBack.setImageBitmap(b);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (front != null) {
            mIvRealFront.setImageBitmap(front);
//            new BitmapFilter().execute(front, b);
        }
        if (back != null) {
            mIvRealBack.setImageBitmap(back);
//            new BitmapFilter().execute(back, b);
        }

    }

    private static final int LOCATION_PERMISSION_REQUEST_ID = 1;

    @OnClick({R.id.ll_me_real_takephoto, R.id.btn_back, R.id.iv_real_front, R.id.iv_real_back, R.id.bt_me_real_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.ll_me_real_takephoto:
                takePhoto();
                break;
            case R.id.iv_real_front:


                House_All_Look_Photo.launch(mContext, "正");
                break;
            case R.id.iv_real_back:
                House_All_Look_Photo.launch(mContext, "反");
                break;
            case R.id.bt_me_real_ok:
                break;
        }
    }



    class BitmapFilter extends AsyncTask<Bitmap, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Bitmap... params) {
            return alphaFilter.overlay(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (is) {
                mIvRealFront.setImageBitmap(result);
            } else {
                mIvRealBack.setImageBitmap(result);
            }
        }
    }

    /**
     * 选择拍照弹出框
     */
    public void takePhoto() {
        final String[] stringItems = {"拍摄正面", "拍摄背面"};
        final ActionSheetDialog dialog = new ActionSheetDialog(Me_Real.this, stringItems, null);
        dialog.title("请选择正反面")//
                .titleTextSize_SP(14.5f)//
                .itemHeight(55)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //正面
                        Me_Real_Front.launch(Me_Real.this);
                        is = true;
                        break;
                    case 1://背面
                        Me_Real_Back.launch(Me_Real.this);
                        is = false;
                        break;

                }
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        back = null;
        front = null;
        System.gc();
    }
}
