package com.fangku.fyz.modular_house.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.all_room.House_All_zuke_message;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Roomer_List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租客页面适配器
 * Created by bowen.ye
 * Date: 2016/9/22
 * Time: 10:35
 */
public class Adapter_House_All_Zuke_Expandablelist extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Bean_House_All_Roomer_List.DataEntity> room = new ArrayList<>();
    private LayoutInflater inflater;

    public Adapter_House_All_Zuke_Expandablelist(Context mContext, List<Bean_House_All_Roomer_List.DataEntity> room) {
        this.mContext = mContext;
        this.room = room;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return room.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return room.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return room.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolde groupViewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_house_all_zuke_parent, null);
            groupViewHolde = new GroupViewHolde();
            groupViewHolde.mName = (TextView) convertView.findViewById(R.id.tv_item_zuke_parent_name);
            groupViewHolde.mTag = (TextView) convertView.findViewById(R.id.tv_item_zuke_parent_tag);
            groupViewHolde.mSex = (TextView) convertView.findViewById(R.id.tv_item_zuke_parent_sex);
            groupViewHolde.mPhone = (TextView) convertView.findViewById(R.id.tv_item_zuke_parent_phone);
            groupViewHolde.miv = (ImageView) convertView.findViewById(R.id.iv_item_zuke_parent_right);
            convertView.setTag(groupViewHolde);
        } else {
            groupViewHolde = (GroupViewHolde) convertView.getTag();
        }
        groupViewHolde.mName.setText(room.get(groupPosition).getRealName());
        if ("y".equals(room.get(groupPosition).getIsMaster())) {
            groupViewHolde.mTag.setVisibility(View.VISIBLE);
            groupViewHolde.mTag.setText("承担人");
        } else {
            groupViewHolde.mTag.setVisibility(View.GONE);
        }
        groupViewHolde.mSex.setText(room.get(groupPosition).getGender());
        groupViewHolde.mPhone.setText(room.get(groupPosition).getRoomerMoblie());

        if (isExpanded) {//展开状态
            groupViewHolde.miv.setImageResource(R.mipmap.xiala_gray_icon_fang);
        } else {//收起状态
            groupViewHolde.miv.setImageResource(R.mipmap.xiala_gray_icon);

        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        chilrenHolde chilrenHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_house_all_zuke_childview, null);
            chilrenHolde = new chilrenHolde();
            chilrenHolde.mLlPhone = (LinearLayout) convertView.findViewById(R.id.ll_item_zuke_child_phone);
            chilrenHolde.mLlEd = (LinearLayout) convertView.findViewById(R.id.ll_item_zuke_child_ed);
            chilrenHolde.mLlClean = (LinearLayout) convertView.findViewById(R.id.ll_item_zuke_child_clean);
            convertView.setTag(chilrenHolde);
        } else {
            chilrenHolde = (chilrenHolde) convertView.getTag();
        }

        //电话
        chilrenHolde.mLlPhone.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:" + room.get(groupPosition).getRoomerMoblie());
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            mContext.startActivity(intent);
        });

        //修改
        chilrenHolde.mLlEd.setOnClickListener(v -> {
            House_All_zuke_message.launch(mContext, true, room.get(groupPosition).getSysId(), room.get(groupPosition).getRealName(), room.get(groupPosition).getRoomerMoblie(), room.get(groupPosition).getIdCardNo(), String.valueOf(room.get(groupPosition).getWorkPlace()), room.get(groupPosition).getGender(), room.get(groupPosition).getRoomId(), room.get(groupPosition).getHouseId(), room.get(groupPosition).getContractId(), room.get(groupPosition).getLandlordId(), room.get(groupPosition).getIsMaster());
        });

        if ("y".equals(room.get(groupPosition).getIsMaster())) {
            chilrenHolde.mLlClean.setVisibility(View.INVISIBLE);
        } else {
            chilrenHolde.mLlClean.setVisibility(View.VISIBLE);
        }

        //删除
        chilrenHolde.mLlClean.setOnClickListener(v -> {
            delect(room.get(groupPosition).getRoomId());
        });

        return convertView;
    }

    public void delect(String roomerId) {


        Map<String, String> map = new HashMap<>();
        map.put("roomerId", roomerId);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.DELETE_ROOMER, map)
                .execute(new Bean_Callback<BeanResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        notifyDataSetChanged();
                    }

                    @Override
                    protected void onOver() {

                    }
                });

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolde {
        TextView mName;
        TextView mTag;
        TextView mSex;
        TextView mPhone;
        ImageView miv;//三角形
    }

    class chilrenHolde {
        LinearLayout mLlPhone;
        LinearLayout mLlEd;
        LinearLayout mLlClean;
    }
}
