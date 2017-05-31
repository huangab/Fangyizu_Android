package com.fangku.fyz.modular_house.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_AddCost_Type_DaClass;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_AddCost_Type_XiaoClass;
import com.fangku.fyz.modular_house.bean.Bean_House_All_AddCost_Type;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Tuizu_Rent;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型选择弹出框
 * Created by bowen.ye
 * Date: 2016/9/5
 * Time: 15:20
 */
public class Dialog_All_AddCost_Type {
    Activity mContext;
    Display display;
    Dialog dialog;
    Bean_House_All_AddCost_Type mDaClass;
    Bean_House_All_AddCost_Type mXiaoClass;
    Adapter_House_All_AddCost_Type_DaClass mDaAdapter;
    Adapter_House_All_AddCost_Type_XiaoClass mXiaoAdapter;
    List<Bean_House_All_AddCost_Type> mDaClassList = new ArrayList<>();
    List<Bean_House_All_AddCost_Type> mXiaoClassList = new ArrayList<>();
    TextView mTextView;
    EditText mEditText;
    public static String type="0";
    public static String name;

    public Dialog_All_AddCost_Type(Activity mContext, TextView mTextView, EditText mEditText) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.mTextView = mTextView;
        this.mEditText = mEditText;
    }

    public Dialog_All_AddCost_Type builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_all_addcost_type, null);

        ListView mLvDaClass = (ListView) view.findViewById(R.id.lv_dialog_all_addcost_type_daClass);
        ListView mLvXiaoClass = (ListView) view.findViewById(R.id.lv_dialog_all_addcost_type_xiaoClass);

        String[] typeDaClass = {"租金", "固定费用", "仪表费用", "其他"};
        for (int i = 0; i < 4; i++) {
            mDaClass = new Bean_House_All_AddCost_Type();
            if (i == 0) {
                mDaClass.setIsClick(true);
            }
            mDaClass.setType(typeDaClass[i]);
            mDaClassList.add(mDaClass);
        }

        String[] typeXiaoClass = {"租金", "押金"};
        for (int i = 0; i < 2; i++) {
            mXiaoClass = new Bean_House_All_AddCost_Type();
            mXiaoClass.setType(typeXiaoClass[i]);
            mXiaoClassList.add(mXiaoClass);
        }

        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        //小类适配器添加
        mXiaoAdapter = new Adapter_House_All_AddCost_Type_XiaoClass(mContext, mXiaoClassList,mTextView,mEditText,dialog);
        mLvXiaoClass.setAdapter(mXiaoAdapter);

        //大类适配器添加
        mDaAdapter = new Adapter_House_All_AddCost_Type_DaClass(mContext, mDaClassList, mXiaoAdapter, mXiaoClassList);
        mLvDaClass.setAdapter(mDaAdapter);

//        // 调整dialog背景大小
//        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
//                .getWidth()), LinearLayout.LayoutParams.WRAP_CONTENT));

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.custom_dialog);  //添加动画
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity getBean(){
        Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity mBean=new Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity();
        mBean.setChargeType(type);
        mBean.setCostName(name);
        return mBean;
    }

}
