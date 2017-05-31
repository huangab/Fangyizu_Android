package com.fangku.fyz.modular_house.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_House_Floors_Number;

import java.util.ArrayList;

/**
 * Created by   jie.wang
 * 用于楼层号适配器
 *
 * @author
 */
public class Adapter_Focus_Select_expandablelist extends BaseExpandableListAdapter implements
        OnItemClickListener {
    private Context mContext;
    private ArrayList<Bean_House_Floors_Number> mFloors;
    private LayoutInflater inflater;
    private NoScrollGridView toolbarGrid;


    public Adapter_Focus_Select_expandablelist(Context mContext, ArrayList<Bean_House_Floors_Number> Floors) {
        this.mContext = mContext;
        this.mFloors = Floors;

        //默认在后面添加一个ITEM 为加号
        for (int i = 0; i < mFloors.size(); i++) {
            Bean_House_Floors_Number.Room room = new Bean_House_Floors_Number.Room();
            room.setIsselect(false);
            room.setRoom(0);
            this.mFloors.get(i).getData().add(room);
        }

        inflater = LayoutInflater.from(mContext);
    }

    public ArrayList<Bean_House_Floors_Number> getData() {

        return mFloors;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mFloors.get(groupPosition).getData();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    /**
     * 得到子类视图   (里面初始化gridview)
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */

    private Adapter_Focus_Select_gridview adapter;

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        if (convertView == null)  加了这行 会引发GIRDVIEW的并发
        convertView = inflater.inflate(R.layout.item_mygridview, null);
        toolbarGrid = (NoScrollGridView) convertView
                .findViewById(R.id.gv_toolbar);
//        toolbarGrid.setNumColumns(4);// 设置每行列数
//        toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
        toolbarGrid.setHorizontalSpacing(5);// 水平间隔
        adapter = new Adapter_Focus_Select_gridview((Activity) mContext,
                mFloors.get(groupPosition).getData());
        toolbarGrid
                .setAdapter(adapter);// 设置菜单Adapter
        toolbarGrid.setOnItemClickListener(this);
//        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return mFloors.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return mFloors.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        GroupViewHolde groupViewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_house_add_parent,
                    null);
            groupViewHolde = new GroupViewHolde();
            groupViewHolde.mTvName = (TextView) convertView.findViewById(R.id.tv_floors_number);
            groupViewHolde.mCheck = (CheckBox) convertView.findViewById(R.id.expandablelist_cb_allselect);
            groupViewHolde.miv = (ImageView) convertView.findViewById(R.id.iv_expan_item_right);
            groupViewHolde.mLayout = (LinearLayout) convertView.findViewById(R.id.ll_check_father);
            convertView.setTag(groupViewHolde);
        } else {
            groupViewHolde = (GroupViewHolde) convertView.getTag();
        }

        groupViewHolde.mTvName.setText(mFloors.get(groupPosition)
                .getFloor() + "楼");

        final GroupViewHolde finalGroupViewHolde = groupViewHolde;
        groupViewHolde.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalGroupViewHolde.mCheck.isChecked()) {
                    finalGroupViewHolde.mCheck.setChecked(false);
                    mFloors.get(groupPosition).setIsselect(false);
                    //反选
                    for (int i = 0; i < mFloors.get(groupPosition).getData().size(); i++) {
                        if (mFloors.get(groupPosition).getData().get(i).getRoom() != 0) {
                            mFloors.get(groupPosition).getData().get(i).setIsselect(false);
                        }
                    }
                } else {
                    finalGroupViewHolde.mCheck.setChecked(true);
                    mFloors.get(groupPosition).setIsselect(true);
                    //全选
                    for (int i = 0; i < mFloors.get(groupPosition).getData().size(); i++) {
                        if (mFloors.get(groupPosition).getData().get(i).getRoom() != 0) {
                            mFloors.get(groupPosition).getData().get(i).setIsselect(true);
                        }
                    }

                }
                notifyDataSetChanged();
            }
        });

        // TODO: 2016/7/26  checkbox在listview的用法 :
        // TODO: 2016/7/26  把监听方法 放在 设置是否选中方法前面可以避免  选择状态的错乱
//        groupViewHolde.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                                                             @Override
//                                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                                                 if (isChecked) {
//                                                                     mFloors.get(groupPosition).setIsselect(true);
//                                                                     //全选
//                                                                     for (int i = 0; i < mFloors.get(groupPosition).getData().size(); i++) {
//                                                                         if (mFloors.get(groupPosition).getData().get(i).getRoom_number() != 0) {
//                                                                             mFloors.get(groupPosition).getData().get(i).setIsselect(true);
//                                                                         }
//                                                                     }
//                                                                 } else
//
//                                                                 {
//                                                                     mFloors.get(groupPosition).setIsselect(false);
//                                                                     //反选
//                                                                     for (int i = 0; i < mFloors.get(groupPosition).getData().size(); i++) {
//                                                                         if (mFloors.get(groupPosition).getData().get(i).getRoom_number() != 0) {
//                                                                             mFloors.get(groupPosition).getData().get(i).setIsselect(false);
//                                                                         }
//                                                                     }
//                                                                 }
////                                                                 adapter.notifyDataSetChanged();
//                                                                 notifyDataSetChanged();
//                                                             }
//                                                         }
//
//        );

        //是否选中方法
        if (mFloors.get(groupPosition).isselect()) {
            groupViewHolde.mCheck.setChecked(true);
        } else {
            groupViewHolde.mCheck.setChecked(false);
        }


        if (isExpanded == true) {//展开状态
            groupViewHolde.miv.setImageResource(R.mipmap.xiala_gray_icon_fang);
        } else {//收起状态
            groupViewHolde.miv.setImageResource(R.mipmap.xiala_gray_icon);

        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }

    class GroupViewHolde {
        TextView mTvName;
        CheckBox mCheck;
        ImageView miv;//三角形
        LinearLayout mLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int position, long id) {
        Bean_House_Floors_Number.Room rooms = (Bean_House_Floors_Number.Room) adapterView
                .getItemAtPosition(position);
        if (rooms != null) {

        }

    }


}

