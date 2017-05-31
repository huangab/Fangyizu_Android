package com.fangku.fyz.modular_hydropower;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Hydropower;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.util.List;

/**
 * 抄表适配器
 * Created by bowen.ye
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_Hydropower_list extends BaseListAdapter<Bean_Hydropower.DataEntity> {
    LayoutInflater mInflater;
    TextView mTextView;
    LinearLayout mL;
    LinearLayout mViewHydropower;
    LinearLayout click;
    String date;

    public Adapter_Hydropower_list(Activity context, List<Bean_Hydropower.DataEntity> list, LinearLayout mL, TextView mTextView, LinearLayout mViewHydropower,String date) {
        super(context, list);
        mInflater = LayoutInflater.from(context);
        this.date=date;
        this.mL = mL;
        this.mTextView = mTextView;
        this.mViewHydropower = mViewHydropower;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_hydropower_listview, null);
        }

        click = (LinearLayout) convertView.findViewById(R.id.ll_hydropower_click);
        final LinearLayout hy = (LinearLayout) convertView.findViewById(R.id.ll_hydropower);
        final LinearLayout now = (LinearLayout) convertView.findViewById(R.id.ll_hydropower_now);
        TextView tvRoomNums = (TextView) convertView.findViewById(R.id.tv_hydropower_roomnums);
        TextView tvOldNums = (TextView) convertView.findViewById(R.id.tv_hydropower_oldnums);
        TextView tvOldTime = (TextView) convertView.findViewById(R.id.tv_hydropower_oldtime);
        final TextView tvNowNums = (TextView) convertView.findViewById(R.id.tv_hydropower_nownums);
        TextView tvNowTime = (TextView) convertView.findViewById(R.id.tv_hydropower_nowtime);
        TextView btn = (TextView) convertView.findViewById(R.id.tv_house_btn);

        tvRoomNums.setText(list.get(position).getRoomNo());
        tvNowTime.setText(list.get(position).getThisDate());
        tvOldTime.setText(list.get(position).getLastDate());
        tvNowNums.setText(String.valueOf(list.get(position).getThisScale()));
        tvOldNums.setText(String.valueOf(list.get(position).getLastScale()));

        if (list.get(position).getIsClick()) {
            hy.setBackgroundResource(R.color.wheat);
            now.setBackgroundResource(R.drawable.fillet_white);
        } else {
            hy.setBackgroundResource(R.color.white);
            now.setBackground(null);
        }

        click.setOnClickListener(
                v -> {
                    Hydropower.position = position;
                    //进行判断这次值小于上次值时进行弹出框操作
                    if ((list.get(Hydropower.position).isRevise!=null)&&(list.get(Hydropower.position).isInput==null) && list.get(Hydropower.position).getThisScale() < list.get(Hydropower.position).getLastScale()) {
                        contrast();
                    } else {
                        //第一次定位用发广播的方法进行
                        if (mViewHydropower.getVisibility() == View.GONE) {
                            mViewHydropower.setVisibility(View.VISIBLE);
                            //发送广播
                            Intent mIntent = new Intent();
                            mIntent.setAction(Static_Hydropower.ACTION_UPDATEUI);
                            mIntent.putExtra("a", "a");
                            mContext.sendOrderedBroadcast(mIntent, null);
                        }
                        //显示键盘
                        mL.setAnimation(CommonUtil.show_in_bottom());
                        mL.setVisibility(View.VISIBLE);

                        Hydropower.show = "0";
                        //判断是否有输入新值
                        if (list.get(position).isRevise!=null) {
                            mTextView.setText(String.valueOf(list.get(position).getThisScale()));
                        } else {
                            mTextView.setText("0");
                        }
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setIsClick(false);
                        }
                        list.get(position).setIsClick(true);
                        notifyDataSetChanged();
                        //定位list位置
                        Hydropower.mLsListView.setSelection(position - 2);
                    }
                }
        );

        btn.setOnClickListener(v -> getHeadImage(position)

        );

        return convertView;
    }

    /**
     * 进行判断这次值小于上次值时进行弹出框操作
     */
    public void contrast() {
        Dialog_Show.contrast(mContext, result -> {
            switch (result) {
                //表数输错
                case "0":
                    list.get(Hydropower.position).setThisDate(list.get(Hydropower.position).getLastDate());
                    list.get(Hydropower.position).setThisScale(list.get(Hydropower.position).getLastScale());
                    list.get(Hydropower.position).setLastDate(Hydropower.LastData);
                    list.get(Hydropower.position).setLastScale(Hydropower.LastSc);
                    list.get(Hydropower.position).setIsRevise(null);
                    Hydropower.show = "0";
                    mTextView.setText("0");
                    notifyDataSetChanged();
                    break;
                //上次表数输错
                case "1":
                    list.get(Hydropower.position).setIsInput("a");
                    click.performClick();
                    break;
                //换表
                case "2":
                    new Dialog_Hydropower_Watch(mContext,date).builder().show();
                    break;
                //越界
                case "3":
                    list.get(Hydropower.position).setFlag("M");
                    list.get(Hydropower.position).setIsInput("a");
                    list.get(Hydropower.position).setDemo("数据越界日期为" + date + "新数据为" + list.get(Hydropower.position).getThisScale() + "旧数据为" + list.get(Hydropower.position).getLastScale());
                    click.performClick();
                    break;
            }
        });
    }

    /**
     * 操作弹出框
     */
    public void getHeadImage(int pos) {
        final String[] stringItems = {"历史", "换表"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("请选择操作")//
                .titleTextSize_SP(14.5f)//
                .itemHeight(55)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //跳转到历史界面
                    case 0:
                        Hydropower_History.launch(mContext,list.get(pos).getHouseId(),list.get(pos).getRoomId(),list.get(pos).getRoomNo(),list.get(pos).getMeterType());
                        break;
                    //换表
                    case 1:
                        new Dialog_Hydropower_Watch(mContext,date).builder().show();
                        break;

                }
                dialog.dismiss();
            }
        });
    }
}