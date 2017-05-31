package com.fangku.fyz.modular_house.adapter;/**
 * Created by 67343 on 2016/8/15.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.fyz.R;

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_Huose_GridView extends BaseListAdapter<String> {
    LayoutInflater layoutInflater;

    public Adapter_Huose_GridView(Activity context, List<String> list) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.huose_add_gridview, null);
        TextView button = (TextView) convertView.findViewById(R.id.bt_nums);
        button.setText(list.get(position));
        return convertView;
    }
}
