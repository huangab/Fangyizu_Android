package com.fangku.fyz.modular_me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.fangku.commonlibrary.utils.PhotoUtil;
import com.fangku.commonlibrary.utils.StorageUtil;
import com.fangku.commonlibrary.utils.ValidateUtil;
import com.fangku.commonlibrary.utils.imageutil.ImageUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.login.Login_Quick_http;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 个人信息
 * Created by bowen.ye
 * Date: 2016/7/13
 * Time: 14:38
 */
public class Me_information extends MyBaseActivity {

    Activity mContext = Me_information.this;
    @Bind(R.id.iv_me_information_image)
    RoundedImageView mIvMeInformationImage;
    @Bind(R.id.tv_me_information_phone)
    TextView mTvMeInformationPhone;
    @Bind(R.id.et_me_information_name)
    EditText mEtMeInformationName;
    @Bind(R.id.et_me_information_position)
    EditText mEtMeInformationPosition;
    @Bind(R.id.et_me_information_email)
    EditText mEtMeInformationEmail;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;

    private String mFilePath;
    private boolean isSuccess;//标志相机拍照是否成功

    private File SaveFile = new File(UserDataUtil.getUserInfo().getData().getImageId());  //头像文件
    private String imageid = UserDataUtil.getUserInfo().getData().getImageId();     //头像ID

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_information.class);
        mContext.startActivity(mIntent);

    }

    @Override
    public int bindLayout() {
        return R.layout.me_information;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("编辑资料");
        mTvMeInformationPhone.setText(UserDataUtil.getUserInfo().getData().getUsername());
        mEtMeInformationName.setText(UserDataUtil.getUserInfo().getData().getName());
        ImageUtil.loadSmallImageCircle(UserDataUtil.getUserInfo().getData().getImageId(), mIvMeInformationImage);


    }

    @Override
    public void getData() {


    }


    @OnClick({R.id.btn_back, R.id.tv_title_right, R.id.ll_select_head, R.id.bt_information_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_information_ok:
                updataUser();//更新用户信息

                break;
            case R.id.ll_select_head:
                getHeadImage();
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title_right:


                break;
        }
    }

    /**
     * 更新用户信息
     */
    private void updataUser() {
        mLoadingDialog.show(mContext, "正在更新..");
        if (!ValidateUtil.isEmail(mEtMeInformationEmail.getText().toString())) {//如果EMAIL不是正确的格式,便把他赋为空
            mEtMeInformationEmail.setText("");
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", mTvMeInformationPhone.getText().toString());
        map.put("imageId", imageid);
        map.put("Name", mEtMeInformationName.getText().toString());
        map.put("email", mEtMeInformationEmail.getText().toString());


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.UPDATE_USER_DETAIL, map)
                .execute(new Bean_Callback<BeanResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        //更新用户信息
                        UserEntity entity = UserDataUtil.getUserInfo();
                        entity.getData().setImageId(imageid);
                        entity.getData().setName(mEtMeInformationName.getText().toString());
                        UserDataUtil.updateUserInfo(entity);
                        Static_Conmom.IsUpdate_Head = true;
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });



    }

    /**
     * 获取用户信息
     */

    /**
     * 头像弹出框
     */
    public void getHeadImage() {
        final String[] stringItems = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("请选择设置照片方式")//
                .titleTextSize_SP(14.5f)//
                .itemTextColor(R.color.background_color)
                .cancelText(R.color.background_color)
                .titleTextColor(R.color.black)
                .itemHeight(55)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //拍一张
                        mFilePath = StorageUtil.IMAGE_DIR + System.currentTimeMillis() + ".jpg";
                        isSuccess = PhotoUtil.takePhoto(mContext, mFilePath, Static_Conmom.ONLY_ONE_CAMERA);
                        break;

                    case 1://相册
                        PhotoUtil.openAlbumSelectPhoto(mContext, Static_Conmom.ONLY_ONE_ALBUM);
                        break;

                }
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //相册选择单张图片
        if (requestCode == Static_Conmom.ONLY_ONE_ALBUM) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表   path.get(0)为得到的图片路径
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);


                //图片裁剪
                PhotoUtil.startCorpImage(Me_information.this, path.get(0), 300, 300, Static_Conmom.TAKE_PHOTO);

            }
        }

        //调用系统相机
        if (requestCode == Static_Conmom.ONLY_ONE_CAMERA) {
            //某些机型在这里是得不到data的，且data取到的数据也是压缩过的图，所以我们不用data去取

            if (isSuccess) {
                //这里是根据我们生成图片的地址去获取原图，需要对图片进行压缩
                Bitmap bitmap = PhotoUtil.getSmallBitmap(mFilePath, 800, 800);//压缩图片不让图片大小大于1M


                //图片裁剪`
                PhotoUtil.startCorpImage(Me_information.this, mFilePath, 300, 300, Static_Conmom.TAKE_PHOTO);
            }
        }

        //调用裁剪功能
        if (requestCode == Static_Conmom.TAKE_PHOTO) {
            if (data != null) {
                //这里拿到的图片只是压缩图
                Bitmap cropBitmap = data.getParcelableExtra("data");
                mIvMeInformationImage.setImageBitmap(cropBitmap);
                mIvMeInformationImage.setOval(true);
                mIvMeInformationImage.setCornerRadius(20);
                SaveFile = CommonUtil.saveBitmapFile(cropBitmap);

                //上传头像
                Login_Quick_http.upload_head_img(mContext, SaveFile, new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        imageid = result;
                    }
                });
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
