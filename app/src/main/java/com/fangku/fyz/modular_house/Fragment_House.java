package com.fangku.fyz.modular_house;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.widget.CircularAnim;
import com.fangku.commonlibrary.widget.dialog.LoadingDialog;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Login;
import com.fangku.fyz.modular_house.adapter.Adapter_house_list;
import com.fangku.fyz.modular_house.all_room.House_All_Main;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;
import com.fangku.fyz.modular_house.dialog.Dialog_Add_House;
import com.fangku.fyz.util.MyBaseFragment;
import com.fangku.fyz.widget.Dialog_Show;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主页
 * Created by bowen.ye
 * Date: 2016/7/15
 * Time: 15:57
 */
public class Fragment_House extends MyBaseFragment {


    @Bind(R.id.tv_house_title_text)
    TextView mTvHouseTitleText;
    @Bind(R.id.lv_house_ListView)
    ListView mLvHouseListView;
    @Bind(R.id.lv_house_sv)
    SpringView mLvHouseSv;
    @Bind(R.id.iv_house_left)
    ImageView mIvHouseLeft;
    @Bind(R.id.tv_house_left)
    TextView mTvHouseLeft;
    @Bind(R.id.view_house_left)
    View mViewHouseLeft;
    @Bind(R.id.iv_house_right)
    ImageView mIvHouseRight;
    @Bind(R.id.tv_house_right)
    TextView mTvHouseRight;
    @Bind(R.id.view_house_right)
    View mViewHouseRight;
    @Bind(R.id.tv_house_not_room)
    TextView mTvHouseNotRoom;

    public static TextView mTvtHouseMessage;// 承载
    public static boolean isMessage;  //是否有消息进来
    public static String Message = "暂无消息";//用于存放实时的新系统信息
    public static final int send_msg_code = 0x161;//handle的code


    private final int GET_2WEIMA = 0x121;      //扫面二维码回调


    private List<Bean_House_Type_Room.DataBean> data = new ArrayList<>();
    public static List<Bean_House_Type_Room.DataBean> leftData = new ArrayList<>();
    public static List<Bean_House_Type_Room.DataBean> rightData = new ArrayList<>();
    private Adapter_house_list mAdapter;

    private Fragment_House_helper helper;//帮助类  减少繁琐的代码


    public static String houseID = "";//房产ID

    LoadingDialog mLoadingDialog;


    public static boolean isUpdate = false;//是否需要更新


    @Override
    public int bindLayout() {
        return R.layout.fragment_house;
    }

    @Override
    public void createFragment(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mLoadingDialog = new LoadingDialog();

        mTvtHouseMessage = (TextView) mView.findViewById(R.id.tv_house_message);

        initListView();

        helper = new Fragment_House_helper(mActivity, mTvHouseLeft, mTvHouseRight);


    }


    @Override
    public void getData() {

        /**
         * 先查询房屋名字 然后获取第一个房屋名字的ID  查询未交租的房间
         */
        mLoadingDialog.show(mActivity);
        Static_Login.updateHouseName(mActivity, result -> {//获取房屋名字列表
            LogUtil.i("返回了什么", "getData: " + result);
            if (result.equals("成功")) {
                setColor("左");
                mTvHouseTitleText.setText(MyApplication.stringItems[0]);
                houseID = MyApplication.houseSysID[0];
                mLoadingDialog.cancel();
                mLvHouseSv.callFresh();


            } else {
                mTvHouseTitleText.setText("我的房屋");
                houseID = "";
                mLvHouseSv.callFresh();
                mTvHouseNotRoom.setVisibility(View.VISIBLE);
                mLoadingDialog.cancel();


            }
        });


    }


    @OnClick({R.id.iv_house_sweep, R.id.iv_house_add, R.id.tv_house_message, R.id.ll_house_number,
            R.id.ll_house_nopay, R.id.ll_house_nohire, R.id.ll_title_house})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_title_house: //title标题
                setTitle();

                break;
            case R.id.iv_house_sweep://二维码

//                CircularAnim.fullActivity(mActivity, mActivity.findViewById(R.id.iv_house_sweep))
//                        .go(() ->{} );
                startActivityForResult(new Intent(mActivity, CaptureActivity.class), GET_2WEIMA);

                break;
            case R.id.iv_house_add://添加房屋
                CircularAnim.show(mIvHouseRight).go();
                new Dialog_Add_House(mActivity).builder().show();
                break;

