package com.fangku.fyz.modular_house.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.commonlibrary.utils.KeyBoardUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.adapter.Adapter_Money_list;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 费用编辑的弹出框
 * Created by jie.wang
 * Date: 2016/9/12
 * Time: 14:31
 */
public class Dialog_Input_Text {
    Context mContext;
    Display display;
    Dialog dialog;
    List<Bean_House_All_RoomCharge> mList;
    int position;
    int type;//1为单价  2为最小值
    Adapter_Money_list mAdapter;
    double mMoney;

    public Dialog_Input_Text(Context mContext, List<Bean_House_All_RoomCharge> list, int position, double money, int type, Adapter_Money_list adapter) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mList = new ArrayList<>(list);
        this.position = position;
        this.type = type;
        mAdapter = adapter;
        mMoney = money;
    }

    public Dialog_Input_Text builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_input_text, null);

        LinearLayout Layout = ViewHolder.get(view, R.id.ll_input_layout);
        EditText text = ViewHolder.get(view, R.id.et_input_text);
        Button ok = ViewHolder.get(view, R.id.bt_input_ok);
        TextView clean = ViewHolder.get(view, R.id.tv_input_clean);
        ImageView quit = ViewHolder.get(view, R.id.iv_input_quit);

        KeyBoardUtil.openKeybord(text, mContext);
        text.setText(String.valueOf(mMoney));

        ok.setOnClickListener(v -> {

            if ("".equals(text.getText().toString().trim())) {
                text.setHint("金额不能为空!");
                return;
            }
            double num = Double.valueOf(text.getText().toString());
            if (num > 9999) {
                num = 9999;
            }
            DecimalFormat format = new DecimalFormat();
            format.applyPattern("0.00");

            if (type == 1) {
                mList.get(position).setCostPrice(Double.valueOf(format.format(num)));
            } else if (type == 2) {
                mList.get(position).setMinValue((int) num);

            }
            mAdapter.notifyDataSetChanged();
            dialog.dismiss();

        });

        clean.setOnClickListener(v -> {
            text.setText("");
            text.setHint("最大值9999,最小值0.01");
        });

        quit.setOnClickListener(v -> dialog.dismiss());


        dialog = new Dialog(mContext, R.style.AlertDialogStyle);//设置出现的风格
        dialog.setContentView(view);
//        dialog.setCancelable(false);   //设置不可点击其他地方取消
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        // 调整dialog背景大小
        Layout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 1), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public void show() {
        dialog.show();
    }
}
