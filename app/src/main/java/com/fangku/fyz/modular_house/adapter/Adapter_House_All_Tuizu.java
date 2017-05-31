package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Tuizu_Rent;

import java.util.List;

/**
 * 退租费用明细适配器
 * Created by bowen.ye
 * Date: 2016/9/5
 * Time: 9:10
 */
public class Adapter_House_All_Tuizu extends BaseListAdapter<Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity>{

    public Adapter_House_All_Tuizu(Activity context, List<Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_house_all_shouzu, null);
        }
        LinearLayout mLl = ViewHolder.get(convertView, R.id.ll_item_house_all_shouzu);
        LinearLayout mLlMeter = ViewHolder.get(convertView, R.id.ll_item_house_all_shouzu_meter);
        CheckBox mRb = ViewHolder.get(convertView, R.id.rb_item_house_all_shouzu);
        TextView mName = ViewHolder.get(convertView, R.id.tv_item_house_all_shouzu);
        TextView mTextMeter = ViewHolder.get(convertView, R.id.tv_item_house_all_shouzu_meter);
        TextView mTextDate = ViewHolder.get(convertView, R.id.tv_item_house_all_shouzu_date);
        TextView mTextMoney = ViewHolder.get(convertView, R.id.tv_item_house_all_shouzu_money);
        TextView mTextRegular = ViewHolder.get(convertView, R.id.tv_item_house_all_shouzu_regular);

        mName.setText(list.get(position).getCostName());
        mTextMoney.setText(String.valueOf(list.get(position).getCostPrice()));

        //根据类型进行对item的修改
         if ("2".equals(list.get(position).getChargeType())) {
            mLlMeter.setVisibility(View.VISIBLE);
            mTextRegular.setVisibility(View.GONE);
            if(list.get(position).getCostName().equals("水表")){
                mTextMeter.setText(list.get(position).getCostPrice()+"元/立方");
            }else{
                mTextMeter.setText(list.get(position).getCostPrice()+"元/度");
            }
            mTextDate.setText(list.get(position).getCreateDate());
        }else{
           mLlMeter.setVisibility(View.GONE);
           mTextRegular.setVisibility(View.VISIBLE);
           mTextRegular.setText(list.get(position).getCostPrice()+"元/月");
       }

        return convertView;
    }
}
