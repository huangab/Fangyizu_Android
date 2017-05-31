package com.fangku.fyz.modular_me.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 意见反馈
 * Created by   jie.wang
 * Date: 2016/8/14
 * Time: 17:47
 */
public class Me_Tell_Me extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_me_setting_tell_title)
    EditText mEtTitle;
    @Bind(R.id.et_me_setting_tell_content)
    EditText mEtContent;


    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Tell_Me.class);
        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.me_setting_tell_me;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("意见反馈");

    }

    @Override
    public void getData() {


    }


    public void addFeedback() {

        mLoadingDialog.show(mContext);
        Map<String, String> map = new HashMap<>();
        map.put("title", mEtTitle.getText().toString().trim());
        map.put("msgContent", mEtContent.getText().toString().trim());

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ADD_FEEDBACK, map)
                .execute(new Bean_Callback<BeanResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });


    }


    @OnClick({R.id.btn_back, R.id.bt_to_fankui, R.id.tv_yijianfankui_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_to_fankui:
                addFeedback();
                break;
            case R.id.tv_yijianfankui_type:
                ToastUtil.showShort(mContext, "开发中....");
                break;
        }
    }


}
