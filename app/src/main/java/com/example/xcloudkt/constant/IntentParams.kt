package com.example.xcloudkt.constant

object IntentParams {
    //欢迎页传参类型
    const val WELCOME_INTENT_TYPE = "welcome_intent_type"

    //欢迎页原生跳转类型
    const val WELCOME_INTENT_NATIVE_PAGE = "welcome_Intent_native_page"

    //欢迎页跳转token web
    const val WELCOME_INTENT_TOKEN_H5 = "welcome_intent_token_h5"

    //欢迎页跳转游客web
    const val WELCOME_INTENT_TOURIST_H5 = "welcome_intent_tourist_h5"

    //账单信息
    const val BILL_INTENT = "bill_intent"

    //店铺id
    const val BILL_STORE_ID = "storeId"

    //订单id
    const val BILL_BILL_ID = "billId"

    //POS机序号
    const val BILL_POS_SEQ = "posSeq"

    //订单交易时间
    const val BILL_TRADE_TIME = "time"

    /**
     * 首页-卡券包点击
     * 会员码-点击和摇一摇（在会员码界面中调取api）
     * 注意事项：在App切换过程中，只要不是重新启动App就不能调取api,防止调用频率太高，服务器挂掉@Android _张航 @ios 王坚
     */
    const val MEMBER = 2 //会员吗
    const val COUPON = 3 //卡券包

    //清除米粒
    const val LOGINTIME = "LoginTime"

    //闪屏
    const val AD_IMAGE = "ad_image"
    const val NOMORE = "-1" //所有通用跳转默认参数
    const val JUMP_MAIN = "jump_main" //跳转到主页面
    const val JUMP_MAIN_DATA = "jump_main_data" //跳转到主页面

    /**
     * 首页金刚区 底部导航跳转类型
     */
    const val HOME_ICON_TYPE = 1119 //金刚跳原生
    const val HOME_ICON_TYPE_WEB = 1120 //金刚区跳web
    const val HOME_NAVIGATION_TYPE = 1121 //底部导航跳原生
    const val HOME_NAVIGATION_TYPE_WEB = 1122 //底部导航跳web
    const val HOME_MEMBER = 1123 //底部导航会员码单独判断
}