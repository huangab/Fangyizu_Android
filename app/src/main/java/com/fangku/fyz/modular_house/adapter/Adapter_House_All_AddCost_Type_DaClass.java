package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_AddCost_Type;
import com.fangku.fyz.modular_house.dialog.Dialog_All_AddCost_Type;

import java.util.List;

/**
 * 类型选择适配器(大类)
 * Created by bowen.ye
 * Date: 2016/9/5
 * Time: 15:35
 */
public class Adapter_House_All_AddCost_Type_DaClass extends BaseListAdapter<Bean_House_All_AddCost_Type> {
    Adapter_House_All_AddCost_Type_XiaoClass mAdapter;
    List<Bean_House_All_AddCost_Type> mXiaoClassList;
    public Adapter_House_All_AddCost_Type_DaClass(Activity context, List<Bean_House_All_AddCost_Type> list,Adapter_House_All_AddCost_Type_XiaoClass mAdapter,List<Bean_House_All_AddCost_Type> mXiaoClassList) {
        super(context, list);
        this.mAdapter=mAdapter;
        this.mXiaoClassList=mXiaoClassList;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_house_all_addcost_type, null);
        }
        TextView mTextView = ViewHolder.get(convertView, R.id.tv_item_house_all_addcost_type);
        ImageView mImageView = ViewHolder.get(convertView, R.id.iv_item_house_all_addcost_type);
        RelativeLayout r = ViewHolder.get(convertView, R.id.rl_item_house_all_addcost_type);

        if (list.get(position).isClick()) {
            r.setBackgroundResource(R.color.activation);
            mImageView.setVisibility(View.VISIBLE);
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            mImageView.setVisibility(View.GONE);
            r.setBackgroundResource(R.color.wheat);
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPos(position);
                Dialog_All_AddCost_Type.type=String.valueOf(position);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setIsClick(false);
                }
                list.get(position).setIsClick(true);
                notifyDataSetChanged();
            }
        });

        mTextView.setText(list.get(position).getType());
        return convertView;
    }


    public void getPos(int pos){
        Bean_House_All_AddCost_Type mXiaoClass;
        mXiaoClassList.clear();
        switch (pos) {
            case 0:
                String[] typeXiaoClass0 = {"租金", "押金"};
                for (int i = 0; i < 2; i++) {
                    mXiaoClass = new Bean_House_All_AddCost_Type();
                    mXiaoClass.setType(typeXiaoClass0[i]);
                    mXiaoClassList.add(mXiaoClass);
                }
                break;
            case 1:
                String[] typeXiaoClass1 = {"管理费", "网费", "卫生费"};
                for (int i = 0; i < 3; i++) {
                    mXiaoClass = new Bean_House_All_AddCost_Type();
                    mXiaoClass.setType(typeXiaoClass1[i]);
                    mXiaoClassList.add(mXiaoClass);
                }
                break;
            case 2:
                String[] typeXiaoClass2 = {"电表", "水表"};
                for (int i = 0; i < 2; i++) {
                    mXiaoClass = new Bean_House_All_AddCost_Type();
                    mXiaoClass.setType(typeXiaoClass2[i]);
                    mXiaoClassList.add(mXiaoClass);
                }
                break;
            case 3:
                String[] typeXiaoClass3 = {"其他"};
                for (int i = 0; i < 1; i++) {
                    mXiaoClass = new Bean_House_All_AddCost_Type();
                    mXiaoClass.setType(typeXiaoClass3[i]);
                    mXiaoClassList.add(mXiaoClass);
                }
                break;
        }
        mAdapter.notifyDataSetChanged();
    }
}
