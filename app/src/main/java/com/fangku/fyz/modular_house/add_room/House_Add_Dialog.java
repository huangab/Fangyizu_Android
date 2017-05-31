package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/8/15.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.fangku.commonlibrary.widget.dialog.CustomDialog;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.adapter.Adapter_Huose_GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_Add_Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    private CustomDialog dialog;
    GridView gridView;
    Adapter_Huose_GridView adapter;
    List<String> nums = new ArrayList<>();
    Button btClose;
    TextView txTitle;
    OnItemData mOnItemData;
    int name;

    public void show(Activity mContext, int name) {

        dialog = new CustomDialog(mContext, R.style.AlertDialogStyle, R.layout.house_add_dialog, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

        this.name = name;
        gridView = (GridView) dialog.findViewById(R.id.gv_house_nums);
        btClose = (Button) dialog.findViewById(R.id.bt_huose_dialog_close);
        txTitle = (TextView) dialog.findViewById(R.id.tv_house_dialog_name);

        gridView.setOnItemClickListener(this);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));//屏蔽默认点击效果

        switch (name) {
            case 1:
                txTitle.setText("选择楼层数目");
                break;
            case 2:
                txTitle.setText("选择单层房间数目");
                break;
        }

        btClose.setOnClickListener(this);

        for (int i = 1; i <= 30; i++) {
            nums.add(String.valueOf(i));
        }
        adapter = new Adapter_Huose_GridView(mContext, nums);
        gridView.setAdapter(adapter);
        dialog.show();
    }

    public void SetOnItemListent(OnItemData mOnItemData) {
        this.mOnItemData = mOnItemData;

    }

    @Override
    public void onClick(View v) {
        dialog.cancel();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int i = (int) id;
        mOnItemData.getNums(i + 1, name);
        dialog.dismiss();
    }


    public interface OnItemData {
        public void getNums(int nums, int name);
    }
}
