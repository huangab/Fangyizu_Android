package com.fangku.fyz.modular_house.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.all_room.House_All_Heyue;
import com.fangku.fyz.modular_house.all_room.House_All_chuzu;
import com.fangku.fyz.modular_house.all_room.House_All_fangjianxinxi;
import com.fangku.fyz.modular_house.all_room.House_All_huanfang;
import com.fangku.fyz.modular_house.all_room.House_All_shouzujilu;
import com.fangku.fyz.modular_house.all_room.House_All_tuizu;
import com.fangku.fyz.modular_house.all_room.House_All_xuyue;
import com.fangku.fyz.modular_house.all_room.House_All_zhangzu;
import com.fangku.fyz.modular_house.all_room.House_All_zuke;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   jie.wang
 * 用于所有房间的适配器
 *
 * @author
 */
public class Adapter_house_all_expandablelist extends BaseExpandableListAdapter implements
        OnClickListener {
    private Context mContext;
    private List<Bean_House_Type_Room.DataBean> room = new ArrayList<>();
    private LayoutInflater inflater;
    private String mHouseName;


    private int mGroupPosition;

    public Adapter_house_all_expandablelist(Context mContext, String houseName, List<Bean_House_Type_Room.DataBean> room
    ) {
        this.mContext = mContext;
        this.room = room;
        mHouseName = houseName;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return room.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }


    /**
     * todo 子布局
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        mGroupPosition = groupPosition;
        chilrenHolde chilrenHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_house_all_chilren, null);
            chilrenHolde = new chilrenHolde();


            chilrenHolde.mLL1 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item1);
            chilrenHolde.mLL2 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item2);
            chilrenHolde.mLL3 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item3);
            chilrenHolde.mLL4 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item4);
            chilrenHolde.mLL21 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item21);
            chilrenHolde.mLL22 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item22);
            chilrenHolde.mLL23 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item23);
            chilrenHolde.mLL24 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item24);
            chilrenHolde.mLL31 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item31);
            chilrenHolde.mLL32 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item32);
            chilrenHolde.mLL33 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item33);
            chilrenHolde.mLL34 = (LinearLayout) convertView.findViewById(R.id.ll_house_all_item34);
            chilrenHolde.mLL_one = (LinearLayout) convertView.findViewById(R.id.ll_item_house_all_one);
            chilrenHolde.mLL_two = (LinearLayout) convertView.findViewById(R.id.ll_item_house_all_two);
            chilrenHolde.mLL_three = (LinearLayout) convertView.findViewById(R.id.ll_item_house_all_three);
            convertView.setTag(chilrenHolde);
        } else {
            chilrenHolde = (chilrenHolde) convertView.getTag();
        }
        chilrenHolde.mLL1.setOnClickListener(this);
        chilrenHolde.mLL2.setOnClickListener(this);
        chilrenHolde.mLL3.setOnClickListener(this);
        chilrenHolde.mLL4.setOnClickListener(this);
        chilrenHolde.mLL21.setOnClickListener(this);
        chilrenHolde.mLL22.setOnClickListener(this);
        chilrenHolde.mLL23.setOnClickListener(this);
        chilrenHolde.mLL24.setOnClickListener(this);
        chilrenHolde.mLL31.setOnClickListener(this);
        chilrenHolde.mLL32.setOnClickListener(this);
        chilrenHolde.mLL33.setOnClickListener(this);
        chilrenHolde.mLL34.setOnClickListener(this);


        if (room.get(groupPosition).getType().equals("已出租") || room.get(groupPosition).getType().equals("合同到期")) {
            chilrenHolde.mLL_one.setVisibility(View.VISIBLE);
            chilrenHolde.mLL_two.setVisibility(View.VISIBLE);
            chilrenHolde.mLL_three.setVisibility(View.GONE);

        } else {
            chilrenHolde.mLL_one.setVisibility(View.GONE);
            chilrenHolde.mLL_two.setVisibility(View.GONE);
            chilrenHolde.mLL_three.setVisibility(View.VISIBLE);
        }


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
        return room.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub

        return room.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }


    /**
     * 父布局
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        GroupViewHolde groupViewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_house_all_parent,
                    null);
            groupViewHolde = new GroupViewHolde();
            groupViewHolde.mTvName = (TextView) convertView.findViewById(R.id.tv_item_yichuzu_name);
            groupViewHolde.mMoney = (TextView) convertView.findViewById(R.id.tv_item_yichuzu_money);
            groupViewHolde.miv = (ImageView) convertView.findViewById(R.id.iv_item_all_right);
            groupViewHolde.mRoom_num = (TextView) convertView.findViewById(R.id.tv_item_yichuzu_room_num);
            groupViewHolde.mWeixin = (ImageView) convertView.findViewById(R.id.iv_item_yishouzu_weixin);
            convertView.setTag(groupViewHolde);


        } else {
            groupViewHolde = (GroupViewHolde) convertView.getTag();
        }

        groupViewHolde.mTvName.setText(room.get(groupPosition).getRoomerName());
        groupViewHolde.mRoom_num.setText(room.get(groupPosition).getRoomNo());


        if (room.get(groupPosition).getType().equals("已出租") || room.get(groupPosition).getType().equals("合同到期")) {
            groupViewHolde.mWeixin.setVisibility(View.VISIBLE);
            groupViewHolde.mMoney.setText(String.valueOf(room.get(groupPosition).getRent()));


            if (room.get(groupPosition).getType().equals("合同到期")) {
                groupViewHolde.mRoom_num.setText(room.get(groupPosition).getRoomNo() + "(已到期)");
            }
        } else {
            groupViewHolde.mWeixin.setVisibility(View.GONE);
            groupViewHolde.mTvName.setText("无");
            groupViewHolde.mMoney.setText("无");


        }

        if (isExpanded) {//展开状态
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
        TextView mRoom_num;
        TextView mTvName;
        TextView mMoney;
        ImageView miv;//三角形
        ImageView mWeixin;//三角形

    }

    class chilrenHolde {
        LinearLayout mLL1;
        LinearLayout mLL2;
        LinearLayout mLL3;
        LinearLayout mLL4;
        LinearLayout mLL21;
        LinearLayout mLL22;
        LinearLayout mLL23;
        LinearLayout mLL24;
        LinearLayout mLL31;
        LinearLayout mLL32;
        LinearLayout mLL33;
        LinearLayout mLL34;
        LinearLayout mLL_one;   //第一行
        LinearLayout mLL_two;       //第二行
        LinearLayout mLL_three; //第三行


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_house_all_item1:       //退租
                House_All_tuizu.launch(mContext, room.get(mGroupPosition).getRoomId(), room.get(mGroupPosition).getHouseId(), room.get(mGroupPosition).getContractId());
                break;
            case R.id.ll_house_all_item2:           //续约
                House_All_xuyue.launch(mContext, room.get(mGroupPosition).getHouseId(), room.get(mGroupPosition).getContractId(), mHouseName, room.get(mGroupPosition).getRoomId(), room.get(mGroupPosition).getRoomNo());
                break;
            case R.id.ll_house_all_item3://涨租
                House_All_zhangzu.launch(mContext, room.get(mGroupPosition).getContractId());
                break;
            case R.id.ll_house_all_item4://换房
                House_All_huanfang.launch(mContext, room.get(mGroupPosition).getHouseId(), mHouseName, room.get(mGroupPosition).getRoomId(), room.get(mGroupPosition).getRoomerId(), room.get(mGroupPosition).getRoomNo(), room.get(mGroupPosition).getRent(), room.get(mGroupPosition).getDeposit(), room.get(mGroupPosition).getContractId());
                break;
            case R.id.ll_house_all_item21://租客
                House_All_zuke.launch(mContext, room.get(mGroupPosition).getContractId());
                break;
            case R.id.ll_house_all_item22://合约
                House_All_Heyue.launch(mContext, room.get(mGroupPosition).getContractId(), mHouseName, room.get(mGroupPosition).getRoomNo());
                break;
            case R.id.ll_house_all_item23://房间信息
                House_All_fangjianxinxi.launch(mContext, room.get(mGroupPosition).getHouseId(),
                        room.get(mGroupPosition).getRoomId(), mHouseName, room.get(mGroupPosition).getRoomNo(), true);
                break;
            case R.id.ll_house_all_item24://收租记录
                House_All_shouzujilu.launch(mContext, room.get(mGroupPosition).getHouseId(), room.get(mGroupPosition).getRoomId());
                break;
            case R.id.ll_house_all_item31://出租
                House_All_chuzu.launch(mHouseName, room.get(mGroupPosition).getRoomNo(), room.get(mGroupPosition).getRoomId(), room.get(mGroupPosition).getHouseId(), room.get(mGroupPosition).getContractId(), room.get(mGroupPosition).getLandlordId(), room.get(mGroupPosition).getLandlordCardId(), mContext);
                break;
            case R.id.ll_house_all_item32://撤销退租

                break;
            case R.id.ll_house_all_item33://房间信息
                House_All_fangjianxinxi.launch(mContext, room.get(mGroupPosition).getHouseId(),
                        room.get(mGroupPosition).getRoomId(), mHouseName, room.get(mGroupPosition).getRoomNo(), false);

                break;
            case R.id.ll_house_all_item34://收租记录
                House_All_shouzujilu.launch(mContext, room.get(mGroupPosition).getHouseId(), room.get(mGroupPosition).getRoomId());

                break;

        }

    }


}

