package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/9/12.
 */

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fangku.commonlibrary.widget.dialog.CustomDialog;
import com.fangku.fyz.R;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Dialog_UP_Select_House {
    private CustomDialog dialog;
    ListView listView;

    public  Dialog_UP_Select_House(Activity mContext) {
        dialog = new CustomDialog(mContext, R.style.custom_dialog, R.layout.dialog_house_up, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

        listView = (ListView) dialog.findViewById(R.id.lv_house_up);

        Button button = (Button) dialog.findViewById(R.id.bt_huose_up_dialog_close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void show() {
        dialog.show();
    }
}
