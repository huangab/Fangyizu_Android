package com.fangku.fyz.modular_me.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_me.bean.Bean_Me_Message;

import java.util.List;

/**
 * 卡券包适配器
 * Created by bowen.ye
 * Date: 2016/7/15
 * Time: 16:15
 */
public class Adapter_Message extends BaseListAdapter<Bean_Me_Message.DataBean> {
    public Adapter_Message(Activity context, List<Bean_Me_Message.DataBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_message_center, null);
        }
        TextView messageName = ViewHolder.get(convertView, R.id.tv_message_name);
        TextView messageTitle = ViewHolder.get(convertView, R.id.tv_message_title);
        TextView time = ViewHolder.get(convertView, R.id.tv_message_time);
        LinearLayout background = ViewHolder.get(convertView, R.id.ll_bank_background);

        messageName.setText(list.get(position).getPublish());
        messageTitle.setText(list.get(position).getTitle());
        time.setText(list.get(position).getPublishDate() + "  " + list.get(position).getPublishTime());

        return convertView;
    }
}
