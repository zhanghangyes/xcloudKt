package com.example.xcloudkt.constant

object Constant {
    /**
     * 社群appkey环境区分
     */
    const val DEV = 1 //开发环境
    const val UAT = 2 //uat环境
    const val RELEASE = 3 //生产环境

    /**
     * 移动端每次打开H5页面时，需判断该链接是否为第三方url的前缀
     * 否：维持现有逻辑，跳转进入后续流程
     * 是：弹框提示用户即将访问第三方链接，
     * 跳转类型 1：内部跳转2：内部h5 3 外部链接
     */
    const val HOME_ICON_URL_TYPE_ONE = 1
    const val HOME_ICON_URL_TYPE_TWO = 2
    const val HOME_ICON_URL_TYPE_THREE = 3

    /**
     * 升级类型
     */
    const val PROMPT_UPGRADE = 1 //提示升级
    const val FORCED_UPGRADE = 2 //强制升级

    /**
     * 是否第一次登录
     */
    const val IS_ACCOUNT_LOGIN_ONE = "is_account_login_one"

    /**
     * 默认地址
     */
    const val DEFULT_LOCATION = "上海"

    //bugly
    const val BUGLY_APPID = "c9b98e66ef"

    /**
     * ---------------------------------支付方式类型--------------------------
     */
    //支付宝
    const val ALI_PAY_TYPE = "03"

    //微信
    const val WE_CHART_TYPE = "04"

    //云闪付
    const val FLASH_PAY_TYPE = "05"

    //积分
    const val SCORE_TYPE = "02"
    //储值金
    //    public static final String STORE_CASH_TYPE = "01";
    /**
     * umeng ID
     */
    const val UMENG_ID = "5ebbb758dbc2ec07f77a040f"

    /**
     * 微信开发者平台ID
     */
    const val WEIXIN_ID = "wxeb934ce107e0b2ff"

    /**
     * 微信开发者平台secret
     */
    const val WEIXIN_SECRET = "006a8c81bc39389eb696033a313616c1"

    /**
     * 听云的key
     */
    const val NBS_DEBUG = "a2beeca8166c4a419364b1266ec22010"
    const val NBS = "1d3c609a53564d9ca3a8a40e31d3cb45"

    /**
     * 融云小米push的appid
     */
    const val IM_MIAPPID = "2882303761518437193"
    const val IM_MIAPPKEY = "5701843776193"

    //社群融云appkey
    const val IMAPPKEY_RELEASE = "z3v5yqkbz7wi0"
    const val IMAPPKEY_UAT = "lmxuhwagles5d"
    const val IMAPPKEY_DEV = "pvxdm17jpe73r"

    /**
     * 支付宝开发者平台ID
     */
    const val ALI_ID = "2021001163642774"
    const val COLUMN_NAME_RET_CODE = "retCode"
    const val API16 = 16

    /**
     * 神策url
     */
    //    public static final String SA_SERVER_URL = "https://fmapp-datacollect.chinafamilymart.com.cn/sa?project=production";
    const val SA_SERVER_URL =
        "https://fmapp-datacollect.chinafamilymart.com.cn/sa?project=production_fixed"
    const val SA_SERVER_URL_DEBUG =
        "https://fmapp-datacollect.chinafamilymart.com.cn/sa?project=default"

    /**
     * 正确
     */
    const val RET_OK = 1

    /**
     * Cursor为null
     */
    const val RET_NULL_CURSOR = 16

    /**
     * Cursor中无数据
     */
    const val RET_EMPTY_CURSOR = 17

    /**
     * Cursor moveToFirst时返回false
     */
    const val RET_CURSOR_MOVETOFIRST = 18

    /**
     * MMOpenApiCaller执行时crash，请检查demo程序
     */
    const val RET_MM_EXCEPTION = 19

    /**
     * demo检测参数为null，请检查输入参数
     */
    const val RET_NULL_ARGS = 20

    /**
     * demo初始化值
     */
    const val RET_MM_INITIAL = 21

    /**
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 活动类型 0充值 1签到 2礼包活动 3秒杀活动 4限时促销 5定金预售 6多人拼团 7砍价活动 8生日礼 9满额赠 10满额减
     * 11满额加价购 12首单赠送 13满额免外送费 14指定单品/商品组活动 15满件赠  16社群领券
     * 17邀请有礼 18兑换码 19券冲正 20社群领券（群主发券）21 注册有礼  22助力活动 23领券
     */
    const val HOME_ACTIVITYCODE = "17" //邀请有礼
    const val HOME_COUPON = "23" //23领券
    const val HOME_BOOST = "22" //助力活动
    const val HOME_INTEGRAL = "24" //积分购
    const val SHARETURN = "34" //大转盘分享
    const val HOME_JOIN_GROUP = "35" //拼团

    /**
     * --------------------------------------------------原生页面的标识，配合后端配置跳转，如果需要改动，需前后端一起，保持一致性-------------------------------
     */
    //本地跳转(带token)
    const val LOCAL_OPEN = 1

