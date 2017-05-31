package com.fangku.fyz.modular_hydropower;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;

import java.util.List;

/**
 * 抄表历史页面适配器
 * Created by bowen.ye
 * Date: 2016/8/21
 * Time: 17:22
 */
public class Adapter_Hydropower_List_History extends BaseListAdapter<Bean_Hydropower.DataEntity> {
    public Adapter_Hydropower_List_History(Activity context, List<Bean_Hydropower.DataEntity> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_hydropower_history,null);
        }

        TextView scale= ViewHolder.get(convertView,R.id.tv_item_hydropower_history_roomId);
        TextView date= ViewHolder.get(convertView,R.id.tv_item_hydropower_history_date);
        TextView time= ViewHolder.get(convertView,R.id.tv_item_hydropower_history_time);
        TextView flag= ViewHolder.get(convertView,R.id.tv_item_hydropower_history_flag);

        scale.setText(String.valueOf(list.get(position).getThisScale()));
        date.setText(list.get(position).getCreateDate());
        time.setText(list.get(position).getCreateTime());
        flag.setText(list.get(position).getState());

        return convertView;
    }
}
