package com.fangku.fyz.modular_me.real;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseActivity;

import java.io.IOException;

import butterknife.OnClick;

/**
 * 身份证正面扫描
 * Created by bowen.ye
 * Date: 2016/8/24
 * Time: 17:18
 */
public class Me_Real_Front extends MyBaseActivity implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceView surfaceView;

    volatile boolean a = true;

    public static void launch(Context context) {
        Intent mIntent = new Intent(context, Me_Real_Front.class);
        context.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_real_front;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        surfaceView = (SurfaceView) findViewById(R.id.sv_image);
        surfaceView.getHolder()
                .setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(176, 155); //设置Surface分辨率
        surfaceView.getHolder().setKeepScreenOn(true);// 屏幕常亮
        surfaceView.getHolder().addCallback(this);//为SurfaceView的句柄添加一个回调函数
    }

    @Override
    public void getData() {

    }

    @OnClick(R.id.tv_image2_1)
    public void onClick() {
        if (camera != null) {
            // 拍照
            camera.takePicture(null, null, new MyPictureCallback());
            a = false;
        }
    }

    class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                Me_Real.front = BitmapFactory.decodeByteArray(data, 0, data.length);
                Log.i("onPictureTaken", "onPictureTaken: " + Me_Real.front.getWidth() + " " + Me_Real.front.getHeight());
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    // 开始拍照时调用该方法
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // 当surfaceview创建就去打开相机
            camera = Camera.open();
            Camera.Parameters params = camera.getParameters();
            params.setJpegQuality(80);  // 设置照片的质量
            params.setPictureSize(640, 480);
            camera.setParameters(params); // 将参数设置给相机
            // 设置预览显示
            camera.setPreviewDisplay(surfaceView.getHolder());
            camera.setDisplayOrientation(getPreviewDegree(this));
            // 开启预览
            camera.startPreview();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (a) {
                            if (camera != null) {
                                camera.autoFocus(new Camera.AutoFocusCallback() {
                                    @Override
                                    public void onAutoFocus(boolean success, Camera camera) {

                                    }
                                });
                            }
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        } catch (IOException e) {
            displayFrameworkBugMessageAndExit();
            e.printStackTrace();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            LogUtil.i("点击相机", e.getMessage());
            displayFrameworkBugMessageAndExit();
        }
    }


    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(com.xys.libzxing.R.string.app_name2));
        builder.setMessage("权限被禁用,请到安全中心赋予拍照权限!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }


    // 拍照状态变化时调用该方法
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // 停止拍照时调用该方法
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.release(); // 释放照相机
            camera = null;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        a = false;
        if (camera != null) {
            camera.release(); // 释放照相机
            camera = null;
        }
    }
}
