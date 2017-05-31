package com.fangku.fyz.modular_house.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.add_room.House_Add_Focus;
import com.fangku.fyz.modular_house.add_room.House_Add_Focus_Select;
import com.fangku.fyz.modular_house.add_room.House_Add_Room;
import com.fangku.fyz.modular_house.add_room.House_Money;

/**
 * 增加房源成功弹出框
 * Created by bowen.ye
 * Date: 2016/7/18
 * Time: 14:31
 */
public class Dialog_Add_House_Success {
    Context mContext;
    Display display;
    Dialog dialog;

    public Dialog_Add_House_Success(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public Dialog_Add_House_Success builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_money_house, null);

//        Button back = (Button) view.findViewById(R.id.btn_dialog_money_house_back);
        Button go = (Button) view.findViewById(R.id.btn_dialog_money_house_go);

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_dialog_add_house);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().removeActivity(House_Money.class);
                ActivityManager.getInstance().removeActivity(House_Add_Focus_Select.class);
                ActivityManager.getInstance().removeActivity(House_Add_Focus.class);
                ActivityManager.getInstance().removeActivity(House_Add_Room.class);

                dialog.dismiss();
            }
        });

        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(false);   //设置不可点击其他地方取消

        // 调整dialog背景大小
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.95), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public void show() {
        dialog.show();
    }
}
