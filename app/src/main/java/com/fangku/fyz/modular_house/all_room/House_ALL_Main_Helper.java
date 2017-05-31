package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.commonlibrary.widget.dialog.LoadingDialog;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;
import com.fangku.fyz.util.Only_CallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有房间主页帮助类  分担代码  使逻辑更清晰
 * Created by   jie.wang
 * Date: 2016/10/10
 * Time: 11:17
 */
public class House_ALL_Main_Helper {
    Context mContext;
    private TextView  mTvHouseAllBar1, mTvHouseAllBar2, mTvHouseAllBar3;
    LoadingDialog mDialog;
    static int change=0;//当为3的时候关闭dialog

    House_ALL_Main_Helper(Context context,
                          TextView Bar1, TextView Bar2, TextView Bar3, LoadingDialog dialog) {
        mContext = context;
        mTvHouseAllBar1 = Bar1;
        mTvHouseAllBar2 = Bar2;
        mTvHouseAllBar3 = Bar3;
        mDialog = dialog;

    }


    public void getListData_1(String houseID, Only_CallBack callBack) {
        House_All_Main.Data1.clear();
        Map<String, String> map = new HashMap();
        map.put("houseId", houseID);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_RENT_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {

                        House_All_Main.Data1.addAll(response.getData());
                        settype("已出租", House_All_Main.Data1);

                    }

                    @Override
                    protected void onOver() {
                        callBack.isSuccess("查询成功");
                        if(change==2){
                            mDialog.cancel();
                            change=0;
                        }else {
                            ++change;
                        }
                    }

                    @Override
                    protected void onFailed(String message) {

                    }


                });
    }


    public void getListData_2(String houseID, Only_CallBack callBack) {
        House_All_Main.Data2.clear();
        Map<String, String> map = new HashMap();
        map.put("houseId", houseID);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_NO_RENT_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {

                        House_All_Main.Data2.addAll(response.getData());
                        settype("未出租", House_All_Main.Data2);

                    }

                    @Override
                    protected void onOver() {
                        callBack.isSuccess("查询成功");
                        if(change==2){
                            mDialog.cancel();
                            change=0;
                        }else {
                            ++change;
                        }

                    }


                });
    }


    public void getListData_3(String houseID, Only_CallBack callBack) {
        House_All_Main.Data3.clear();
        Map<String, String> map = new HashMap();
        map.put("houseId", houseID);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_EXPIRE_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {

                        House_All_Main.Data3.addAll(response.getData());
                        settype("合同过期", House_All_Main.Data3);
                    }

                    @Override
                    protected void onOver() {
                        callBack.isSuccess("查询成功");
                        if(change==2){
                            mDialog.cancel();
                            change=0;
                        }else {
                            ++change;
                        }

                    }
                });
    }


    private void settype(String name, List<Bean_House_Type_Room.DataBean> data) {
        if (data.size() != 0) {

                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setType(name);
                }


            switch (name) {
                case "已出租":
                    mTvHouseAllBar1.setText("已出租(" + data.size() + ")");
                    break;
                case "未出租":
                    mTvHouseAllBar2.setText("未出租(" + data.size() + ")");

                    break;
                case "合同到期":
                    mTvHouseAllBar3.setText("合同到期(" + data.size() + ")");

                    break;
            }

        } else {

            switch (name) {
                case "已出租":
                    mTvHouseAllBar1.setText("已出租(0)");
                    break;
                case "未出租":
                    mTvHouseAllBar2.setText("未出租(0)");

                    break;
                case "合同到期":
                    mTvHouseAllBar3.setText("合同到期(0)");

                    break;
            }
        }


    }


}
