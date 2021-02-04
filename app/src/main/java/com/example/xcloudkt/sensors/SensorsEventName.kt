package com.example.xcloudkt.sensors

/**
 * 事件英文变量名 -- 事件名都定义在这里 防止以后更改某个事件名称再去代码中找
 */
interface SensorsEventName {
    companion object {
        const val onboarding = "onboarding" //启动页操作
        const val newMemberExposure = "newMemberExposure" //曝光新人礼弹窗
        const val newMemberClick = "newMemberClick" //点击领取新人礼
        const val GETCODE = "getCode" //点击获取验证码
        const val reGetCode = "reGetCode" //点击重新获取
        const val loginResult = "loginResult" //登录
        const val bandPhoneLoginResult = "bandPhoneLoginResult" //绑定手机号登录
        const val clickChangeShop = "clickChangeShop" //点击切换门店
        const val changeShopResult = "changeShopResult" //切换门店结果
        const val clickMessage = "clickMessage" //点击消息中心
        const val viewMessageDetail = "viewMessageDetail" //查看消息详情
        const val mktClick = "mktClick" //运营位点击
        const val newProductView = "newProductView" //商品详情页浏览
        const val exitDetailView = "exitDetailView" //退出详情页浏览
        const val todayDetailView = "todayDetailView" //活动列表页浏览
        const val exitTodayDetail = "exitTodayDetail" //退出活动列表页浏览
        const val clickTodayTab = "clickTodayTab" //退出活动列表页浏览
        const val exitCampaignDetail = "exitCampaignDetail" //退出活动详情页
        const val tabClick = "tabClick" //底部导航点击
        const val checkIn = "checkIn" //点击签到
        const val checkInResult = "checkInResult" //点击签到成功
        const val exchangeProductListView = "exchangeProductListView" //兑换商品列表页浏览
        const val clickExchangeProduct = "clickExchangeProduct" //兑换商品点击
        const val exchangeProductDetailView = "exchangeProductDetailView" //兑换商品详情页浏览
        const val exitExchangeDetail = "exitExchangeDetail" //兑换商品详情页浏览
        const val clickProductExchange = "clickProductExchange" //点击商品兑换
        const val summitExchange = "summitExchange" //提交商品兑换
        const val couponListView = "couponListView" //优惠券列表页浏览
        const val couponDetail = "couponDetail" //优惠券详情浏览
        const val enterScanPay = "enterScanPay" //扫码结果
        const val clickScanAlipay = "clickScanAlipay" //点击扫支付宝
        const val clickScanWechat = "clickScanWechat" //点击扫微信支付
        const val clickStoreCard = "clickStoreCard" //点击储值金
        const val changeStoreCard = "changeStoreCard" //点击切换储值卡
        const val changeStoreCardResult = "changeStoreCardResult" //切换储值卡结果
        const val memberPageView = "memberPageView" //查看会员资料
        const val summitMemberPage = "summitMemberPage" //提交会员资料
        const val changeFunction = "changeFunction" //修改设置功能
        const val shareClick = "shareClick" //点击分享
        const val shareOption = "shareOption" //选择分享方式
        const val shareResult = "shareResult" //分享结果
        const val billPageView = "billPageView" //查看我的账单
        const val slideBanner = "slideBanner" //滑动运营位
        const val applyElectronicInvoice = "applyElectronicInvoice" //订单详情电子发票电子
        const val PushReceived = "PushReceived" //Push 通知送达
        const val PushClick = "PushClick" //Push点击
        const val scanToGroup = "scanToGroup" //扫码加入社群
        const val enterGroup = "enterGroup" //打开社群页面
        const val exchangeCodeExposure = "exchangeCodeExposure" //兑换码曝光
        const val exchangeCodeOperation = "exchangeCodeOperation" //兑换码操作
        const val exchangeCodeSummit = "exchangeCodeSummit" //提交兑换码
        const val onLableBackPressed = "onLableBackPressed" //标签页返回
        const val onInviteBackPressed = "onInviteBackPressed" //门店码返回
        const val onMainOnDestroy = "onMainOnDestroy" //main销毁
        const val onSaveInfoDestroy = "onSaveInfoDestroy" //个人资料销毁
        const val preCoupon = "preCoupon" //预发券
        const val enterComingProduct = "enterComingProduct" //进入新品抢先知
        const val exitComingProductView = "exitComingProductView" //退出新品抢先知
        const val clickDate = "clickDate" //切换日期 新品抢先知
        const val tabResult = "tabResult" //底部导航 金刚区
    }
}