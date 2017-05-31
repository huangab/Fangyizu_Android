package com.fangku.fyz.modular_house.adapter;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.modular_house.dialog.Dialog_Input_Text;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/9/8
 * Time: 15:43
 */
public class Adapter_Money_list extends BaseListAdapter<Bean_House_All_RoomCharge> {
    LayoutInflater layoutInflater;

    public Adapter_Money_list(Activity context, List<Bean_House_All_RoomCharge> list) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_money_list_layout, null);
        }


        final CheckBox cbox = ViewHolder.get(convertView, R.id.rb_AddMoney_list);

        LinearLayout cbFather = ViewHolder.get(convertView, R.id.ll_feiyong_check_father);

        TextView textdanwei = ViewHolder.get(convertView, R.id.tv_money_DanWei_list);

        TextView textnum = ViewHolder.get(convertView, R.id.et_money_DanWeiNums_list);

        TextView textmin = ViewHolder.get(convertView, R.id.et_money_MinNums_list);

        LinearLayout linearLayout = ViewHolder.get(convertView, R.id.ll_addmoney_list);

        final TextView textname = ViewHolder.get(convertView, R.id.tv_money_name_list);

        LinearLayout danjia = ViewHolder.get(convertView, R.id.ll_danjia);
        LinearLayout ll_min = ViewHolder.get(convertView, R.id.ll_min);


        textnum.setText(list.get(position).getCostPrice().toString());
        if (list.get(position).getMinValue() != null) {
            textmin.setText(list.get(position).getMinValue().toString());
        }
        textname.setText(list.get(position).getCostName().toString());
        if (list.get(position).getCostUnit() != null) {
            textdanwei.setText(list.get(position).getCostUnit().toString());
        }

        if (!list.get(position).isCheck()) {
            cbFather.setVisibility(View.GONE);
        } else {
            cbFather.setVisibility(View.VISIBLE);
        }


        if ("1".equals(list.get(position).getChargeType())) {//类型为1 则隐藏最小值
            linearLayout.setVisibility(View.GONE);
        } else {//类型为2 则不隐藏最小值
            linearLayout.setVisibility(View.VISIBLE);
        }

        danjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dsa", "bindView: 我点击了按钮");
                new Dialog_Input_Text(mContext, list, position, Double.valueOf(textnum.getText().toString()), 1, Adapter_Money_list.this).builder().show();
            }
        });
        ll_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dsa", "bindView: 我点击了按钮2");
                new Dialog_Input_Text(mContext, list, position, Double.valueOf(textmin.getText().toString()), 2, Adapter_Money_list.this).builder().show();

            }
        });


        cbFather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cbox.isChecked()) {
                    cbox.setChecked(false);
                    list.get(position).setNeedDelete(false);
                } else {
                    cbox.setChecked(true);
                    list.get(position).setNeedDelete(true);
                }
            }
        });

        if (list.get(position).isNeedDelete()) {
            cbox.setChecked(true);
        } else {
            cbox.setChecked(false);
        }


        return convertView;
    }


}
