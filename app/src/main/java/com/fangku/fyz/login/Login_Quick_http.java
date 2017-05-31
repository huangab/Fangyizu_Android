package com.fangku.fyz.login;

import android.content.Context;
import android.text.TextUtils;

import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.ValidateUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.callback.Upload_CallBack;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.commonlibrary.utils.security.Base64;
import com.fangku.commonlibrary.widget.dialog.LoadingDialog;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_getHead_result;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.Only_CallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 登录模块  网络接口集成类
 * Created by   jie.wang
 * Date: 2016/6/28
 * Time: 15:20
 */
public class Login_Quick_http {


    //用于一键登录
    public static void Login(final Context context, String username, final String password, final Only_CallBack callBack) {
        if (TextUtils.isEmpty(username.trim()) && TextUtils.isEmpty(password.trim())) {
            ToastUtil.showShort(context, "请输入完整的帐号密码!");
            callBack.isSuccess("错误");
            return;
        }
        if (!ValidateUtil.isMobilePhone(username)) {
            ToastUtil.showShort(context, "请输入正确的手机号!");
            callBack.isSuccess("错误");
            return;
        }
        if ((password.length() < 8)) {
            ToastUtil.showShort(context, "密码不足8位!");
            callBack.isSuccess("错误");
            return;
        }

        new PostUtil()
                .Post_Bean(Url_final.LOGIN + username + "/" + Base64.encode(password.getBytes()) + "/" + Static_Conmom.VERSION)
                .execute(new Bean_Callback<UserEntity>(context) {
                    @Override
                    protected void onSuccess_Code200(UserEntity response, String message) {
                        UserEntity entity = response;
                        entity.getData().setPassword(Base64.encode(password.getBytes()));
                        UserDataUtil.updateUserInfo(entity);
                        callBack.isSuccess("登录成功");
                    }

                    @Override
                    protected void onOver() {
                        callBack.isSuccess("登录失败");
                    }
                });

    }


