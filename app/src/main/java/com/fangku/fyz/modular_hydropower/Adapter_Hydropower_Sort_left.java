package com.fangku.fyz.modular_hydropower;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;

import java.util.List;

/**
 * Created by bowen.ye
 * Date: 2016/8/18
 * Time: 14:51
 */
public class Adapter_Hydropower_Sort_left extends BaseListAdapter<Bean_Hydropower.DataEntity> {
    List<Bean_Hydropower.DataEntity> list2;
    Adapter_Hydropower_Sort_Right adapter_hydropower_sort_right;

    public Adapter_Hydropower_Sort_left(Activity context, List<Bean_Hydropower.DataEntity> list,List<Bean_Hydropower.DataEntity> list2,Adapter_Hydropower_Sort_Right adapter_hydropower_sort_right) {
        super(context, list);
        this.list2=list2;
        this.adapter_hydropower_sort_right=adapter_hydropower_sort_right;
    }

    @Override
    public View bindView(final int position, View convertView, final ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_hydropower_sort,null);
        }

        LinearLayout mL= ViewHolder.get(convertView,R.id.ll_item_hydropower_sort);
        TextView mT= ViewHolder.get(convertView,R.id.tv_item_hydropower_sort);
        ImageView mI= ViewHolder.get(convertView,R.id.iv_item_hydropower_sort);

        mT.setText(list.get(position).getRoomNo());
        mI.setImageResource(R.mipmap.select_sort);

        mL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list2.add(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
                adapter_hydropower_sort_right.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
