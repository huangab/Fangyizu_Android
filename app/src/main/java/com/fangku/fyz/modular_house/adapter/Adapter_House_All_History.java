package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_History;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/9/22
 * Time: 9:47
 */
public class Adapter_House_All_History extends BaseListAdapter<Bean_House_All_History.DataBean> {


    public Adapter_House_All_History(Activity context, List<Bean_House_All_History.DataBean> list) {
        super(context, list);

    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_all_history, null);
        }

        TextView name = ViewHolder.get(convertView, R.id.tv_all_history_name);
        TextView money = ViewHolder.get(convertView, R.id.tv_all_history_money);
        TextView type = ViewHolder.get(convertView, R.id.tv_all_history_type);
        TextView time = ViewHolder.get(convertView, R.id.tv_all_history_time);

        name.setText(list.get(position).getRealName());
        money.setText(String.valueOf(list.get(position).getCost()));
        type.setText(list.get(position).getState());
        time.setText(list.get(position).getStartDate());

        return convertView;
    }
}
