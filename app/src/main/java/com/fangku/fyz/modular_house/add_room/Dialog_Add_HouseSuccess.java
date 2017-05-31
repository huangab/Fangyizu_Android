package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/8/30.
 */

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.widget.dialog.CustomDialog;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.Fragment_House;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Dialog_Add_HouseSuccess {
    private CustomDialog dialog;

    public void show(final Activity mContext, String HouseData) {

        dialog = new CustomDialog(mContext, R.style.custom_dialog, R.layout.dialog_money_house, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

        TextView textView = (TextView) dialog.findViewById(R.id.tv_addhouse_Success);

        textView.setText(HouseData);

        Button button = (Button) dialog.findViewById(R.id.btn_dialog_money_house_go);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ActivityManager.getInstance().removeActivity(House_Add_Focus_Select.class);
                ActivityManager.getInstance().removeActivity(House_Money.class);
                ActivityManager.getInstance().removeActivity(House_Add_Focus.class);
                ActivityManager.getInstance().removeActivity(House_Add_Dispersed.class);
                ActivityManager.getInstance().removeActivity(House_Add_HomeRelease.class);
                Fragment_House.isUpdate=true;



            }
        });

        dialog.show();
    }


    public void cancel() {
        dialog.dismiss();
    }

}
