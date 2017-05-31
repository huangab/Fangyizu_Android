package com.fangku.fyz.modular_house;

import android.content.Context;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.commonlibrary.widget.dialog.LoadingDialog;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;
import com.fangku.fyz.util.Only_CallBack;

import java.util.HashMap;
import java.util.Map;

import static com.fangku.fyz.modular_house.Fragment_House.houseID;

/**
 * 主页帮助类  分担代码
 * Created by   jie.wang
 * Date: 2016/10/10
 * Time: 9:45
 */
public class Fragment_House_helper {
    Context mContext;

    LoadingDialog mLoadingDialog;

    TextView mTvHouseLeft, mTvHouseRight;

    public Fragment_House_helper(Context context, TextView mTvHouseLeft, TextView mTvHouseRight) {
        mContext = context;

        this.mTvHouseLeft = mTvHouseLeft;
        this.mTvHouseRight = mTvHouseRight;
        mLoadingDialog = new LoadingDialog();
    }

    //获取左边
    public void getDataLeft(Only_CallBack callback) {

        Map<String, String> map = new HashMap();
        map.put("houseId", houseID);
        Fragment_House.leftData.clear();
        new PostUtil()
                .Post_Bean(Url_final.QUERY_NO_TAXES_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {


                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {

                        Fragment_House.leftData.addAll(response.getData());
                        for (int i = 0; i < Fragment_House.leftData.size(); i++) {
                            Fragment_House.leftData.get(i).setType("未交租");
                        }
                        Fragment_House.leftData.get(0).setSize(Fragment_House.leftData.size()); //第0项目保存着这一类型的房间数量
                        mTvHouseLeft.setText("未交租(" + Fragment_House.leftData.get(0).getSize() + ")");

                    }

                    @Override
                    protected void onOver() {
                        callback.isSuccess("成功");

                        mLoadingDialog.cancel();
                    }

                    @Override
                    protected void onSize_0() {
                        mTvHouseLeft.setText("未交租(0)");

                    }


                });

    }

    //获取右边
    public void getDataRight(Only_CallBack callback) {
        Map<String, String> map = new HashMap();
        map.put("houseId", houseID);
        Fragment_House.rightData.clear();
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_NO_RENT_AND_CONTRACTEND_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {
                        Fragment_House.rightData.addAll(response.getData());
                        for (int i = 0; i < Fragment_House.rightData.size(); i++) {
                            Fragment_House.rightData.get(i).setType("未出租");
                        }
                        Fragment_House.rightData.get(0).setSize(Fragment_House.rightData.size()); //第0项目保存着这一类型的房间数量
                        mTvHouseRight.setText("未出租(" + Fragment_House.rightData.get(0).getSize() + ")");


                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                        callback.isSuccess("成功");
                    }

                    @Override
                    protected void onSize_0() {
                        mTvHouseRight.setText("未出租(0)");

                    }
                });
    }


}
