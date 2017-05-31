package com.fangku.fyz.modular_me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.utils.imageutil.ImageUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.modular_me.balance.Me_Balance;
import com.fangku.fyz.modular_me.bankcard.Me_BankCard;
import com.fangku.fyz.modular_me.message.Me_Message_Center;
import com.fangku.fyz.modular_me.real.Me_Real;
import com.fangku.fyz.modular_me.setting.Me_Setting;
import com.fangku.fyz.util.MyBaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的模块主界面
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Fragment_Me extends MyBaseFragment {
    @Bind(R.id.tv_title_me)
    TextView mTvTitleMe;
    @Bind(R.id.rl_title_top_me)
    LinearLayout mRlTitleTopme;
    @Bind(R.id.tv_me_showname)
    TextView mTvMeShowname;
    @Bind(R.id.iv_me_vip_img)
    ImageView mIvMeVipImg;
    @Bind(R.id.tv_me_vip_text)
    TextView mTvMeVipText;
    @Bind(R.id.tv_me_showid)
    TextView mTvMeShowid;
    @Bind(R.id.tv_me_gps_sheng)
    TextView mTvMeGpsSheng;
    @Bind(R.id.tv_me_gps_shi)
    TextView mTvMeGpsShi;
    @Bind(R.id.ll_me_information)
    LinearLayout mLlMeInformation;
    @Bind(R.id.ll_me_usermoney)
    LinearLayout mLlMeUsermoney;
    @Bind(R.id.ll_me_mybank_card)
    LinearLayout mLlMeMybankCard;
    @Bind(R.id.ll_me_cardbag)
    LinearLayout mLlMeCardbag;
    @Bind(R.id.ll_me_contact)
    LinearLayout mLlMeContact;
    @Bind(R.id.ll_me_setting)
    LinearLayout mLlMeSetting;
    @Bind(R.id.ll_me_real)
    LinearLayout mLlMeReal;
    @Bind(R.id.iv_me_head_img)
    ImageView mIvMeHeadImg;
    @Bind(R.id.ll_me_messages)
    LinearLayout mLlMeMessages;

    @Override
    public int bindLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void createFragment(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.ll_me_real, R.id.ll_me_messages, R.id.tv_me_showid, R.id.ll_me_information, R.id.ll_me_usermoney, R.id.ll_me_mybank_card, R.id.ll_me_cardbag, R.id.ll_me_contact, R.id.ll_me_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_me_messages:
                Me_Message_Center.launch(mActivity);
                break;
            case R.id.ll_me_information:
                Me_information.launch(mActivity);
                break;
            case R.id.ll_me_usermoney:
                Me_Balance.launch(mActivity);
                break;
            case R.id.ll_me_mybank_card:
                Me_BankCard.launch(mActivity);
                break;
            case R.id.ll_me_cardbag:
                Me_Cardpackage.launch(mActivity);
                break;
            case R.id.ll_me_contact:

                Me_Contact.launch(mActivity);
                break;
            case R.id.ll_me_setting:
                Me_Setting.launch(mActivity);
                break;
            case R.id.ll_me_real:
                Me_Real.launch(mActivity);

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();


        if (Static_Conmom.IsUpdate_Head == true) {
            String head = UserDataUtil.getUserInfo().getData().getImageId();
            ImageUtil.loadSmallImageCircle(head, mIvMeHeadImg);
            mTvMeShowname.setText(UserDataUtil.getUserInfo().getData().getName());
            Static_Conmom.IsUpdate_Head = false;
        }


    }


}
