package com.fangku.fyz.modular_house.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;

import java.util.List;

/**
 * 合约的适配器
 * Created by   jie.wang
 * Date: 2016/8/23
 * Time: 15:04
 */
public class Adapter_House_Heyue extends BaseListAdapter<Bean_House_All_Contract.DataEntity.CbxxEntity>{
    LayoutInflater mInflater;

    public Adapter_House_Heyue(Activity context, List<Bean_House_All_Contract.DataEntity.CbxxEntity> list) {
        super(context, list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_heyue_shuibiao_dianbiao, null);
        }
        TextView name = ViewHolder.get(convertView, R.id.tv_heyue_list_name);
        TextView fanwei = ViewHolder.get(convertView, R.id.tv_heyue_list_size);
        EditText mSize = ViewHolder.get(convertView, R.id.et_heyue_list_inpuut);//多少度 /吨
        TextView danwei = ViewHolder.get(convertView, R.id.tv_heyue_danwei);//单位

        mSize.setEnabled(false);
        if(list.get(position).getLastScale()!=0){
            mSize.setText(String.valueOf(list.get(position).getLastScale()));
        }

        name.setText(list.get(position).getMeterName() + "*");
        if (list.get(position).getMeterType().equals("E")) {
            danwei.setText("吨");
        } else {
            danwei.setText("度");
        }

        mSize.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!"".equals(mSize.getText().toString())) {
                    list.get(position).setThisScale(Double.parseDouble(mSize.getText().toString()));
                }
            }
        });

        return convertView;
    }


}