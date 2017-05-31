package com.fangku.fyz.modular_house.adapter;/**
 * Created by 67343 on 2016/8/14.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_Add_GetAddress;

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_Houes_Add_address extends BaseListAdapter<Bean_House_Add_GetAddress.DataBean> {

    LayoutInflater mInflater;
    ListViwLineOnClick mListViwLineOnClick;

    public Adapter_Houes_Add_address(Activity context, List<Bean_House_Add_GetAddress.DataBean> list) {
        super(context, list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.house_add_list_address, null);
        }
        TextView txAddressXiao = (TextView) convertView.findViewById(R.id.tv_house_address_xiao);
        TextView txAddressDa = (TextView) convertView.findViewById(R.id.tv_house_address_da);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_house_address_lv);

        txAddressXiao.setText(list.get(position).getName().toString());
        txAddressDa.setText(list.get(position).getCity() + " " + list.get(position).getDistrict().toString());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListViwLineOnClick.getData(list.get(position));
            }
        });


        return convertView;
    }

    public void setListViwLineOnClickListener(ListViwLineOnClick mListViwLineOnClick) {
        this.mListViwLineOnClick = mListViwLineOnClick;
    }

    public interface ListViwLineOnClick {
        public void getData(Bean_House_Add_GetAddress.DataBean data);
    }
}
