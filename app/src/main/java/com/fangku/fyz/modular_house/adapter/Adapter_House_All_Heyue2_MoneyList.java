package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
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

import java.util.List;

/**
 * Created by bowen.ye
 * Date: 2016/9/28
 * Time: 17:13
 */
public class Adapter_House_All_Heyue2_MoneyList  extends BaseListAdapter<Bean_House_All_RoomCharge> {
    LayoutInflater layoutInflater;

    public Adapter_House_All_Heyue2_MoneyList(Activity context, List<Bean_House_All_RoomCharge> list) {
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
        textname.setText(list.get(position).getCostName());
        if (list.get(position).getCostUnit() != null) {
            textdanwei.setText(list.get(position).getCostUnit());
        }

        if (!list.get(position).isCheck()) {
            cbFather.setVisibility(View.GONE);
        } else {
            cbFather.setVisibility(View.VISIBLE);
        }


        return convertView;
    }


}
