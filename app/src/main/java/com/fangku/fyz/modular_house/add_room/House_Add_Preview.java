package com.fangku.fyz.modular_house.add_room;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseActivity;
import com.fangku.commonlibrary.utils.imageutil.ImageUtil;
import com.fangku.fyz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 显示图片的viewpager
 */
public class House_Add_Preview extends BaseActivity implements ViewPager.OnPageChangeListener {


    @Bind(R.id.preview_vp)
    ViewPager mPreviewVp;
    @Bind(R.id.tv_preview_index)
    TextView mTvPreviewIndex;

    private ArrayList<String> mPics;
    private List<ImageView> mImgList;

    @Override
    public int bindLayout() {
        return R.layout.activity_house_add_preview;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {
        mPics = getIntent().getStringArrayListExtra("pics");
        int index = getIntent().getIntExtra("index", 0);
        mImgList = new ArrayList<>();
        for (int i = 0; i < mPics.size(); i++) {
            ImageView iv = new ImageView(House_Add_Preview.this);
//            Bitmap smallBitmap = PhotoUtil.getSmallBitmap(picPath, 1024, 1024);
//            iv.setImageBitmap(smallBitmap);


            ImageUtil.loadFile(mPics.get(i), iv);

            mImgList.add(iv);
        }
        mPreviewVp.setAdapter(new PicAdapter());
        mPreviewVp.addOnPageChangeListener(this);
        mPreviewVp.setCurrentItem(index);
        onPageSelected(index);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvPreviewIndex.setText((position + 1) + "/" + mPics.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPics.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImgList.get(position);
            container.addView(imageView);
//            position = position + 1;
//            mTvPreviewIndex.setText( position + "/" + mPics.size());
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageUtil.clearMemory(mContext);


    }
}
