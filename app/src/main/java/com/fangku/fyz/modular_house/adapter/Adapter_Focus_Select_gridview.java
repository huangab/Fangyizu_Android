package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_House_Floors_Number;

import java.util.List;

/**
 * Created by   jie.wang
 * GRIDVIEW适配器
 *
 * @author jayqiu
 */
public class Adapter_Focus_Select_gridview extends BaseListAdapter<Bean_House_Floors_Number.Room> {

    public Adapter_Focus_Select_gridview(Activity context, List list) {
        super(context, list);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_mygridview_item, null);
        }
        final CheckBox box = (CheckBox) convertView.findViewById(R.id.cb_item_goods_type);
        box.setText(list.get(position).getRoom() + "");

        //如果checkbox中的值为0,则把他设为最后的那个加号
        if (box.getText().equals("0")) {
            box.setVisibility(View.VISIBLE);
            box.setText("+");
            box.setTextSize(20);
            box.setBackgroundResource(R.drawable.selector_white_button);
            box.setTextColor(ContextCompat.getColor(mContext, R.color.background_color));
            if ((list.size() - 1) % 100 == 35) {
                box.setVisibility(View.GONE);
            }
        }


        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bean_House_Floors_Number.Room room;


                if (box.getText().toString().equals("+")) {
                    room = new Bean_House_Floors_Number.Room();
                    room.setIsselect(false);
                    room.setRoom(list.get(position - 1).getRoom() + 1);
                    list.add(list.size() - 1, room);
                    box.setTextSize(15);
                    box.setText(list.get(position - 1).getRoom() + "");
                    box.setBackgroundResource(R.drawable.checked_button);
                    notifyDataSetChanged();
                }
            }
        });


        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.get(position).setIsselect(true);
                    box.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                } else {
                    list.get(position).setIsselect(false);
                    box.setTextColor(ContextCompat.getColor(mContext, R.color.background_color));
                }
            }
        });

        if (list.get(position).isselect()) {
            box.setChecked(true);
        } else {
            box.setChecked(false);
        }
        notifyDataSetChanged();

        return convertView;
    }


}