    //web跳转 (带token)
    const val WEB_OPEN = 2

    //3送礼品
    const val SPLASH_OPEN = 3

    //web跳转 (不带token)
    const val WEB_OPEN_NO_TOKEN = 4

    //toast
    const val TOAST = 5

    //本地跳转
    const val LOCAL_OPEN_NO_TOKEN = 7

    //米粒兑换商城界面
    const val GRAIN_MALL_PAGE = 10

    //签到主界面
    const val SIGN_IN_PAGE = 11

    //我的发米粒界面
    const val MINE_GRAIN_PAGE = 12

    //新品上市二级列表页面
    const val NEW_PRODUCT_PAGE = 13

    //品牌专区二级页面
    const val BRAND_ACTIVITY = 14

    //今日特惠二级列表界面
    const val DISCOUNT_TODAY_LIST_PAGE = 15

    //个人中心
    const val PERSON_CENTER_PAGE = 16

    //优惠券卡包列表页
    const val COUPONS_PACKAGE_LIST_PAGE = 17

    //米粒说明
    const val GRAIN_DESCRIPTION = 18

    //我的账单列表
    const val BILL_LIST_PAGE = 19

    //消息中心列表
    const val MESSAGE_CENTER_LIST = 20

    //会员资料查看界面
    const val MEMBER_INFO_PAGE = 21

    //会员权益
    const val MEMBER_RIGHTS_PAGE = 22

    //关于我们
    const val ABOUT_WE_PAGE = 23

    //系统设置
    const val SYSTEM_SETTING_PAGE = 24

    //首页
    const val HOME_PAGE = 25

    //会员吗
    const val MEMBER_PAGE = 26
    /**
     * ------------------------------广告投放类型----------------------------
     */
    /**
     * 闪屏
     */
    const val ADVERTISING_PUT_SHAN_PIN_TYPE = 1

    /**
     * 首页弹窗
     */
    const val ADVERTISING_PUT_HOME_TOAST_TYPE = 2

    /**
     * 首页轮播
     */
    const val ADVERTISING_PUT_HOME_BANNER_TYPE = 3

    /**
     * 个人中心轮播
     */
    const val ADVERTISING_PUT_PERSONAL_BANNER_TYPE = 4

    /**
     * 首页品牌
     */
    const val ADVERTISING_PUT_HOME_PIN_PAI_TYPE = 5

    /**
     * app分享地址
     */
    const val VERSITION_SHARE = "https://fmapp-download.chinafamilymart.com.cn/index.html"

    /**
     * 吉祥联盟地址
     */
    const val MAXXIPOINT =
        "https://a.app.qq.com/o/simple.jsp?pkgname=com.maxxipoint.android"

    /**
     * 是否第一次登录
     */
    const val IS_ACCOUNT_LOGIN = "is_account_login"

    /**
     * 是否打开会员码
     */
    const val IS_LOGIN_MEMBER_ONE = "is_login_member_one"

    /**
     * 登录token
     */
    const val APP_AUTH_TOKEN = "app_auth_token"

    /**
     * 融云token
     */
    const val RONG_YUN_TOKEN = "rong_yun_auth_token"

    /**
     * 地址存储
     */
    const val ADDRESS = "address"

    /**
     * StoreCode存储
     */
    const val STORECODE = "StoreCode"

    /**
     * CityCode存储
     */
    const val CITYCODE = "CityCode"

    /**
     * activityID
     */
    const val ACTIVITYID = "activityid"

    /**
     * 电话权限
     */
    const val PHONE_PERMISSIONS = "phone_permissions"

    /**
     * 定位权限
     */
    const val ADDRESS_PERMISSIONS = "address_permissions"

    /**
     * 相冊权限
     */
    const val IMAGE_PERMISSIONS = "image_permissions"

    /**
     * 文件权限
     */
    const val FILE_PERMISSIONS = "file_permissions"

    /**
     * activityType
     */
    const val ACTIVITYTYPE = "activitytype"

    /**
     * 邀请有礼的活动code
     */
    const val ACTIVITYCODE = "activitycode"

    /**
     * memberId
     */
    const val MEMBERID = "memberId"

    /**
     * 新用户领券
     */
    const val ISPERFECTINFOFLAG = "isPerfectInfoFlag"

    /**
     * 门店返回
     */
    const val HOME_BEAN = "home_bean"

    /**
     * 消息通知免打扰
     */
    const val SETTING_MESSAGE_NO_SHOW = "setting_message_no_show"

    /**
     * 社群新消息接收提醒
     */
    const val IM_SETTING_MESSAGE_NO_SHOW = "im_setting_message_no_show"

    /**
     * 订阅通知
     */
    const val SETTING_MESSAGE_SYSTEM_SHOW = "setting_message_system_show"

    /**
     * 修改昵称
     */
    const val UPDATE_NICKNAME = "update_nickname"