    //验证手机号   先验证手机号是否存在
    public static void isExistForMobile(final Context context, final LoadingDialog dialog, final String phonenumber, final String pwd, final String usercode, final File imageFile, final Only_CallBack callBack) {
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort(context, "手机号不能为空");
            return;
        }
        if (!ValidateUtil.isMobilePhone(phonenumber)) {
            ToastUtil.showShort(context, "请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort(context, "密码不能为空!");

            return;
        }
        if (!CommonUtil.isHardPassword(pwd)) {
            ToastUtil.showShort(context, "密码长度为8-20,必须带有英文字母!");

            return;
        }

        dialog.show(context);//显示加载框

        OkHttpUtils.post().url(Url_final.CHECK_PHONE + phonenumber).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("验证手机号错误", e.getMessage());
                        ToastUtil.showShort(context, R.string.will_dopost_onFailed);
                        dialog.cancel();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BaseResponse bean = JsonMananger.jsonToBean(response, BaseResponse.class);
                        if (bean != null) {
                            if ("500".equals(bean.getCode())) {//500为手机号不存在


                                upload_head_img(context, imageFile, result1 -> {
                                    LogUtil.i(result1 + "");

                                    // TODO: 2016/8/8  访问暂存表
                                    Map<String, String> map = new HashMap<String, String>();
                                    map.put("mobile", phonenumber);
                                    map.put("password", Base64.encode(pwd.getBytes()));
                                    map.put("recommendCode", usercode);
                                    map.put("imageId", result1);
                                    PostUtil postUtil = new PostUtil();
                                    postUtil.Post_Bean(Url_final.SAVE, map)
                                            .execute(new Bean_Callback<BeanResponse>(context) {
                                                @Override
                                                protected void onSuccess_Code200(BeanResponse response, String message) {
                                                    callBack.isSuccess(response.getMessage());
                                                }

                                                @Override
                                                protected void onOver() {
                                                    dialog.cancel();//取消加载框
                                                }
                                            });
                                });
                            } else {
                                ToastUtil.showShort(context, bean.getMessage());

                            }
                        }
                    }
                });

    }


    //获取验证码
    public static void message(final Context context, String phone, final LoadingDialog dialog, String type, final Only_CallBack callBack) {
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GETCODE + phone + type).execute(new Bean_Callback<BaseResponse>(context) {
            @Override
            protected void onSuccess_Code200(BaseResponse response, String message) {
                callBack.isSuccess("发送成功!");
            }

            @Override
            protected void onOver() {
                dialog.cancel();
            }
        });

    }


    //上传头像
    public static void upload_head_img(final Context context, File savefile, final Only_CallBack callBack) {


        if (savefile.toString().equals(Static_Conmom.IMAGE)) {
            callBack.isSuccess(Static_Conmom.IMAGE);
            return;
        }

        //上传单张图片
        PostUtil postUtil = new PostUtil();
        postUtil.Post_uploadFile(Url_final.UPLOAD_HEAD_IMG, savefile.toString(), new Upload_CallBack<String>() {
            @Override
            public void inProgress(float progress, long total) {

            }

            @Override
            public void onError(Exception e) {
                ToastUtil.showShort(context, "头像上传失败,请检查网络!");
            }

            @Override
            public void is200(String response) {
                Bean_getHead_result head_result = JsonMananger.jsonToBean(response, Bean_getHead_result.class);
                LogUtil.i("上传得到的头像ID", head_result.getData().getSysId() + " " + head_result.getMessage());
                callBack.isSuccess(head_result.getData().getSysId());
            }

            @Override
            public void not200(String message) {
                ToastUtil.showShort(context, message);
            }


        });


//上传多个图片示例
//        Map<String,File> fileMap=new HashMap<>();
//        fileMap.put("file1.png",savefile);
//        fileMap.put("file2.png",savefile);
//        fileMap.put("file3.png",savefile);
//
//        PostUtil postUtil = new PostUtil();
//        postUtil.Post_uploadFile(Url_final.UPLOAD_HEAD_IMG,fileMap, new Upload_CallBack<String>() {
//            @Override
//            public void inProgress(float progress, long total) {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//                ToastUtil.showShort(context, "头像上传失败,请检查网络!");
//            }
//
//            @Override
//            public void is200(String response) {
//                Bean_getHead_result head_result = JsonMananger.jsonToBean(response, Bean_getHead_result.class);
//                LogUtil.i("上传得到的头像ID", head_result.getData().getSysId() + " " + head_result.getMessage());
//                callBack.isSuccess(head_result.getData().getSysId());
//            }
//
//            @Override
//            public void not200(String message) {
//                ToastUtil.showShort(context, message);
//            }
//
//
//        });
    }

    //注册步骤   注册操作

    public static void register(final Context context, final LoadingDialog dialog, String phone, String messagecode, final Only_CallBack callBack) {


        if (messagecode.length() < 6) {
            ToastUtil.showShort(context, "验证码为6位数字!");
            return;
        }


        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("code", messagecode);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.REGISTER, map)
                .execute(new Bean_Callback<BeanResponse>(context) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        callBack.isSuccess(response.getMessage());
                    }

                    @Override
                    protected void onOver() {
                        dialog.cancel();
                    }
                });

    }

    //适用于修改密码
    public static void forget(final Context context, final LoadingDialog dialog, String phone, String code, String pwd, String realname, final Only_CallBack callBack) {

        if (code.length() < 6) {
            ToastUtil.showShort(context, "验证码为6位数字!");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("validCode", code);
        map.put("password", Base64.encode(pwd.getBytes()));
        map.put("realName", realname);
        dialog.show(context);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.FORGET_CHANGE_PASSWORD, map)
                .execute(new Bean_Callback<BaseResponse>(context) {
                    @Override
                    protected void onSuccess_Code200(BaseResponse response, String message) {
                        callBack.isSuccess("成功");
                    }

                    @Override
                    protected void onOver() {
                        dialog.cancel();
                    }
                });


    }
}
