package com.fangku.fyz.modular_house.add_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_House_Floors_Number;
import com.fangku.fyz.modular_house.adapter.Adapter_Focus_Select_expandablelist;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加集中式房产
 * 选择楼层房间数
 * Created by   jie.wang
 * Date: 2016/7/22
 * Time: 14:51
 */
public class House_Add_Focus_Select extends MyBaseActivity {

    @Bind(R.id.lv_focus_select)
    ExpandableListView mLvFocusSelect;
    private Context mContext = House_Add_Focus_Select.this;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.bt_focus_select_next)
    Button mBtFocusSelectNext;

    private ArrayList<Bean_House_Floors_Number> mFloorsList;
    private Adapter_Focus_Select_expandablelist Adapter;

    public static House_Add_Focus_Select focusSelect;

    public static void launch(Context context) {
        Intent mIntent = new Intent(context, House_Add_Focus_Select.class);
        context.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.house_add_focus_select;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {


    }

    @Override
    public void initView() {
        focusSelect = this;
        mTvTitle.setText("楼层房间数设置");
        mLvFocusSelect.setGroupIndicator(null);
        mFloorsList = new ArrayList<Bean_House_Floors_Number>();

        //把数据添加到集合中 加载到适配器中
        for (int i = 1; i <= Integer.valueOf(House_Add_Focus.mRoomData.getHouseLevel()); i++) {
            Bean_House_Floors_Number floorsList = new Bean_House_Floors_Number();
            floorsList.setFloor(i);
            floorsList.setIsselect(true);
            ArrayList<Bean_House_Floors_Number.Room> roomList = new ArrayList<Bean_House_Floors_Number.Room>();
            for (int j = 1; j <= Integer.valueOf(House_Add_Focus.mRoomData.getRoomNumber()); j++) {
                Bean_House_Floors_Number.Room addressEntity = new Bean_House_Floors_Number.Room();
                addressEntity.setRoom(i * 100 + j);
                addressEntity.setIsselect(true);
                roomList.add(addressEntity);
            }
            floorsList.setData(roomList);
            mFloorsList.add(floorsList);
        }
        Adapter = new Adapter_Focus_Select_expandablelist(House_Add_Focus_Select.this,
                mFloorsList);
        mLvFocusSelect.setAdapter(Adapter);
    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.tv_title, R.id.btn_back, R.id.bt_focus_select_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_focus_select_next:
                copyList();
                House_Add_HomeRelease.launch(mContext);
                break;
        }
    }


    /**
     *
     */
    private void copyList() {
        List<Bean_House_Floors_Number> num = Adapter.getData();
        //去掉未选中的房间
        for (int i = 0; i < num.size(); i++) {
            for (int j = 0; j < num.get(i).getData().size(); j++) {
                if (num.get(i).getData().get(j).isselect() == false) {
                    num.get(i).getData().remove(num.get(i).getData().get(j));
                    j--;
                }
                if (num.get(i).getData().get(j).getRoom() == 0) {
                    num.get(i).getData().remove(num.get(i).getData().get(j));
                    j--;
                }
            }
        }
        for (int i = 0; i < num.size(); i++) {

        }
        House_Add_Focus.mRoomData.setFloorsAndRooms(num);
    }
}
