package com.fangku.fyz.modular_house.adapter;/**
 * Created by 67343 on 2016/8/24.
 */

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;

import java.util.List;

/**
 * 费用新增适配器
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_GridView_DataMoney extends BaseListAdapter<Bean_ListData> {
    LayoutInflater layoutInflater;
    private EditText mEditText;
    private TextView mTextView;
    private LinearLayout bottomLL;


    public Adapter_GridView_DataMoney(Activity context, List<Bean_ListData> list, EditText editText,
                                      TextView textView, LinearLayout linearLayout) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
        mEditText = editText;
        mTextView = textView;
        bottomLL = linearLayout;
    }


    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.huose_money_add_grieview, null);
        }


        final TextView tv_name = ViewHolder.get(convertView, R.id.tv_moneyAdd);
        tv_name.setText(list.get(position).getName());

        if (list.get(position).isCheck()) {
            tv_name.setBackgroundResource(R.drawable.fillet_background);
            tv_name.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            tv_name.setBackgroundResource(R.drawable.fillet_white_only);
            tv_name.setTextColor(ContextCompat.getColor(mContext, R.color.all_text_color));


        }


        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).isCheck()) {
                    return;
                }


                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setIsCheck(false);
                }
                list.get(position).setIsCheck(true);
                notifyDataSetChanged();

                if (list.get(position).getAttr4().equals("1")) {
                    bottomLL.setVisibility(View.GONE);
                    if (list.get(position).getName().equals("其他")) {
                        mEditText.setEnabled(true);
                        mEditText.setText("");
                        mEditText.requestFocus();

                    } else {
                        mEditText.setText(list.get(position).getName());
                        mEditText.setEnabled(false);
                    }

                } else {
                    mEditText.setEnabled(false);
                    bottomLL.setVisibility(View.VISIBLE);
                    mEditText.setText(list.get(position).getName());
                }

                mTextView.setText(list.get(position).getAttr3().toString());

            }
        });


        return convertView;
    }


}
