package com.example.xcloudkt.constant

/**
 * 广告类型
 */
object AdType {
    //闪屏
    const val AD_TYPE_SPLASH = "1"

    //弹窗
    const val AD_TYPE_DIALOG = "2"

    //新人礼物
    const val AD_TYPE_NEW_USER_GIFT = "3"

    //消息全读
    const val MSGREAD_ALL = "9"

    //活动消息
    const val ACTIVITY_MSGREAD_ALL = "1"

    //通知消息
    const val SAY_MSGREAD_ALL = "2"

    //系统消息
    const val SYSTEM_MSGREAD_ALL = "3"

    //0显示加载框，-1不显示
    const val LOADING = 0
    const val NOT_LOADING = -1

    //通用跳转类型定义
    const val HOME_BANNER = 1 //home   头部banner
    const val HOME_ACTIVITY = 2 //home  活动专区
    const val HOME_BRAND = 3 //home  品牌专区
    const val MINE_BANNER = 4 //个人中心  banner
    const val SCHEME_TYPE = 5 //scheme
    const val SPLASH_TYPE = 6 //闪屏页跳转  bannerd
    const val HOME_DIALOG = 7 //首页  弹框
    const val HOME_ICON = 8 //首页  金刚区
}