package com.fangku.fyz.modular_rent;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseFragment;

import butterknife.Bind;

/**
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Fragment_Rent extends MyBaseFragment {


    @Bind(R.id.wv_1)
    WebView mWv1;
    @Bind(R.id.imageView)
    ImageView mImageView;

    @Override
    public int bindLayout() {
        return R.layout.fragment_rent;
    }

    @Override
    public void createFragment(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mImageView.setVisibility(View.GONE);
        mWv1.getSettings().setJavaScriptEnabled(true);
        mWv1.getSettings().setSupportMultipleWindows(true);
        mWv1.getSettings().setUseWideViewPort(true);

        mWv1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWv1.loadUrl("http://news.163.com/rank/");
        ToastUtil.showShort(mActivity, "网页测试...");


    }


    @Override
    public void getData() {

    }


}
