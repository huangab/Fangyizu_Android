package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
 * 类型选择适配器(小类)
 * Created by bowen.ye
 * Date: 2016/9/5
 * Time: 15:35
 */
public class Adapter_House_All_AddCost_Type_XiaoClass extends BaseListAdapter<Bean_House_All_AddCost_Type> {
    TextView mText;
    EditText mEditText;
    Dialog dialog;
    public Adapter_House_All_AddCost_Type_XiaoClass(Activity context, List<Bean_House_All_AddCost_Type> list,TextView mTextView, EditText mEditText,Dialog dialog) {
        super(context, list);
        this.mText = mTextView;
        this.mEditText = mEditText;
        this.dialog=dialog;
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
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.activation));
        } else {
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_All_AddCost_Type.name=list.get(position).getType();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setIsClick(false);
                }
                list.get(position).setIsClick(true);
                notifyDataSetChanged();
                mText.setText(list.get(position).getType());
                mEditText.setText(list.get(position).getType());

                dialog.dismiss();
            }
        });

        mTextView.setText(list.get(position).getType());
        return convertView;
    }
}
