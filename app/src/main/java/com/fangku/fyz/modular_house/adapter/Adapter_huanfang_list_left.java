package com.fangku.fyz.modular_house.adapter;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/9/8
 * Time: 15:43
 */
public class Adapter_huanfang_list_left extends BaseListAdapter<Bean_House_Data.DataBean> {
    LayoutInflater layoutInflater;
    huangfangLeftMessage message;
    boolean isFalstStart = true;
    List<RadioButton> radioButtons = new ArrayList<>();
    List<RelativeLayout> relativeLayouts = new ArrayList<>();

    public Adapter_huanfang_list_left(Activity context, List<Bean_House_Data.DataBean> list) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
        isFalstStart = true;
    }


    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.huanfang_roomlist_layout, null);
        }

        RelativeLayout rl = ViewHolder.get(convertView, R.id.rl_huanfang_background);

        relativeLayouts.add(rl);

        RadioButton rb = ViewHolder.get(convertView, R.id.cb_huanfangList);
        rb.setText(list.get(position).getHouseName());
        if (position == 0 && isFalstStart) {
            isFalstStart = false;
            rb.setChecked(true);
            relativeLayouts.get(position).setBackgroundResource(R.color.dodgerblue);
            message.getMessage(list.get(position));
        }

        Log.i("test", rb.isChecked() + "");

        radioButtons.add(rb);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < radioButtons.size(); i++) {
                    radioButtons.get(i).setChecked(false);
                    relativeLayouts.get(i).setBackgroundResource(R.color.white);
                }
                radioButtons.get(position).setChecked(true);
                relativeLayouts.get(position).setBackgroundResource(R.color.dodgerblue);
                message.getMessage(list.get(position));
            }
        });
        return convertView;
    }

    public void setHuangfangLeftMessage(huangfangLeftMessage message) {
        this.message = message;
    }

    public interface huangfangLeftMessage {
        public void getMessage(Bean_House_Data.DataBean s);
    }

}
