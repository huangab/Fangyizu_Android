package com.fangku.fyz.modular_me.bankcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_me.adapter.Adapter_BankCard;
import com.fangku.fyz.modular_me.bean.Bean_Me_BankCard;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的银行卡
 * Created by 67343 on 2016/7/14.
 */
public class Me_BankCard extends MyBaseActivity {


    List<Bean_Me_BankCard.DataDataBean> mList = new ArrayList<>();

    Context mContext = Me_BankCard.this;
    @Bind(R.id.list_me_BandCard)
    ListView mListMeBandCard;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.tv_me_BankCard_AddCard)
    Button mTvMeBankCardAddCard;
    @Bind(R.id.imageView)
    ImageView mImageView;

    private Adapter_BankCard mAdapter;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_BankCard.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_bankcard;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mTvTitle.setText("我的银行卡");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setText("管理");
        mAdapter = new Adapter_BankCard(Me_BankCard.this, mList);
        mListMeBandCard.setAdapter(mAdapter);
    }


    @Override
    public void getData() {

    }

    PostUtil postUtil;

    @Override
    public void onResume() {
        super.onResume();

        mLoadingDialog.show(mContext);
        mList.clear();
        postUtil = new PostUtil(mContext);
        postUtil.Post_Bean(Url_final.GET_BANKSHOW)
                .execute(new BeanList_Callback<Bean_Me_BankCard>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_Me_BankCard response, String message) {
                        mList.addAll(response.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel500();

                    }

                });


    }

    @OnClick({R.id.btn_back, R.id.tv_title_right, R.id.tv_me_BankCard_AddCard})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title_right:
                showPopupWindow(mTvTitleRight);
                break;
            case R.id.tv_me_BankCard_AddCard:
//                Me_Add_BankCard.launch(Me_BankCard.this);
                break;
        }
    }

    /**
     * 右上角弹出框
     *
     * @param view
     */
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.pop_bank_setting, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        // 设置按钮的点击事件
        LinearLayout mLlWater = (LinearLayout) contentView.findViewById(R.id.ll_pop_hydropower_water);
        LinearLayout mLlElectricity = (LinearLayout) contentView.findViewById(R.id.ll_pop_hydropower_electricity);


        mLlWater.setOnClickListener(v -> {
            Me_Add_BankCard.launch(Me_BankCard.this);

            popupWindow.dismiss();
        });
        mLlElectricity.setOnClickListener(v -> popupWindow.dismiss());

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor((v, event) -> {

            LogUtil.i("mengdd", "onTouch : ");

            return false;
            // 这里如果返回true的话，touch事件将被拦截
            // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        ColorDrawable dw = new ColorDrawable(0x00000);
        popupWindow.setBackgroundDrawable(dw);

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postUtil.Cancel();
    }
}
