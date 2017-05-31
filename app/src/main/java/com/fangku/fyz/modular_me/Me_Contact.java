package com.fangku.fyz.modular_me;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_me.bean.Bean_Me_Contact;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 联系客服页面
 * Created by   jie.wang
 * Date: 2016/7/14
 * Time: 10:49
 */
public class Me_Contact extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.ll_contact_contact)
    LinearLayout mLlContactContact;
    @Bind(R.id.ll_contact_qq)
    LinearLayout mLlContactQq;
    @Bind(R.id.ll_contact_weixin)
    LinearLayout mLlContactWeixin;
    @Bind(R.id.ll_contact_phone)
    LinearLayout mLlContactPhone;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Contact.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_contact;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("客服中心");
    }

    @Override
    public void getData() {
        mLoadingDialog.show(mContext);


        new PostUtil()
                .Post_Bean(Url_final.GET_CONTACT)
                .execute(new Bean_Callback<Bean_Me_Contact>(mContext) {


                    @Override
                    protected void onSuccess_Code200(Bean_Me_Contact response, String message) {

                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });


    }


    @OnClick({R.id.btn_back, R.id.ll_contact_contact, R.id.ll_contact_qq, R.id.ll_contact_weixin, R.id.ll_contact_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:

                finish();

                break;
            case R.id.ll_contact_contact:
                break;
            case R.id.ll_contact_qq:

                if (isQQClientAvailable(mContext)) {//QQ是否已经安装
                    String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=32669744&version=1";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                } else {
                    ToastUtil.showShort(mContext, "您尚未安装QQ客户端!");
                }
                break;

            case R.id.ll_contact_weixin:


                if (isWeixinAvilible(this)) {//微信是否已经安装
                    copy("32669744", mContext);
                    ToastUtil.showLong(mContext, "号码已复制,请手动添加客服人员为好友!");
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                    startActivity(intent);

                } else {
                    ToastUtil.showShort(mContext, "您尚未安装微信客户端!");
                }

                break;
            case R.id.ll_contact_phone://打电话

                Intent in2 = new Intent();
                in2.setAction(Intent.ACTION_DIAL);//指定意图动作
                in2.setData(Uri.parse("tel:1836380000"));//指定电话号码
                in2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in2);
                break;
        }
    }


    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param text
     */
    public static void copy(String text, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(text);//6.0之后可能不适用
    }


    /**
     * 判断 用户是否安装QQ客户端
     */
    public boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断 用户是否安装微信客户端
     */
    public boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

}
