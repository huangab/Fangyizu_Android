package com.fangku.fyz.modular_hydropower;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;

/**
 * 换表换弹出框
 * Created by bowen.ye
 * Date: 2016/8/13
 * Time: 14:44
 */
public class Dialog_Hydropower_Watch {

    Context mContext;
    Display display;
    Dialog dialog;
    String date;

    public Dialog_Hydropower_Watch(Context mContext,String date) {
        this.mContext = mContext;
        this.date=date;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public Dialog_Hydropower_Watch builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_hydropower_watch, null);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_dialog_hydropower_watch);
        final EditText old = (EditText) view.findViewById(R.id.et_dialog_hydropower_watch_old);
        final EditText now = (EditText) view.findViewById(R.id.et_dialog_hydropower_watch_now);
        Button confirm = (Button) view.findViewById(R.id.btn_dialog_hydropower_watch_confirm);
        Button finish = (Button) view.findViewById(R.id.btn_dialog_hydropower_watch_finish);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (old.getText().toString().equals("") && now.getText().toString().equals("")) {
                    ToastUtil.showShort(mContext, "请输入数据");
                } else {
//                    Hydropower.beanHydropowersList.get(Hydropower.position).setAccScale(Double.parseDouble(old.getText().toString()) - Hydropower.beanHydropowersList.get(Hydropower.position).getThisScale());
                    Hydropower.beanHydropowersList.get(Hydropower.position).setLastScale(Double.parseDouble(old.getText().toString()));
                    Hydropower.beanHydropowersList.get(Hydropower.position).setLastDate(date);
                    Hydropower.beanHydropowersList.get(Hydropower.position).setThisScale(Double.parseDouble(now.getText().toString()));
                    Hydropower.beanHydropowersList.get(Hydropower.position).setThisDate(date);
                    Hydropower.beanHydropowersList.get(Hydropower.position).setFlag("C");
                    Hydropower.beanHydropowersList.get(Hydropower.position).setDemo("换表操作日期为"+date + "新表数据为" + now.getText().toString() + "旧表数据为" + old.getText().toString());
                    Hydropower.beanHydropowersList.get(Hydropower.position).setIsRevise("a");
                    Hydropower.beanHydropowersList.get(Hydropower.position).setIsInput("a");
                    Hydropower.mAdpterHydropowerlist.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public void show() {
        dialog.show();
    }
}