    /**
     * 刷新会话列表
     */
    const val CHAT2FRAGMENT_FLASH = "Chat2Fragment_flash"
    const val CHAT2FRAGMENT = "Chat2Fragment"

    /**
     * 修改群公告
     */
    const val UPDATE_GROUP_NOTICE = "update_group_notice"

    /**
     * 消息免打扰
     */
    const val MESSAGE_DISTURB = "message_disturb"

    /**
     * 消息免打扰状态获取
     */
    const val MESSAGE_DISTURB_VALUE = "message_disturb_value"

    /**
     * 清除聊天记录
     */
    const val CLEAR_CHART_RECORD = "clear_chart_record"

    /**
     * 销毁群聊会话界面
     */
    const val FINISH_CONVERSATION_ACTIVITY = "finish_conversation_activity"

    /**
     * 滚动群聊会话界面
     */
    const val SCORLL_CONVERSATION_ACTIVITY_BOTTOM = "scorll_conversation_activity_bottom"

    /**
     * 返回会话列表
     */
    const val RETURN_CONVERSATION_LIST = "return_conversation_list"

    /**
     * 消息未读数量
     */
    const val CHAT_UNM = "chat_unm"

    /**
     * 显示新人礼
     */
    const val SHOW_DIALOG_NEW = "show_dialog_new"
    const val APP_TYPE_ANDROID = "2"

    /**
     * 摇一摇
     */
    const val SHAKE_ON_PAUSE = "shake_on_pause"
    const val SHAKE_ON_RESUME = "shake_on_resume"

    /**
     * 清除会话列表缓存，通知刷新社群未读消息数量
     */
    const val CHAT_UNREAD_NUM = "chat_unread_num"

    //个人中心文本
    const val text1 = "全家陪伴：本月您已消费 "
    const val text2 = " 元，已省 "
    const val text3 = " 元\n已获 "
    const val text4 = "已省 "
    const val text5 = " 积分"

    /**
     * 新功能提醒  （sharepreferences ）
     */
    const val NEWFEATURE_REMINDER = "newfeature_reminder"

    /**
     * 社群发券
     */
    const val IMCOUONGROUPID = "IMCouponGroupId"
    const val IMCOUPONITEM = "IMCouponItem"

    /**
     * MainActivity
     */
    //摇一摇
    const val MAIN_TYPE_SHAKE = 1

    //web网页
    const val MAIN_TYPE_WEB_JOIN = 2

    //闪屏页
    const val MAIN_TYPE_WEB_SPLASH = 3
    const val HOME_ADDIALOG = "home_addialog" //首页弹框
    const val MINE_PULL = "mine_pull" //下拉刷新
    const val CARDMINEACTIVITY2 = "CardMineActivity2" //标识会员权益页面
    const val CARDMINEACTIVITY = "CardMineActivity" //标识会员权益页面
    const val SAVEINFO = "save_info" //个人信息保存回调
    const val HOME_MAIN = "home_main" //底部跳转类型

    /*
     * -------------------------------------------------------------登录类型判断--------------------------------------------------------
     */
    const val LOGIN_TYPE_DEF = -1 //默认值,不处理
    const val LOGIN_TYPE_ADDRESS = 1 //门店切换
    const val LOGIN_TYPE_NEW = 2 //新品上市 更多
    const val LOGIN_TYPE_THREE = 3 //我的Fa米粒
    const val LOGIN_TYPE_FOUR = 4 //Fa米外卖
    const val LOGIN_TYPE_FIVE = 5 //账单
    const val LOGIN_TYPE_SIX = 6 //优惠券包
    const val LOGIN_TYPE_SENVEN = 7 //Fa米粒兑换
    const val LOGIN_TYPE_SIGN = 8 //签到
    const val LOGIN_TYPE_SCANGO = 9 //扫码购
    const val LOGIN_TYPE_SEARCH = 10 //门店查询
    const val LOGIN_TYPE_MESSAGE = 11 //消息
    const val LOGIN_TYPE13 = 13 //品牌专区 跳转登录
    const val LOGIN_TYPE14 = 14 //头部banner 跳转web,带token
    const val LOGIN_TYPE15 = 15 //头部banner 跳原生登录
    const val LOGIN_TYPE16 = 16 //品牌专区 跳转web,带token
    const val LOGIN_TYPE17 = 17 //活动专区 跳转web,带token
    const val LOGIN_TYPE18 = 18 //首页弹框dialog 跳转web,带token
    const val LOGIN_TYPE19 = 19 //首页弹框dialog 跳原生登录
    const val LOGIN_TYPE20 = 20 //活动专区 跳原生登录
    const val SHAREBTN = "share_btn" //分享按钮的显示隐藏
    const val WEBLOGIN = "web_login" //刷新web

    /**
     *
     */
    const val SP_ONEKEYPHONE = "sp_onekeyphone"
    const val SP_ONEKEY_OPERATOR = "sp_onekey_operator"
    const val SP_ONEKEY_TIME = "sp_onekey_time"
}