package com.fangku.fyz.modular_me.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_me.bean.Bean_Me_Cardpag;

import java.util.List;

/**
 * 卡券包适配器
 * Created by bowen.ye
 * Date: 2016/7/15
 * Time: 16:15
 */
public class Adapter_Cardpackage extends BaseListAdapter<Bean_Me_Cardpag.DataBean> {
    public Adapter_Cardpackage(Activity context, List<Bean_Me_Cardpag.DataBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_me_cardpackage, null);
        }
        TextView name = ViewHolder.get(convertView, R.id.tv_item_me_cardpackage_name);
        TextView time = ViewHolder.get(convertView, R.id.tv_item_me_cardpackage_time);
        TextView money = ViewHolder.get(convertView, R.id.tv_item_me_cardpackage_money);
        RelativeLayout background = ViewHolder.get(convertView, R.id.tv_back_right);

        if ((position + 1) % 1 == 0) {
            background.setBackgroundResource(R.color.red_button);
            money.setBackgroundResource(R.drawable.fillet_red);
        }

        if ((position + 1) % 2 == 0) {
            background.setBackgroundResource(R.color.button_color);
            money.setBackgroundResource(R.drawable.fillet_background);
        }
        if ((position + 1) % 3 == 0) {
            background.setBackgroundResource(R.color.white_gray);
            money.setBackgroundResource(R.drawable.fillet_gray);
        }

        String mTime = "结束日期:" + list.get(position).getEndDate();
        name.setText(list.get(position).getCardTitle());
        time.setText(mTime);
        money.setText("￥" + list.get(position).getAmount());

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        convertView.startAnimation(alphaAnimation);

        return convertView;
    }
}
