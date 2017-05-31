package com.fangku.fyz.constant;

/**
 * Created by   jie.wang
 * Date: 2016/8/2
 * Time: 20:32
 */
public class Static_Conmom {

    //版本号
    public static final String VERSION = "0.0.3";


    //广播表识
    public static final String ACTION_QUIT = "action.QuitFyz";

    //短信定时时间
    public static final int TIME_SIZE = 120;

    //第三方登录类型
    public static String ThreeLogin_Type = "";


    //第三方DIALOG开始

    //背景色黑色调
    public static final String BACK_COLOR = "#384248";
    //背景色暗黑蓝色调
    public static final String blue_COLOR = "#3b7299";
    //列表弹出框item字体大小
    public static final int DIALOG_TEXT_SIZE = 15;
    //列表弹出框TITLE    字体大小
    public static final int TITLE_TEXT_SIZE = 18;

    //第三方DIALOG结束

    //饼图开始
    public static final String pic_1 = "#ff8400";
    public static final String pic_2 = "#f4c31a";



    //饼图结束


    //默认头像
    public static final String IMAGE = "160728100000002ZQUbs";


    public static boolean IsUpdate_Head;//是否需要更新头像

    public static final int MONEY_TO_ADD_MONEY = 0x008;  //从费用列表跳转到添加费用列表的参数

    public static final int HEYUE_TO_ADD_MONEY = 0x010;  //从合约2跳转到添加费用列表的参数

    public static final int FANGJIANXINXI_TO_ADD_MONEY = 0x033;  //从房间信息跳转到添加费用列表的参数


    public static final int MONEY_RESULT = 0x009;  //从添加费用列表 返回的参数


    public static final int ONLY_ONE_ALBUM = 0x001;  //从相册选一张
    public static final int ONLY_ONE_CAMERA = 0x002;//从相机拍一张
    public static final int TAKE_PHOTO = 0x004;//裁剪功能
}
