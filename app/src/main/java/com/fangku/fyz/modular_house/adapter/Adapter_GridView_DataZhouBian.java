package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.adapter.BaseListAdapter;
import com.fangku.commonlibrary.adapter.ViewHolder;
import com.fangku.commonlibrary.utils.imageutil.ImageUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.sql.Sql_Table_Bean_ListData_Add;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Adapter_GridView_DataZhouBian extends BaseListAdapter<Bean_ListData> {
    LayoutInflater layoutInflater;
    Sql_Table_Bean_ListData_Add table_bean_listData_add;
    List<String> listData = new ArrayList<>();
    boolean needSql = true;

    public Adapter_GridView_DataZhouBian(Activity context, List<Bean_ListData> list) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
        table_bean_listData_add = new Sql_Table_Bean_ListData_Add(mContext);
    }

    public Adapter_GridView_DataZhouBian(Activity context, List<Bean_ListData> list, boolean needSQl) {
        super(context, list);
        layoutInflater = LayoutInflater.from(context);
        table_bean_listData_add = new Sql_Table_Bean_ListData_Add(mContext);
        needSql = needSQl;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (needSql)
            listData = table_bean_listData_add.select();

        if (listData.size() > 0) {
            setOldData();
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.house_add_focus_gridview_layout, null);
        }
        ImageView imageView1 = ViewHolder.get(convertView, R.id.iv_focus_Zoubian_image);

        TextView textView = ViewHolder.get(convertView, R.id.tv_focus_Zoubian_name);

        CheckBox checkBox = ViewHolder.get(convertView, R.id.ck_zhoubian);

        checkBox.setChecked(list.get(position).isCheck());

        LinearLayout linearLayout = ViewHolder.get(convertView, R.id.ll_zhoubian);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
                if (checkBox.isChecked())
                    list.get(position).setIsCheck(true);
                else
                    list.get(position).setIsCheck(false);
            }
        });

        ImageUtil.loadSmallImage(list.get(position).getAttr1(), imageView1);

        textView.setText(list.get(position).getName().toString());

        return convertView;
    }

    /**
     * 加载上次的数据
     */
    private void setOldData() {
        for (int i = 0; i < listData.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (listData.get(i).equals(list.get(j).getName())) {
                    list.get(j).setIsCheck(true);
                }
            }
        }
    }
}
