package com.fangku.fyz.modular_me.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_me.bean.Bean_Me_BankCard;

import java.util.List;

/**
 * 卡券包适配器
 * Created by bowen.ye
 * Date: 2016/7/15
 * Time: 16:15
 */
public class Adapter_BankCard extends BaseListAdapter<Bean_Me_BankCard.DataDataBean> {
    public Adapter_BankCard(Activity context, List<Bean_Me_BankCard.DataDataBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_me_bank, null);
        }
        TextView bankName = ViewHolder.get(convertView, R.id.tv_me_BankCard_BankName);
        TextView banktype = ViewHolder.get(convertView, R.id.tv_me_BankCard_CardClass);
        TextView banknumber = ViewHolder.get(convertView, R.id.tv_me_BankCard_CardNums);
        LinearLayout background = ViewHolder.get(convertView, R.id.ll_bank_background);

        if ((position + 1) % 1 == 0) {

            background.setBackgroundResource(R.mipmap.bank_back_red);
        }

        if ((position + 1) % 2 == 0) {

            background.setBackgroundResource(R.mipmap.bank_back_blue);
        }
//        if ((position + 1) % 3 == 0) {
//
//            background.setBackgroundResource(R.mipmap.bank_back_green);
//        }

        bankName.setText(list.get(position).getName());
        banktype.setText(list.get(position).getCardType());
        String old = list.get(position).getCardNo();
        String new_num;
        if (old.length()==16){
             new_num = "**** **** **** " + old.substring(12, 16);
        }else {
            new_num = "**** **** **** **** " + old.substring(16, 19);
        }

        banknumber.setText(new_num);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        convertView.startAnimation(alphaAnimation);
        return convertView;
    }
}
