package com.fangku.fyz.modular_house.adapter;/**
 * Created by 67343 on 2016/7/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.all_room.House_All_Shouzu;
import com.fangku.fyz.modular_house.all_room.House_All_chuzu;
import com.fangku.fyz.modular_house.all_room.House_All_fangjianxinxi;
import com.fangku.fyz.modular_house.all_room.House_All_xuyue;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.widget.Dialog_Show;

import java.util.List;

/**
 * 首页listview
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_house_list extends BaseListAdapter<Bean_House_Type_Room.DataBean> {
    LayoutInflater mInflater;
    private String mHouseName;
    private String mHouseID;


    public Adapter_house_list(Activity context, String houseID, String houseName, List<Bean_House_Type_Room.DataBean> list) {
        super(context, list);
        mHouseName = houseName;
        mHouseID = houseID;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_house_listview, null);
        }
        TextView tvRoomNums = ViewHolder.get(convertView, R.id.tv_house_roomnums);
        final TextView tvText1 = ViewHolder.get(convertView, R.id.tv_house_text1);
        TextView tvText2 = ViewHolder.get(convertView, R.id.tv_house_text2);
        TextView btn = ViewHolder.get(convertView, R.id.tv_house_btn);
        LinearLayout telephone = ViewHolder.get(convertView, R.id.ll_telephone);//给房客打电话

        tvRoomNums.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tvText1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线

        tvRoomNums.setText(list.get(position).getRoomNo());
        tvText1.setText(list.get(position).getRealName());

        if (list.get(position).getType().equals("未交租")) {
            tvText2.setText("￥" + String.valueOf(list.get(position).getCost()));
            btn.setText("收租");
            telephone.setEnabled(true);
        } else {//如果为未出租
            if (list.get(position).getRemaining() != null) {//如果过期信息不为空 则显示续租
                telephone.setEnabled(true);
                tvText1.setText(list.get(position).getRoomerName());
                tvText2.setText(list.get(position).getRemaining());
                btn.setText("续租");
            } else {//否则显示出租
                tvText1.setText("暂无租客");
                tvText2.setText("暂无租金");
                telephone.setEnabled(false);
                btn.setText("出租");
            }
        }

        btn.setOnClickListener(v -> {
            switch (btn.getText().toString()) {
                case "收租":
                    House_All_Shouzu.launch(mContext, list.get(position).getContractId(), list.get(position).getRoomerMobile());
                    break;
                case "续租":
                    House_All_xuyue.launch(mContext, mHouseID, list.get(position).getContractId(), mHouseName, list.get(position).getRoomId(), list.get(position).getRoomNo());
                    break;
                case "出租":
                    House_All_chuzu.launch(mHouseName, list.get(position).getRoomNo(), list.get(position).getRoomId(), list.get(position).getHouseId(), list.get(position).getContractId(), list.get(position).getLandlordId(), list.get(position).getLandlordCardId(), mContext);
                    break;
            }


        });

        telephone.setOnClickListener(v -> {
            String old_phome = list.get(position).getRoomerMobile() + "";
            if (old_phome.length() == 11) {
                old_phome = CommonUtil.phone_set_null(old_phome);
            }

            Dialog_Show.showTwoButton(mContext, "温馨提醒",
                    "是否确认向房客 " + tvText1.getText().toString() + " 拨打电话\n(" + old_phome + ")",
                    "取消", "确认", result -> {
                        if (result.equals("2")) {

                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + list.get(position).getRoomerMobile()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);


                        }
                    });
        });

        tvRoomNums.setOnClickListener(v -> {
            switch (btn.getText().toString()) {
                case "收租":
                    House_All_fangjianxinxi.launch(mContext, list.get(position).getHouseId(),
                            list.get(position).getRoomId(), mHouseName, list.get(position).getRoomNo(), true);
                    break;
                case "续租":
                    House_All_fangjianxinxi.launch(mContext, list.get(position).getHouseId(),
                            list.get(position).getRoomId(), mHouseName, list.get(position).getRoomNo(), true);
                    break;
                case "出租":
                    House_All_fangjianxinxi.launch(mContext, list.get(position).getHouseId(),
                            list.get(position).getRoomId(), mHouseName, list.get(position).getRoomNo(), false);
                    break;
            }

        });

        return convertView;
    }
}