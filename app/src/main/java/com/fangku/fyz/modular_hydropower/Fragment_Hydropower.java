package com.fangku.fyz.modular_hydropower;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.MyBaseFragment;
import com.fangku.fyz.widget.PieChartView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 水电  (抄表)  主界面
 * Created by   bowen.ye
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Fragment_Hydropower extends MyBaseFragment {
    @Bind(R.id.rd_hydropower_left)
    RadioButton mRdHydropowerLeft;
    @Bind(R.id.rd_hydropower_right)
    RadioButton mRdHydropowerRight;
    @Bind(R.id.pie_chart)
    PieChartView mPieChart;
    @Bind(R.id.riv_round)
    RoundedImageView mRivRound;
    @Bind(R.id.view_show)
    FrameLayout mViewShow;


    @Override
    public int bindLayout() {
        return R.layout.fragment_hydropower;
    }

    @Override
    public void createFragment(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        List<PieChartView.PieceDataHolder> pieceDataHolders = new ArrayList<>();


        pieceDataHolders.add(new PieChartView.PieceDataHolder(3000, Color.parseColor(Static_Conmom.pic_2), "水费:2000"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(1800, Color.GREEN, "管理费:10"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(7000, Color.YELLOW, "电费:2000"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(12000, Color.parseColor(Static_Conmom.pic_1), "租金:5200"));
        mPieChart.setData(pieceDataHolders);


        Animation operatingAnim = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_enter);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        mViewShow.setAnimation(operatingAnim);

    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.tv_title_caobiao_left,R.id.tv_title_caobiao_right, R.id.rd_hydropower_left, R.id.rd_hydropower_right, R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3, R.id.ll_tab4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_caobiao_left:
                Hydropower.launch(mActivity);
                break;
            case R.id.tv_title_caobiao_right:

                PopupMenu popupMenu=new PopupMenu(mActivity,mActivity.findViewById(R.id.tv_title_caobiao_right));
                //从xml文件中加载菜单到popupMenu中
                popupMenu.inflate(R.menu.a);

                //显示  popupMenu
                popupMenu.show();

                break;


            case R.id.rd_hydropower_left:
                if (mRdHydropowerLeft.isChecked()) {
                    mRdHydropowerLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.background_color));
                    mRdHydropowerRight.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                }
                break;
            case R.id.rd_hydropower_right:
                if (mRdHydropowerRight.isChecked()) {
                    mRdHydropowerRight.setTextColor(ContextCompat.getColor(mActivity, R.color.background_color));
                    mRdHydropowerLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                }
                break;
            case R.id.ll_tab1:
                ToastUtil.showShort(mActivity, "开发中...");
                break;
            case R.id.ll_tab2:
                ToastUtil.showShort(mActivity, "开发中...");
                break;
            case R.id.ll_tab3:
                ToastUtil.showShort(mActivity, "开发中...");
                break;
            case R.id.ll_tab4:
                ToastUtil.showShort(mActivity, "开发中...");
                break;
        }
    }

    private Handler mHandler = new Handler();

    @Override
    public void onResume() {
        super.onResume();


    }


}
