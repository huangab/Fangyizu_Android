package com.fangku.fyz.modular_me.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_me.adapter.Adapter_Message;
import com.fangku.fyz.modular_me.bean.Bean_Me_Message;
import com.fangku.fyz.util.MyBaseActivity;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 消息中心
 * Created by   jie.wang
 * Date: 2016/7/14
 * Time: 10:49
 */
public class Me_Message_Center extends MyBaseActivity implements AdapterView.OnItemClickListener {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.lv_me_message)
    ListView mLvMeMessage;
    @Bind(R.id.sv_me_message)
    SpringView mSvMeMessage;

    Context mContext = Me_Message_Center.this;

    private Adapter_Message mAdapter;
    List<Bean_Me_Message.DataBean> mList = new ArrayList<>();

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Message_Center.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_message_center;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("消息中心");

        mAdapter = new Adapter_Message(Me_Message_Center.this, mList);

        mLvMeMessage.setAdapter(mAdapter);
        mSvMeMessage.setType(SpringView.Type.FOLLOW);
        mSvMeMessage.setHeader(new DefaultHeader(mContext));
        mSvMeMessage.setFooter(new DefaultFooter(mContext));

        mSvMeMessage.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getData();


            }

            @Override
            public void onLoadmore() {
                ToastUtil.showShort(mContext, "没有数据了!");
                mSvMeMessage.onFinishFreshAndLoad();

            }
        });


        mLvMeMessage.setOnItemClickListener(this);
    }

    @Override
    public void getData() {
        mLoadingDialog.show(mContext);


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GET_MY_MESSAGE_LIST)
                .execute(new BeanList_Callback<Bean_Me_Message>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_Me_Message response, String message) {
                        mList.addAll(response.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                        mSvMeMessage.onFinishFreshAndLoad();
                    }
                });
    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bean_Me_Message.DataBean list = mAdapter.getList().get(position);
        Me_Message_Item.launch(mContext, list.getPublish(), list.getTitle(), list.getPublishDate(), list.getPublishTime(), list.getMsgContent());

    }
}
