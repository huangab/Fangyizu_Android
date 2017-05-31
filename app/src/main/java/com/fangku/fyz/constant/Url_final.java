package com.fangku.fyz.constant;

/**
 * Created by 67343 on 2016/7/13.
 */
public class Url_final {

    public static final String MainUrl = "http://192.168.0.250:8080/fyz";//主地址

//    public static final String MainUrl = "http://192.168.1.40:8888/fyz";//测试地址

//    public static final String MainUrl = "http://192.168.1.40:8888/fyz";//测试地址

// TODO: 2016/8/10 用户模块!!!!!!!!!!!!!

    public static final String LOGIN = MainUrl + "/api/sys/user/login/";//账号密码登陆

    public static final String CHECK_PHONE = MainUrl + "/api/sys/user/isExistForMobile/";//判断电话号码是否存在

    public static final String REGISTER = MainUrl + "/api/sys/user/register";//注册

    public static final String GETCODE = MainUrl + "/api/sys/user/findcaptcha/";//获取验证码

    public static final String SAVE = MainUrl + "/api/sys/user/saveStagingUser";//访问临时存储表

    public static final String FORGET_CHANGE_PASSWORD = MainUrl + "/api/sys/user/changePwd";//忘记密码——修改密码

    public static final String UPLOAD_HEAD_IMG = MainUrl + "/api/sys/picture/upload";//上传图片

    public static final String DOWNLOAD_SMALL_IMG = MainUrl + "/api/sys/picture/downloadSmallPic/";//下载小图

    public static final String DOWNLOAD_BIG_IMG = MainUrl + "/api/sys/picture/downloadBigPic/";//下载大图

    public static final String UPDATE_USER_DETAIL = MainUrl + "/app/sys/user/updateUserDetail";//更新用户信息

    public static final String GET_BALANCE = MainUrl + "/app/sys/user/getMyAccountBalance";//返回余额

    public static final String GET_BANKSHOW = MainUrl + "/app/sys/bankCard/getMyBankCard";//银行卡展示

    public static final String GET_CARDPAGER = MainUrl + "/app/sys/coupon/getMyCoupon";//卡包展示

    public static final String ADD_BANKCARD = MainUrl + "/app/sys/bankCard/addMyBankCard";//添加银行卡

    public static final String GET_MY_MESSAGE_LIST = MainUrl + "/app/sys/message/getMyMessageList";//我的消息列表

    public static final String ADD_FEEDBACK = MainUrl + "/app/sys/feedback/addFeedback";//反馈意见

    public static final String GET_CONTACT = MainUrl + "/app/sys/user/getContactUs";//获取客服


    // TODO: 2016/8/14 创建房产
    public static final String GET_ADDRESS = MainUrl + "/app/house/searchPlace";//地图接口查找位置

    public static final String ADD_HOUSE = MainUrl + "/app/house/addHouse";//添加房产

    public static final String ADD_HOUSE2 = MainUrl + "/app/house/addHouseRoom";//添加房产2

    public static final String ADD_Room = MainUrl + "/app/room/addRoom";//房间添加

    public static final String GET_ChargeType = MainUrl + "/app/house/getChargeType";//获取费用种类参数

    public static final String UpdateHouseCharge = MainUrl + "/app/house/updateHouseCharge";

    public static final String GETHOUSEDATA = MainUrl + "/app/house/queryHouseList";//获取房产列表

    public static final String GET_BASELIST = MainUrl + "/api/sys/dict/basList/";//获取参数表大类、费用、信息

    public static final String UPDataHouse = MainUrl + "/app/house/updateHouse";//修改房产

    public static final String GetSingleHouse = MainUrl + "/app/house/getSingleHouse";//根据房产ID获取房产信息

    public static final String SaveChageRoomContract = MainUrl + "/app/rent/saveChageRoomContract";//换房

    public static final String DELETE_HOUSE = MainUrl + "/app/house/deleteHouse";//根据房产ID  删除房产


    // TODO: 2016/8/30 房屋出租
    public static final String addContract = MainUrl + "/app/rent/addContract";///房屋出租

    public static final String SAVE_RENEW_CONTRACT = MainUrl + "/app/rent/saveRenewContract";///续约


    // TODO: 2016/8/17  抄表!!!!!!!!!!!!!!!
    public static String HY = MainUrl + "/app/me/queryNewScalebyHouseId";//抄表查询

    public static String SAVA_HY = MainUrl + "/app/me/addBatchMeter";//保存抄表

    public static String HISTORY_HY = MainUrl + "/app/me/queryHisScalebyRoomId";//抄表历史

    public static String ORDERBY_HY = MainUrl + "/app/me/updMeterOrderBy";//抄表排序

    //// TODO: 2016/9/20  所有房间功能快

    //首页.获取未交租的房间
    public static String QUERY_NO_TAXES_ROOM = MainUrl + "/app/rent/queryNoTaxesRoom";

    //首页.获取未出租的房间
    public static String QUERY_NO_RENT_AND_CONTRACTEND_ROOM = MainUrl + "/app/rent/queryNotRentAndContractEndRoom";

    public static String QUERY_NO_RENT_ROOM = MainUrl + "/app/rent/queryNotRentRoom";//获取未出租的房间

    public static String QUERY_EXPIRE_ROOM = MainUrl + "/app/rent/queryExpireRoom";//获取合同到期的房间

    public static String QUERY_RENT_ROOM = MainUrl + "/app/rent/queryTaxesRoom";//获取已出租的房间

    public static String QUERY_ROOMER = MainUrl + "/app/roomer/queryRoomerByContractId";//根据合同Id查询租客信息

    public static String QUERY_HIS = MainUrl + "/app/rent/queryHisRentRecord";//查询历史收租记录

    public static String GET_ROOM_BY_ID = MainUrl + "/app/room/getRoomById";//查询房间信息

    public static String SAVE_RENT = MainUrl + "/app/house/refundrent";//退房

    public static String ADD_ROOMER = MainUrl + "/app/roomer/addRoomer";//增加房客

    public static String UPDATE_ROOMER = MainUrl + "/app/roomer/updateRoomer";//修改房客

    public static String DELETE_ROOMER = MainUrl + "/app/roomer/deleteRoomer";//删除房客

    public static String UPDATE_ROOM = MainUrl + "/app/room/updateRoom";//修改房间

    public static String ContractById = MainUrl + "/app/rent/getContractById";//根据合同id查合同

    public static String RentFee = MainUrl + "/app/rent/getRentFee";//获取单个房间费用

    public static String SAVA_RISERENTCONTRACT = MainUrl + "/app/rent/saveRiseRentContract";//涨租

    public static String ADD_ROOMRENT = MainUrl + "/app/rent/addRoomRent";//收租

}
