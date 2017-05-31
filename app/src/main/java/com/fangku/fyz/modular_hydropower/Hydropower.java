package com.fangku.fyz.modular_hydropower;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.NetUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Hydropower;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.sql.sql_hy;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.NormalDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 抄表界面
 * Created by bowen.ye
 * Date: 2016/8/12
 * Time: 9:07
 */
public class Hydropower extends MyBaseActivity implements View.OnClickListener {
    @Bind(R.id.ll_keyboard)
    LinearLayout mLlKeyboard;
    @Bind(R.id.tv_keyboard_show)
    EditText mTvKeyboardShow;
    @Bind(R.id.tv_keyboard_time)
    TextView mTvKeyboardTime;
    @Bind(R.id.tv_keyboard_flashlight)
    TextView mTvKeyboardFlashlight;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.view_hydropower)
    LinearLayout mViewHydropower;
    @Bind(R.id.tv_keyboard_last)
    TextView mTvKeyboardLast;
    @Bind(R.id.tv_keyboard_next)
    TextView mTvKeyboardNext;
    @Bind(R.id.ll_keyboard_finish)
    LinearLayout mLlKeyboardFinish;
    @Bind(R.id.iv_right_xiala)
    ImageView mIvRightXiala;

    //当前日期
    String date;
    //设置年月日
    int mYear = 1980, mMonth = 0, mDay = 1;
    //表类型
    String meterType = "E";
    //储存当前信息
    static public BCI bci;
    static String LastData;
    static double LastSc;
    static String show = "0";
    static int position = 0;
    static List<Bean_Hydropower.DataEntity> beanHydropowersList = new ArrayList<>();//真实数据
    static Adapter_Hydropower_list mAdpterHydropowerlist;
    static ListView mLsListView;
    //设置相机
    boolean isopent;
    Camera camera;

    sql_hy hy;
    Context mContext = Hydropower.this;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Hydropower.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.hydropower;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mLsListView = (ListView) findViewById(R.id.ls_ListView);
        //数据进行初始化
        mTvKeyboardShow.setInputType(InputType.TYPE_NULL);
        mLlKeyboard.setVisibility(View.GONE);
        mIvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setVisibility(View.VISIBLE);
        mIvRightXiala.setVisibility(View.VISIBLE);

        if (MyApplication.stringItems.length == 0) {
            mTvTitle.setText("我的房屋");
        } else {
            mTvTitle.setText(MyApplication.stringItems[0]);
        }
        mTvTitleRight.setText("电表");

        //获取当前日期
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.MONTH,-1);
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(c.getTimeInMillis()));
        mYear = Integer.parseInt(date.substring(0, 4));
        mMonth = Integer.parseInt(date.substring(5, 7)) - 1;
        mDay = Integer.parseInt(date.substring(8, 10));
        mTvKeyboardTime.setText(date);

        //listview添加适配器
        mAdpterHydropowerlist = new Adapter_Hydropower_list(this, beanHydropowersList, mLlKeyboard, mTvKeyboardShow, mViewHydropower, date);
        mLsListView.addFooterView(getLayoutInflater().inflate(R.layout.footer_hydropower, null));
        mLsListView.setAdapter(mAdpterHydropowerlist);

        //动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Static_Hydropower.ACTION_UPDATEUI);
        bci = new BCI();
        this.registerReceiver(bci, filter);

        hy = new sql_hy(mContext);

    }

    @Override
    public void getData() {
        mLoadingDialog.show(mContext);
        //当上次有数据没有进行保存时先进行保存炒作
        Boolean iskeep = false;
        int size = hy.mQuery().size();
        for (int i = 0; i < size; i++) {
            if (hy.mQuery().get(i).isRevise != null) {
                iskeep = true;
            }
        }
        //对网络进行判断没有网络读取本地数据库
        if (iskeep) {
            saveMeter();
        } else {
            queryNewScalebyHouseId();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdpterHydropowerlist.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_title_right, R.id.btn_hydropower_keep, R.id.tv_title, R.id.btn_back, R.id.iv_title_right, R.id.ll_keyboard_finish, R.id.tv_keyboard_time, R.id.tv_keyboard_flashlight, R.id.tv_keyboard_sort, R.id.tv_keyboard_1, R.id.tv_keyboard_4, R.id.tv_keyboard_7, R.id.tv_keyboard_drop, R.id.tv_keyboard_2, R.id.tv_keyboard_5, R.id.tv_keyboard_8, R.id.tv_keyboard_0, R.id.tv_keyboard_3, R.id.tv_keyboard_6, R.id.tv_keyboard_9, R.id.iv_keyboard_clear, R.id.tv_keyboard_last, R.id.tv_keyboard_next})
    public void onClick(View view) {
        switch (view.getId()) {
            //水电表切换
            case R.id.tv_title_right:
                showPopupWindow(mTvTitleRight);
                break;
            case R.id.btn_hydropower_keep:
                //对网络进行判断没有网络读取本地数据库
                Log.i("A", "onClick: " + (NetUtil.isConnected(mContext) || NetUtil.isWifi(mContext)));
                if (NetUtil.isConnected(mContext) || NetUtil.isWifi(mContext)) {
                    saveMeter();
                } else {
                    //当有数据改变时进行本地数据库保存操作
                    boolean a = false;
                    for (int i = 0; i < beanHydropowersList.size(); i++) {
                        if (beanHydropowersList.get(i).isRevise != null) {
                            hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                            a = true;
                        }
                    }
                    if (a) {
                        ToastUtil.showShort(mContext, "保存本地成功,有网络时请再次保存");
                    }
                }
                break;
            //地点选择弹出框
            case R.id.tv_title:
                Dialog_Show.showList(Hydropower.this, new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        mTvTitle.setText(MyApplication.stringItems[Integer.valueOf(result)]);
                        Fragment_House.houseID = MyApplication.houseSysID[Integer.valueOf(result)];
                        mLoadingDialog.show(mContext);
                        queryNewScalebyHouseId();
                    }
                });
                break;
            //返回按钮判断是否有数据修改
            case R.id.btn_back:
                boolean iskeep = false;
                for (int i = 0; i < beanHydropowersList.size(); i++) {
                    if (beanHydropowersList.get(i).isRevise != null) {
                        hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                    }
                }
                for (int i = 0; i < hy.mQuery().size(); i++) {
                    if (hy.mQuery().get(i).isRevise != null) {
                        iskeep = true;
                    }
                }
                if (iskeep) {
                    a();
                } else {
                    finish();
                }
                break;
            //地点选择弹出框
            case R.id.iv_title_right:
                Dialog_Show.showList(Hydropower.this, result -> mTvTitle.setText(MyApplication.stringItems[Integer.valueOf(result)]));
                break;
            //隐藏键盘
            case R.id.ll_keyboard_finish:
                if (beanHydropowersList.get(position).getThisScale() < beanHydropowersList.get(position).getLastScale() && beanHydropowersList.get(position).isInput == null && beanHydropowersList.get(Hydropower.position).isRevise != null) {
                    contrast(mLlKeyboardFinish);
                } else {
                    mViewHydropower.setVisibility(View.GONE);
                    mLlKeyboard.setVisibility(View.GONE);
                    for (int i = 0; i < beanHydropowersList.size(); i++) {
                        beanHydropowersList.get(i).setIsClick(false);
                    }
                    mAdpterHydropowerlist.notifyDataSetChanged();
                }
                break;
            case R.id.tv_keyboard_time:
