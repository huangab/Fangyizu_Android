package com.fangku.fyz.modular_house.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/9/8
 * Time: 15:43
 */
public class Adapter_huanfang_list_right extends BaseListAdapter<Bean_House_Type_Room.DataBean> {
    LayoutInflater layoutInflater;
    huangfangRightMessage message;

    public Adapter_huanfang_list_right(Activity context, List<Bean_House_Type_Room.DataBean> list) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.huanfang_roomlist_layout, null);
        }

        RelativeLayout linearLayout = ViewHolder.get(convertView, R.id.rl_huanfang_background);

        RadioButton rb = ViewHolder.get(convertView, R.id.cb_huanfangList);
        rb.setText(list.get(position).getRoomNo());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.getMessage2(list.get(position));
            }
        });

        ImageView imageView = ViewHolder.get(convertView, R.id.iv_huanfangImage);
        imageView.setVisibility(View.GONE);

        return convertView;
    }

    public void setHuangfangRightMessage(huangfangRightMessage message) {
        this.message = message;
    }

    public interface huangfangRightMessage {
        public void getMessage2(Bean_House_Type_Room.DataBean s);
    }
}
