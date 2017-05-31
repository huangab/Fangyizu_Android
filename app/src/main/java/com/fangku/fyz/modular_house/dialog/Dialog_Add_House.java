package com.fangku.fyz.modular_house.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.add_room.House_Add_Dispersed;
import com.fangku.fyz.modular_house.add_room.House_Add_Focus;
import com.fangku.fyz.modular_house.add_room.House_Add_Room;
import com.fangku.fyz.modular_house.add_room.House_UP;

/**
 * 增加房源弹出框
 * Created by bowen.ye
 * Date: 2016/7/18
 * Time: 14:31
 */
public class Dialog_Add_House {
    Context mContext;
    Display display;
    Dialog dialog;

    public Dialog_Add_House(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public Dialog_Add_House builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_house, null);

        LinearLayout concentrate = (LinearLayout) view.findViewById(R.id.ll_dialog_add_house_concentrate);
        LinearLayout disperse = (LinearLayout) view.findViewById(R.id.ll_dialog_add_house_disperse);
        LinearLayout house = (LinearLayout) view.findViewById(R.id.ll_dialog_add_house_2);
        LinearLayout houseup = (LinearLayout) view.findViewById(R.id.ll_dialog_add_house_weihu);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_dialog_add_house);
        TextView quit = (TextView) view.findViewById(R.id.tv_dialog_addhouse_quit);
        concentrate.setOnClickListener(v -> {
            House_Add_Focus.launch(mContext);
            dialog.dismiss();
        });
        disperse.setOnClickListener(v -> {
            House_Add_Dispersed.launch(mContext);
            dialog.dismiss();
        });
        house.setOnClickListener(v -> {
            House_Add_Room.launch(mContext);
            dialog.dismiss();
        });
        quit.setOnClickListener(v -> dialog.dismiss());

        houseup.setOnClickListener(v -> {
            if (MyApplication.houseSysID.length != 0) {
                Intent intent = new Intent(mContext, House_UP.class);
                mContext.startActivity(intent);
            } else {
                ToastUtil.showShort(mContext, "您尚无房产!");
            }

            dialog.dismiss();
        });


        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.95), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public void show() {
        dialog.show();
    }
}