            case R.id.tv_house_message:   //消息
                mTvtHouseMessage.setVisibility(View.GONE);
                Fragment_House.isMessage = false;
                //显示一个按钮的弹出框
                Dialog_Show.showOneButton(mActivity, "消息预览", Message,
                        "确定", result -> {
                        });

                break;
            case R.id.ll_house_number://所有房间
                House_All_Main.lanuch(mActivity, houseID, mTvHouseTitleText.getText().toString());

                break;
            case R.id.ll_house_nopay:   //未交租
                setColor("左");
                data.clear();
                data.addAll(leftData);

                mAdapter.notifyDataSetChanged();

                break;
            case R.id.ll_house_nohire://未出租
                setColor("右");
                data.clear();
                data.addAll(rightData);

                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    //获取房产列表
    private void setTitle() {
        if (MyApplication.stringItems.length == 0) {
            ToastUtil.showShort(mActivity, "请先添加一套房产.");
            return;
        }

        Dialog_Show.showList(mActivity, result -> {
                    mTvHouseTitleText.setText(MyApplication.stringItems[Integer.valueOf(result)]);//顶部选择房屋弹出框
                    houseID = MyApplication.houseSysID[Integer.valueOf(result)];


                    mLvHouseSv.callFresh();


                }

        );


    }


    //初始化可以上拉下拉的listview
    private void initListView() {

        mAdapter = new Adapter_house_list(mActivity, houseID, mTvHouseTitleText.getText().toString(), data);
        mLvHouseListView.setAdapter(mAdapter);
        mLvHouseSv.setType(SpringView.Type.FOLLOW);

        mLvHouseSv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                if (MyApplication.houseSysID.length != 0) {
                    data.clear();
                    helper.getDataLeft(result -> {

                        data.addAll(leftData);
                        setColor("左");
                        mAdapter.notifyDataSetChanged();
                        mLvHouseSv.onFinishFreshAndLoad();


                    });
                    helper.getDataRight(result2 -> {


                    });

                } else {
                    data.clear();
                    mLvHouseSv.onFinishFreshAndLoad();
                    mAdapter.notifyDataSetChanged();
                    ToastUtil.showShort(mActivity, "您还没有创建房产");
                }
            }


            @Override
            public void onLoadmore() {

                new Handler().postDelayed(() -> {
                    ToastUtil.showShort(mActivity, "已经没有数据了!");
                    mLvHouseSv.onFinishFreshAndLoad();
                }, 1000);

            }
        });

        mLvHouseSv.setHeader(new DefaultHeader(getContext()));
        mLvHouseSv.setFooter(new DefaultFooter(getContext()));

    }


    //设置点击未交租和未出租的颜色
    private void setColor(String a) {
        mIvHouseLeft.setImageResource(R.mipmap.house_money_dark);
        mTvHouseLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.no_activation));
        mViewHouseLeft.setBackgroundResource(R.color.no_activation);
        mIvHouseRight.setImageResource(R.mipmap.house_house_dark);
        mTvHouseRight.setTextColor(ContextCompat.getColor(mActivity, R.color.no_activation));

        mViewHouseRight.setBackgroundResource(R.color.no_activation);

        if (a.equals("左")) {
            mIvHouseLeft.setImageResource(R.mipmap.house_money_light);
            mTvHouseLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.activation));
            mViewHouseLeft.setBackgroundResource(R.color.activation);
        } else {
            mIvHouseRight.setImageResource(R.mipmap.house_house_light);
            mTvHouseRight.setTextColor(ContextCompat.getColor(mActivity, R.color.activation));
            mViewHouseRight.setBackgroundResource(R.color.activation);
        }
        if (data.size() == 0 || data == null) {
            mTvHouseNotRoom.setVisibility(View.VISIBLE);
        } else {
            mTvHouseNotRoom.setVisibility(View.GONE);
        }
    }


    //用于实时更新接收到的新消息显示
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            if (what == send_msg_code) {


                if (mTvtHouseMessage != null) {
                    if (isMessage) {

                        mTvtHouseMessage.setVisibility(View.VISIBLE);

                    } else {
                        mTvtHouseMessage.setVisibility(View.GONE);

                    }
                }
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        if (isMessage) {
            mTvtHouseMessage.setVisibility(View.VISIBLE);

        } else {
            mTvtHouseMessage.setVisibility(View.GONE);

        }

        if (isUpdate) {
            isUpdate = false;
            getData();
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_2WEIMA) {


        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        leftData.clear();
        rightData.clear();
        mTvtHouseMessage = null;
        System.gc();


    }
}
