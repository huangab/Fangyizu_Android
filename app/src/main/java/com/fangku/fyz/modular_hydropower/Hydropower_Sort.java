package com.fangku.fyz.modular_hydropower;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 房间排序
 * Created by bowen.ye
 * Date: 2016/8/13
 * Time: 11:25
 */
public class Hydropower_Sort extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.lv_hydropower_sort_before)
    ListView mLvHydropowerSortBefore;
    @Bind(R.id.lv_hydropower_sort_after)
    ListView mLvHydropowerSortAfter;

    Context mContext = Hydropower_Sort.this;

    List<Bean_Hydropower.DataEntity> mList1 = new ArrayList<>();
    List<Bean_Hydropower.DataEntity> mList2 = new ArrayList<>();
    Adapter_Hydropower_Sort_left mAdapterLeft;
    Adapter_Hydropower_Sort_Right mAdapterRight;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Hydropower_Sort.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.hydropower_sort;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("房间排序");
        mTvTitleRight.setText("重新排序");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setTextColor(getResources().getColor(R.color.activation));

        mList1.addAll(Hydropower.beanHydropowersList);

        mAdapterRight = new Adapter_Hydropower_Sort_Right(this, mList2, mList1, mAdapterLeft);
        mAdapterLeft = new Adapter_Hydropower_Sort_left(this, mList1, mList2, mAdapterRight);
        mLvHydropowerSortBefore.setAdapter(mAdapterLeft);
        mAdapterRight = new Adapter_Hydropower_Sort_Right(this, mList2, mList1, mAdapterLeft);
        mLvHydropowerSortAfter.setAdapter(mAdapterRight);
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.tv_title_right, R.id.btn_hydropower_sort_keep})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title_right:
                Dialog_Show.showTwoButton(mContext, "提示", "是否重新排序", "否", "是", new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        switch (result) {
                            case "1":
                                break;
                            case "2":
                                mList1.clear();
                                mList2.clear();
                                Log.i("a", "isSuccess: " + Hydropower.beanHydropowersList.size());
                                mList1.addAll(Hydropower.beanHydropowersList);
                                mAdapterLeft.notifyDataSetChanged();
                                mAdapterRight.notifyDataSetChanged();
                                break;
                        }
                    }
                });
                break;
            case R.id.btn_hydropower_sort_keep:
                if (mList2 != null) {
                    if (mList2.size() == Hydropower.beanHydropowersList.size()) {
                        orderby();
                    } else {
                        ToastUtil.showShort(mContext, "请完成所有房间排序");
                    }
                }
                break;
        }
    }

    public void orderby() {
        for(int i=0;i<mList2.size();i++){
            mList2.get(i).setOrderBy(i);
        }

        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();
        map.put("meterjson", JsonMananger.beanToJson(mList2));
        httpUtil.doPost(Url_final.ORDERBY_HY, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                BaseResponse base = JsonMananger.jsonToBean(result, BaseResponse.class);
                if (base != null) {
                    if ("200".equals(base.getCode())) {
                        showOneButton(mContext, "提示", "排序成功", "返回抄表", new Only_CallBack() {
                            @Override
                            public void isSuccess(String result) {
                                Hydropower.beanHydropowersList.clear();
                                Hydropower.beanHydropowersList.addAll(mList2);
                                finish();
                            }
                        });
                    }else{
                        ToastUtil.showShort(mContext,base.getMessage());
                    }
                }else{
                    ToastUtil.showShort(mContext,"数据为空请重新访问");
                }
            }

            @Override
            public void onFailed(Exception e) {
                ToastUtil.showShort(mContext,"网络访问错误");
            }
        });
    }

    /**
     * 只有1个按钮的文本显示弹出框
     *
     * @param context
     * @param callBack
     */
    public void showOneButton(Context context, String title, String content, String buttontext, final Only_CallBack callBack) {
        BaseAnimatorSet bas_in = new BounceEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog//
                .btnNum(1)
                .title(title)
                .content(content)//
                .btnText(buttontext)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                callBack.isSuccess("");
            }
        });

    }
}
