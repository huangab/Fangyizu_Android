package com.fangku.fyz.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.PhotoUtil;
import com.fangku.commonlibrary.utils.StorageUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.ValidateUtil;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 15:32
 */
public class Login_Register extends MyBaseActivity {

    public static Login_Register register_this;//由于不能正在网络回调用使用activitymanage关闭 ,所以手动定义一个来finish


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.rl_title_top)
    RelativeLayout mRlTitleTop;
    @Bind(R.id.iv_reg_head_img)
    RoundedImageView mIvRegHeadImg;
    @Bind(R.id.et_reg_phonenumber)
    EditText mEtRegPhonenumber;


    @Bind(R.id.et_reg_pwd_1)
    EditText mEtRegPwd1;
    @Bind(R.id.et_reg_user_code)
    EditText mEtRegUserCode;


    @Bind(R.id.cb_register_check)
    CheckBox mCbRegisterCheck;
    @Bind(R.id.ll_register_check)
    LinearLayout mLlRegisterCheck;


    private String mFilePath;
    private boolean isSuccess;//标志相机拍照是否成功

    private File SaveFile = new File(Static_Conmom.IMAGE);  //头像文件
    private String mPhone;      //手机号
    private String mPWD1;       //密码
    private String mUserCode;       //推荐码

    private Activity mContext = Login_Register.this;


    @Override
    public int bindLayout() {
        return R.layout.login_register;

    }

    @Override
    public void createActivity(Bundle savedInstanceState) {
    }

    @Override
    public void initView() {
        register_this = this;
        isSuccess = false;
        mFilePath = "默认头像";

        mTvTitle.setText("用户注册");


    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.iv_reg_head_img, R.id.bt_reg_next, R.id.ll_register_check})
    public void onClick(View view) {
        mPhone = mEtRegPhonenumber.getText().toString().trim();
        mPWD1 = mEtRegPwd1.getText().toString().trim();
        mUserCode = mEtRegUserCode.getText().toString().trim();

        switch (view.getId()) {
            case R.id.ll_register_check:        //明文显示
                CommonUtil.PasswordTrue(mCbRegisterCheck, mEtRegPwd1);

                break;
            case R.id.btn_back://返回

                finish();
                break;
            case R.id.iv_reg_head_img:

                getHeadImage();//弹出头像选择对话框

                break;


            case R.id.bt_reg_next://下一步
                if (!ValidateUtil.isMobilePhone(mPhone)) {
                    ToastUtil.showShort(mContext, "请输入正确的手机号码!");
                    return;
                }
                if (!CommonUtil.isHardPassword(mPWD1)) {
                    ToastUtil.showShort(mContext, "密码长度为8-20,必须带有英文字母!");

                    return;
                }
                Login_Quick_http.isExistForMobile(mContext, mLoadingDialog, mPhone, mPWD1, mUserCode, SaveFile,
                        result -> {
                            isexist();//弹出是否发送对话框
                        });


                break;
        }
    }


    //弹出是否发送对话框
    private void isexist() {

        String new_phone = CommonUtil.phone_set_null(mEtRegPhonenumber.getText().toString());

        final NormalDialog dialog = new NormalDialog(mContext);
        dialog.content("向手机号" + new_phone + "发送验证短信?")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .show();

        dialog.setOnBtnClickL(
                () -> dialog.dismiss(),
                () -> {

                    if (MyApplication.time_num != Static_Conmom.TIME_SIZE * 1000) {
                        ToastUtil.showShort(mContext, "还需要" + MyApplication.time_num / 1000 + "秒才能再次获取验证码!");

                        return;
                    }

                    Login_Quick_http.message(mContext, mPhone, mLoadingDialog, "/1", new Only_CallBack() {
                        @Override
                        public void isSuccess(String result) {
                            Login_Register_2.lanuch(mContext, mPhone, mPWD1, mUserCode, SaveFile.toString()
                            );
                            dialog.dismiss();

                        }
                    });
                    dialog.dismiss();

                });
    }

    /**
     * 头像弹出框
     */
    public void getHeadImage() {
        final String[] stringItems = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("请选择设置照片方式")//
                .titleTextSize_SP(14.5f)//
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
                PhotoUtil.startCorpImage(Login_Register.this, path.get(0), 300, 300, Static_Conmom.TAKE_PHOTO);


            }
        }

        //调用系统相机
        if (requestCode == Static_Conmom.ONLY_ONE_CAMERA) {
            //某些机型在这里是得不到data的，且data取到的数据也是压缩过的图，所以我们不用data去取

            if (isSuccess) {
                //这里是根据我们生成图片的地址去获取原图，需要对图片进行压缩
//                Bitmap bitmap = PhotoUtil.getSmallBitmap(mFilePath, 1024, 1024);//压缩图片不让图片大小大于1M


                //图片裁剪`
                PhotoUtil.startCorpImage(Login_Register.this, mFilePath, 300, 300, Static_Conmom.TAKE_PHOTO);
            }
        }

        //调用裁剪功能
        if (requestCode == Static_Conmom.TAKE_PHOTO) {
            if (data != null) {
                //这里拿到的图片只是压缩图
                Bitmap cropBitmap = data.getParcelableExtra("data");
                mIvRegHeadImg.setImageBitmap(cropBitmap);
                mIvRegHeadImg.setOval(true);
                mIvRegHeadImg.setCornerRadius(20);
                SaveFile = CommonUtil.saveBitmapFile(cropBitmap);
                Log.i("是否成功裁剪", SaveFile.toString());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
