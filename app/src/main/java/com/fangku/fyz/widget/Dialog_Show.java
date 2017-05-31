package com.fangku.fyz.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.fangku.fyz.MyApplication;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.Only_CallBack;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

/**
 * 弹出框封装
 * Created by   jie.wang
 * Date: 2016/8/17
 * Time: 11:08
 */
public class Dialog_Show {

    public static BaseAnimatorSet bas_in = new BounceEnter();
    public static BaseAnimatorSet bas_out = new FadeExit();


    public static NormalListDialog listDialog;

    public static NormalDialog dialog;

    /**
     * 用于顶部选择楼盘的列表式弹出框
     *
     * @param context
     * @param callBack
     */
    public static void showList(Context context, final Only_CallBack callBack) {
        listDialog = new NormalListDialog(context, MyApplication.stringItems);
        BaseAnimatorSet bas_in = new BounceEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        listDialog.title("请选择")//
                .titleTextSize_SP(Static_Conmom.TITLE_TEXT_SIZE)//
                .titleBgColor(Color.parseColor(Static_Conmom.BACK_COLOR))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(Static_Conmom.DIALOG_TEXT_SIZE)//
                .layoutAnimation(null)
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .cornerRadius(2)
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        listDialog.setOnOperItemClickL((parent, view, position, id) -> {
            listDialog.dismiss();
            callBack.isSuccess("" + position);//返回的是position的string型

        });
    }

    /**
     * 用于本月数值比上月数值小时选择弹出框
     *
     * @param context
     * @param callBack
     */
    public static void contrast(Context context, final Only_CallBack callBack) {
        listDialog = new NormalListDialog(context, MyApplication.hydropowerItems);
        listDialog.title("请选择")//
                .titleTextSize_SP(Static_Conmom.TITLE_TEXT_SIZE)//
                .titleBgColor(Color.parseColor(Static_Conmom.BACK_COLOR))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(Static_Conmom.DIALOG_TEXT_SIZE)//
                .layoutAnimation(null)
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .cornerRadius(2)
                .show();

        listDialog.setOnOperItemClickL((parent, view, position, id) -> {
            listDialog.dismiss();
            callBack.isSuccess("" + position);//返回的是position的string型

        });
    }


    /**
     * 只有1个按钮的文本显示弹出框
     *
     * @param context
     * @param callBack
     */
    public static void showOneButton(Context context, String title, String content, String buttontext, final Only_CallBack callBack) {

        dialog = new NormalDialog(context);
        dialog//
                .style(NormalDialog.STYLE_TWO)//
                .btnNum(1)
                .title(title)
                .titleTextColor(Color.parseColor(Static_Conmom.BACK_COLOR))
                .titleLineColor(Color.parseColor("#ffffff"))
                .contentTextColor(Color.parseColor(Static_Conmom.blue_COLOR))
                .btnTextColor(Color.parseColor(Static_Conmom.blue_COLOR))
                .content(content)//
                .btnText(buttontext)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.superDismiss();
                callBack.isSuccess("");

            }
        });

    }


    /**
     * 有2个按钮的文本显示弹出框
     *
     * @param context
     * @param callBack
     */
    public static void showTwoButton(Context context, String title, String content, String btn1, String btn2, final Only_CallBack callBack) {

        dialog = new NormalDialog(context);
        dialog//
                .style(NormalDialog.STYLE_TWO)//
                .contentGravity(Gravity.CENTER)
                .btnNum(2)
                .title(title)//标题
                .titleTextColor(Color.parseColor(Static_Conmom.BACK_COLOR))
                .contentTextColor(Color.parseColor(Static_Conmom.blue_COLOR))
                .titleLineColor(Color.parseColor("#ffffff"))
                .btnTextColor(Color.parseColor(Static_Conmom.blue_COLOR))

                .content(content)//正文
                .btnText(btn1, btn2)//按钮文字
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
                                  @Override
                                  public void onBtnClick() {
                                      dialog.superDismiss();
                                      callBack.isSuccess("1");

                                  }
                              },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.superDismiss();
                        callBack.isSuccess("2");

                    }
                });

    }


    /**
     * 有3个按钮的文本显示弹出框
     *
     * @param context
     * @param title
     * @param content
     * @param btn1
     * @param btn2
     * @param btn3
     * @param callBack
     */
    public static void showThreeButton(Context context, String title, String content, String btn1, String btn2, String btn3, final Only_CallBack callBack) {
        dialog = new NormalDialog(context);
        dialog.content(content)//
                .title(title)
                .style(NormalDialog.STYLE_TWO)//
                .titleTextColor(Color.parseColor(Static_Conmom.BACK_COLOR))
                .contentTextColor(Color.parseColor(Static_Conmom.blue_COLOR))
                .btnTextColor(Color.parseColor(Static_Conmom.blue_COLOR))
                .titleLineColor(Color.parseColor("#ffffff"))
                .btnNum(3)
                .btnText(btn1, btn2, btn3)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.superDismiss();
                        callBack.isSuccess("1");

                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.superDismiss();
                        callBack.isSuccess("2");

                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.superDismiss();
                        callBack.isSuccess("3");

                    }
                });

    }

}
