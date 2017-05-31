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
import com.fangku.fyz.modular_hydropower.Bean_Hydropower;

import java.util.List;

/**
 * 出租的适配器
 * Created by   jie.wang
 * Date: 2016/8/23
 * Time: 15:04
 */
public class Adapter_House_Chuzu extends BaseListAdapter<Bean_Hydropower.DataEntity>{
    LayoutInflater mInflater;

    public Adapter_House_Chuzu(Activity context, List<Bean_Hydropower.DataEntity> list) {
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