//                birthday();
                break;
            //打开手电筒
            case R.id.tv_keyboard_flashlight:
                if (!isopent) {
                    camera = Camera.open();
                    Camera.Parameters params = camera.getParameters();
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview(); // 开始亮灯
                    isopent = true;
                    mTvKeyboardFlashlight.setText("关闭电筒");
                    mTvKeyboardFlashlight.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    camera.stopPreview(); // 关掉亮灯
                    camera.setPreviewCallback(null);
                    camera.release(); // 关掉照相机
                    camera = null;
                    isopent = false;
                    mTvKeyboardFlashlight.setText("打开电筒");
                    mTvKeyboardFlashlight.setTextColor(getResources().getColor(R.color.white));
                }
                break;
            //房源排序
            case R.id.tv_keyboard_sort:
                Hydropower_Sort.launch(mContext);
                break;
            case R.id.tv_keyboard_1:
                setNownum("1");
                break;
            case R.id.tv_keyboard_4:
                setNownum("4");
                break;
            case R.id.tv_keyboard_7:
                setNownum("7");
                break;
            case R.id.tv_keyboard_drop:
                setNownum(".");
                break;
            case R.id.tv_keyboard_2:
                setNownum("2");
                break;
            case R.id.tv_keyboard_5:
                setNownum("5");
                break;
            case R.id.tv_keyboard_8:
                setNownum("8");
                break;
            case R.id.tv_keyboard_0:
                setNownum("0");
                break;
            case R.id.tv_keyboard_3:
                setNownum("3");
                break;
            case R.id.tv_keyboard_6:
                setNownum("6");
                break;
            case R.id.tv_keyboard_9:
                setNownum("9");
                break;
            case R.id.iv_keyboard_clear:
                String mShow = "";
                //判断是否是新输入的,是吧本次的数据赋给上次
                if (beanHydropowersList.get(position).isRevise == null) {
                    LastData = beanHydropowersList.get(position).getLastDate();
                    LastSc = beanHydropowersList.get(position).getLastScale();
                    beanHydropowersList.get(position).setLastScale(beanHydropowersList.get(position).getThisScale());
                    beanHydropowersList.get(position).setLastDate(beanHydropowersList.get(position).getThisDate());
                    beanHydropowersList.get(position).setIsRevise("a");
                    beanHydropowersList.get(position).setThisDate(date);
                }
                //清除下的几种情况
                if (mTvKeyboardShow.getText().toString().length() == 1) {
                    mTvKeyboardShow.setText("0");
                    show = "0";
                    beanHydropowersList.get(position).setThisScale(0);
                    mAdpterHydropowerlist.notifyDataSetChanged();
                } else if (mTvKeyboardShow.getText().toString().length() == 2 && String.valueOf(mTvKeyboardShow.getText().toString().charAt(0)).equals("0")) {
                    mTvKeyboardShow.setText("0");
                    show = "0";
                    beanHydropowersList.get(position).setThisScale(0);
                    mAdpterHydropowerlist.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < mTvKeyboardShow.getText().toString().length(); i++) {
                        if (!(i == mTvKeyboardShow.getText().toString().length() - 1)) {
                            mShow = mShow + mTvKeyboardShow.getText().toString().charAt(i);
                        }
                    }
                    mTvKeyboardShow.setText(mShow);
                    beanHydropowersList.get(position).setThisScale(Double.parseDouble(mShow));
                    mAdpterHydropowerlist.notifyDataSetChanged();
                }
                break;
            //上一间
            case R.id.tv_keyboard_last:
                //数据比上次值小时进行判断
                if (beanHydropowersList.get(position).getThisScale() < beanHydropowersList.get(position).getLastScale() && beanHydropowersList.get(position).isInput == null && beanHydropowersList.get(Hydropower.position).isRevise != null) {
                    contrast(mTvKeyboardLast);
                } else {
                    show = "0";
                    if (position - 1 >= 0) {
                        beanHydropowersList.get(position).setIsClick(false);
                        beanHydropowersList.get(position - 1).setIsClick(true);
                        if (beanHydropowersList.get(position - 1).isRevise != null) {
                            mTvKeyboardShow.setText(String.valueOf(beanHydropowersList.get(position - 1).getThisScale()));
                        } else {
                            mTvKeyboardShow.setText("0");
                        }
                        position = position - 1;
                        mAdpterHydropowerlist.notifyDataSetChanged();
                    }
                    mLsListView.setSelection(position - 2);
                }
                break;
            //下一间
            case R.id.tv_keyboard_next:
                if (beanHydropowersList.get(position).getThisScale() < beanHydropowersList.get(position).getLastScale() && beanHydropowersList.get(position).isInput == null && beanHydropowersList.get(Hydropower.position).isRevise != null) {
                    contrast(mTvKeyboardNext);
                } else {
                    show = "0";
                    if (position + 1 < beanHydropowersList.size()) {
                        beanHydropowersList.get(position).setIsClick(false);
                        beanHydropowersList.get(position + 1).setIsClick(true);
                        if (beanHydropowersList.get(position + 1).isRevise != null) {
                            mTvKeyboardShow.setText(String.valueOf(beanHydropowersList.get(position + 1).getThisScale()));
                        } else {
                            mTvKeyboardShow.setText("0");
                        }
                        position = position + 1;
                        mAdpterHydropowerlist.notifyDataSetChanged();
                    }
                    mLsListView.setSelection(position - 2);
                }
                break;
        }

    }

    /**
     * 查询抄表
     */
    public void queryNewScalebyHouseId() {
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();
        map.put("houseId", Fragment_House.houseID);
        httpUtil.doPost(Url_final.HY, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mLoadingDialog.cancel();
                Bean_Hydropower bean_hydropower = JsonMananger.jsonToBean(result, Bean_Hydropower.class);
                if (bean_hydropower != null) {
                    if ("200".equals(bean_hydropower.getCode())) {
                        List<Bean_Hydropower.DataEntity> mList = new ArrayList<>();
                        mList.addAll(bean_hydropower.getData());
                        beanHydropowersList.clear();
                        hy.delete();
                        //按照类型给list添加数据
                        int size = mList.size();
                        for (int i = 0; i < size; i++) {
                            hy.insert(mList.get(i));
                            if (mList.get(i).getMeterType().equals(meterType)) {
                                beanHydropowersList.add(mList.get(i));
                            }
                        }
                        mAdpterHydropowerlist.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShort(mContext, bean_hydropower.getMessage() + ",显示上次访问数据");
                        beanHydropowersList.clear();
                        //按照类型给list添加数据
                        int size = hy.mQuery().size();
                        for (int i = 0; i < size; i++) {
                            if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                                beanHydropowersList.add(hy.mQuery().get(i));
                            }
                        }
                        mAdpterHydropowerlist.notifyDataSetChanged();
                    }
                } else {
                    ToastUtil.showShort(mContext, "网络访问失败,显示上次访问数据");
                    beanHydropowersList.clear();
                    //按照类型给list添加数据
                    int size = hy.mQuery().size();
                    for (int i = 0; i < size; i++) {
                        if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                            beanHydropowersList.add(hy.mQuery().get(i));
                        }
                    }
                    mAdpterHydropowerlist.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mLoadingDialog.cancel();
                ToastUtil.showShort(mContext, "网络访问失败,显示上次访问数据" + e.getMessage());
                beanHydropowersList.clear();
                //按照类型给list添加数据
                int size = hy.mQuery().size();
                for (int i = 0; i < size; i++) {
                    if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                        beanHydropowersList.add(hy.mQuery().get(i));
                    }
                }
                mAdpterHydropowerlist.notifyDataSetChanged();
            }
        });
    }

    /**
     * 键盘输入事件
     *
     * @param num
     */
    public void setNownum(String num) {
        //判断是否是新输入的,是吧本次的数据赋给上次
        if (beanHydropowersList.get(position).isRevise == null) {
            LastData = beanHydropowersList.get(position).getLastDate();
            LastSc = beanHydropowersList.get(position).getLastScale();
            beanHydropowersList.get(position).setLastScale(beanHydropowersList.get(position).getThisScale());
            beanHydropowersList.get(position).setLastDate(beanHydropowersList.get(position).getThisDate());
            beanHydropowersList.get(position).setIsRevise("a");
            beanHydropowersList.get(position).setThisDate(date);
        }
        //输入。时要进行判断
        if (num.equals(".")) {
            show = mTvKeyboardShow.getText().toString();
            if (show.length() <= 5) {
                boolean isDrop = false;
                for (int i = 0; i < show.length(); i++) {
                    if (String.valueOf(show.charAt(i)).equals(".")) {
                        isDrop = true;
                    }
                }
                if (!isDrop) {
                    show = mTvKeyboardShow.getText().toString() + ".";
                    if (show.length() <= 6) {
                        mTvKeyboardShow.setText(show);
                        beanHydropowersList.get(position).setThisScale(Double.parseDouble(show));
                        mAdpterHydropowerlist.notifyDataSetChanged();
                    }
                }
            }
        } else {
            if (show.equals("0")) {
                show = num;
            } else {
                show = mTvKeyboardShow.getText().toString() + num;
            }

            if (show.length() <= 6) {
                mTvKeyboardShow.setText(show);
                beanHydropowersList.get(position).setThisScale(Double.parseDouble(show));
                mAdpterHydropowerlist.notifyDataSetChanged();
            }
        }
    }

    /**
     * 进行判断这次值小于上次值时进行弹出框操作
     */
    public void contrast(final View mView) {
        Dialog_Show.contrast(mContext, result -> {
            switch (result) {
                //表数输错
                case "0":
                    beanHydropowersList.get(position).setThisDate(beanHydropowersList.get(position).getLastDate());
                    beanHydropowersList.get(position).setThisScale(beanHydropowersList.get(position).getLastScale());
                    beanHydropowersList.get(position).setLastDate(LastData);
                    beanHydropowersList.get(position).setLastScale(LastSc);
                    beanHydropowersList.get(position).setIsRevise(null);
                    mTvKeyboardShow.setText("0");
                    show = "0";
                    mAdpterHydropowerlist.notifyDataSetChanged();
                    break;
                //上次表数输错
                case "1":
                    beanHydropowersList.get(position).setIsInput("a");
                    mView.performClick();
                    break;
                //换表
                case "2":
                    new Dialog_Hydropower_Watch(mContext, date).builder().show();
                    break;
                //越界
                case "3":
                    beanHydropowersList.get(position).setFlag("M");
                    beanHydropowersList.get(position).setIsInput("a");
                    beanHydropowersList.get(position).setDemo("数据越界日期为" + date + "新数据为" + beanHydropowersList.get(position).getThisScale() + "旧数据为" + beanHydropowersList.get(position).getLastScale());
                    mView.performClick();
                    break;
            }
        });
    }

    /**
     * 保存抄表
     */
    public void saveMeter() {
//        //积累值的写入
//        for (int i = 0; i < beanHydropowersList.size(); i++) {
//            //判断是否换表
//            if (!beanHydropowersList.get(i).getFlag().equals("C")) {
//                //判断是否修改
//                if (beanHydropowersList.get(i).isRevise) {
//                    //判断是否越界
//                    if (beanHydropowersList.get(i).getFlag().equals("M")) {
//                        if (beanHydropowersList.get(i).getState().equals("未收")) {
//                            beanHydropowersList.get(i).setAccScale(beanHydropowersList.get(i).getAccScale() + beanHydropowersList.get(i).getThisScale() + 10000.0 - beanHydropowersList.get(i).getLastScale());
//                        } else if (beanHydropowersList.get(i).getState().equals("已收")) {
//                            beanHydropowersList.get(i).setAccScale(beanHydropowersList.get(i).getThisScale() + 10000.0 - beanHydropowersList.get(i).getLastScale());
//                        }
//                    } else {
//                        if (beanHydropowersList.get(i).getState().equals("未收")) {
//                            beanHydropowersList.get(i).setAccScale(beanHydropowersList.get(i).getAccScale() + beanHydropowersList.get(i).getThisScale() - beanHydropowersList.get(i).getLastScale());
//                        } else if (beanHydropowersList.get(i).getState().equals("已收")) {
//                            beanHydropowersList.get(i).setAccScale(beanHydropowersList.get(i).getThisScale() - beanHydropowersList.get(i).getLastScale());
//                        }
//                    }
//                }
//            }
//        }

        //当有数据改变时进行本地数据库保存操作
        List<Bean_Hydropower.DataEntity> mList = new ArrayList<>();
        if (beanHydropowersList.size() != 0) {
            int beanSize = beanHydropowersList.size();
            for (int i = 0; i < beanSize; i++) {
                if (beanHydropowersList.get(i).isRevise != null) {
                    hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                }
            }
        }
        //从本地数据库拿出那些标记的数据进行上传
        int hySize = hy.mQuery().size();
        for (int i = 0; i < hySize; i++) {
            if (hy.mQuery().get(i).isRevise != null) {
                mList.add(hy.mQuery().get(i));
            }
        }
        if (mList.size() != 0) {
            HttpUtil httpUtil = new HttpUtil();
            Map<String, String> map = new HashMap<>();
            map.put("meterJson", JsonMananger.beanToJson(mList));
            httpUtil.doPost(Url_final.SAVA_HY, map, new JsonCallBack() {
                @Override
                public void onSuccess(String result) {
                    mLoadingDialog.cancel();
                    BaseResponse baseResponse = JsonMananger.jsonToBean(result, BaseResponse.class);
                    if (baseResponse != null) {
                        if ("200".equals(baseResponse.getCode())) {
                            //保存成功把本地数据库标记清空
                            beanHydropowersList.clear();
                            int size = mList.size();
                            for (int i = 0; i < size; i++) {
                                if (mList.get(i).isRevise != null) {
                                    mList.get(i).setIsRevise(null);
                                    hy.update(mList.get(i), mList.get(i).getSysId());
                                }
                            }
                            //按照类型给list添加数据
                            size = hy.mQuery().size();
                            for (int i = 0; i < size; i++) {
                                if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                                    beanHydropowersList.add(hy.mQuery().get(i));
                                }
                            }
                            mAdpterHydropowerlist.notifyDataSetChanged();
                            ToastUtil.showShort(mContext, "保存成功");
                        } else {
                            ToastUtil.showShort(mContext, baseResponse.getMessage());
                        }
                    } else {
                        ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
                    }
                }

                @Override
                public void onFailed(Exception e) {
                    mLoadingDialog.cancel();
                    ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
                }
            });
        } else {
            ToastUtil.showShort(mContext, "没有要保存的数据");
        }
    }


    /**
     * 日期弹出框
     */
    public void birthday() {
        //创建DatePickerDialog对象
        DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                //更新日期
                String mDate = mYear + "-" + (mMonth + 1) + "-" + mDay;
                mTvKeyboardTime.setText(mDate);
                beanHydropowersList.get(position).setThisDate(mDate);
                mAdpterHydropowerlist.notifyDataSetChanged();
            }
        }, mYear, mMonth, mDay);
        dpd.show();//显示DatePickerDialog组件
    }


    /**
     * 水电表切换
     *
     * @param view 控件进行重新点击
     */
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.pop_hydropower, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        // 设置按钮的点击事件
        LinearLayout mLlWater = (LinearLayout) contentView.findViewById(R.id.ll_pop_hydropower_water);
        LinearLayout mLlElectricity = (LinearLayout) contentView.findViewById(R.id.ll_pop_hydropower_electricity);
        ImageView mIvWater = (ImageView) contentView.findViewById(R.id.iv_pop_hydropower_water);
        ImageView mIvElectricity = (ImageView) contentView.findViewById(R.id.iv_pop_hydropower_electricity);
        TextView mTvWater = (TextView) contentView.findViewById(R.id.tv_pop_hydropower_water);
        TextView mTvElectricity = (TextView) contentView.findViewById(R.id.tv_pop_hydropower_electricity);
        ImageView water_select = (ImageView) contentView.findViewById(R.id.iv_pop_hydropower_water_select);
        ImageView electricity_select = (ImageView) contentView.findViewById(R.id.iv_pop_hydropower_electricity_select);

        if (mTvTitleRight.getText().equals("水表")) {
            mTvWater.setTextColor(ContextCompat.getColor(mContext, R.color.text_light));
            mIvWater.setImageResource(R.mipmap.shuibiao_icon_dark);
            water_select.setVisibility(View.VISIBLE);
        } else if (mTvTitleRight.getText().equals("电表")) {
            mTvElectricity.setTextColor(ContextCompat.getColor(mContext, R.color.text_light));
            mIvElectricity.setImageResource(R.mipmap.dianbiao_icon_dark);
            electricity_select.setVisibility(View.VISIBLE);
        }

        mLlWater.setOnClickListener(v -> {
            mTvTitleRight.setText("水表");
            meterType = "W";
            mViewHydropower.setVisibility(View.GONE);
            mLlKeyboard.setVisibility(View.GONE);

            for (int i = 0; i < beanHydropowersList.size(); i++) {
                if (beanHydropowersList.get(i).isRevise != null) {
                    hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                }
            }
            beanHydropowersList.clear();
            for (int i = 0; i < hy.mQuery().size(); i++) {
                if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                    beanHydropowersList.add(hy.mQuery().get(i));
                }
            }
            mAdpterHydropowerlist.notifyDataSetChanged();
            popupWindow.dismiss();
        });
        mLlElectricity.setOnClickListener(v -> {
            mTvTitleRight.setText("电表");
            meterType = "E";
            mViewHydropower.setVisibility(View.GONE);
            mLlKeyboard.setVisibility(View.GONE);

            for (int i = 0; i < beanHydropowersList.size(); i++) {
                if (beanHydropowersList.get(i).isRevise != null) {
                    hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                }
            }
            beanHydropowersList.clear();
            for (int i = 0; i < hy.mQuery().size(); i++) {
                if (hy.mQuery().get(i).getMeterType().equals(meterType)) {
                    beanHydropowersList.add(hy.mQuery().get(i));
                }
            }
            mAdpterHydropowerlist.notifyDataSetChanged();
            popupWindow.dismiss();
        });

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor((v, event) -> {

            Log.i("mengdd", "onTouch : ");

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

    @Override //实现后台功能
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //进行判断是否有标记有更新本地数据库并提醒保存
        boolean iskeep = false;
        for (int i = 0; i < beanHydropowersList.size(); i++) {
            if (beanHydropowersList.get(i).isRevise != null) {
                hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
            }
        }
        int size = hy.mQuery().size();
        for (int i = 0; i < size; i++) {
            if (hy.mQuery().get(i).isRevise != null) {
                iskeep = true;
            }
        }
        if (iskeep) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                a();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 数据保存弹出框
     */
    public void a() {
        BaseAnimatorSet bas_in = new BounceEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        final NormalDialog dialog = new NormalDialog(Hydropower.this);
        dialog.isTitleShow(false)//
                .cornerRadius(5)//
                .content("数据有修改是否保存")//
                .contentGravity(Gravity.CENTER)//
                .btnTextSize(15.5f, 15.5f)//
                .widthScale(0.85f)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                this::finish,
                () -> {
                    //对网络进行判断没有网络读取本地数据库
                    if (NetUtil.isConnected(mContext) || NetUtil.isWifi(mContext)) {
                        saveMeter();
                    } else {
                        //当有数据改变时进行本地数据库保存操作
                        boolean a = false;
                        for (int i = 0; i < beanHydropowersList.size(); i++) {
                            if (beanHydropowersList.get(i).isRevise != null) {
                                hy.update(beanHydropowersList.get(i), beanHydropowersList.get(i).getSysId());
                                a = true;
                            }
                        }
                        if (a) {
                            ToastUtil.showShort(mContext, "保存本地成功,有网络时请再次保存");
                        }
                    }
                    finish();
                });
    }

    /**
     * 捕捉广播
     */
    public static class BCI extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String s = intent.getStringExtra("a");
            if (action.equals(Static_Hydropower.ACTION_UPDATEUI) && s.equals("a")) {
                mLsListView.setSelection(position - 2);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束广播
        this.unregisterReceiver(bci);
        if (camera != null) {
            camera.stopPreview(); // 关掉亮灯
            /**
             * Note: 一定要调用 camera.setPreviewCallback(null), 否则出现 Method called
             * after release() 异常.
             */
            camera.setPreviewCallback(null);
            camera.release(); // 关掉照相机
            camera = null;
        }

        beanHydropowersList.clear();
    }

}
