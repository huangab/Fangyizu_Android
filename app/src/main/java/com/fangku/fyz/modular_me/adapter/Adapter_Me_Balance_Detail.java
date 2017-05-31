package com.fangku.fyz.modular_me.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_Me_Balance_Detail;

import java.util.List;

/**
 * 明细列表适配器
 * Created by bowen.ye
 * Date: 2016/7/14
 * Time: 16:13
 */
public class Adapter_Me_Balance_Detail extends BaseListAdapter<Bean_Me_Balance_Detail> {
    List<Bean_Me_Balance_Detail> mList;

    public Adapter_Me_Balance_Detail(Activity context, List<Bean_Me_Balance_Detail> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_me_balance_detail, null);
        }

        TextView type= ViewHolder.get(convertView,R.id.tv_item_me_balance_detail_type);
        TextView time= ViewHolder.get(convertView,R.id.tv_item_me_balance_detail_time);
        TextView balance= ViewHolder.get(convertView,R.id.tv_item_me_balance_detail_balance);
        TextView pay= ViewHolder.get(convertView,R.id.tv_item_me_balance_detail_pay);

        type.setText(list.get(position).getType());
        time.setText(list.get(position).getTime());
        balance.setText(list.get(position).getBalance());
        pay.setText(list.get(position).getPay());

        return convertView;
    }
}
