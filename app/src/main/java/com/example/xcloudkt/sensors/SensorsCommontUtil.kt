package com.example.xcloudkt.sensors

import android.util.Log
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI
import com.sensorsdata.analytics.android.sdk.SensorsDataDynamicSuperProperties
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * author : zhanghang
 * date : 2019/12/21 13:39
 */
object SensorsCommontUtil {
    /**
     * 启动页操作
     *
     * @param isSummit   是否提交  是（我选好了时），否（跳过时）
     * @param preference 消费偏好  咖啡，包子，盒饭，酸奶…
     */
    fun onboarding(isSummit: Boolean, preference: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSummit", isSummit)
            jsonObject.put("preference", preference)
            SensorsUtil.clickTrack(SensorsEventName.onboarding, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 曝光新人礼弹窗
     *
     * @param isSummit         是否提交  领取，跳过
     * @param rookieActivityID 新人礼活动ID,
     * @param couponID         领取成功券ID
     */
    fun newMemberExposure(
        isSummit: Boolean,
        rookieActivityID: String?,
        couponID: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSummit", isSummit)
            jsonObject.put("rookieActivityID", rookieActivityID)
            jsonObject.put("couponID", couponID)
            SensorsUtil.clickTrack(SensorsEventName.newMemberExposure, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击领取新人礼
     *
     * @param isSuccess        是否提交  领取，跳过
     * @param errorReason      失败原因
     * @param rookieActivityID 新人礼活动ID,
     * @param couponID         领取成功券ID
     */
    fun newMemberClick(
        isSuccess: Boolean,
        errorReason: String?,
        rookieActivityID: String?,
        couponID: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("rookieActivityID", rookieActivityID)
            jsonObject.put("couponID", couponID)
            SensorsUtil.clickTrack(SensorsEventName.newMemberClick, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击获取验证码
     */
    val code: Unit
        get() {
            try {
                val jsonObject = JSONObject()
                SensorsUtil.clickTrack(SensorsEventName.reGetCode, jsonObject)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    /**
     * 点击重新获取验证码
     */
    fun reGetCode() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.GETCODE, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 登陆  有登录结果时触发
     *
     * @param loginType   登录类型  手机号，微信授权，支付宝授权，Apple授权
     * @param isSuccess   是否成功
     * @param errorReason 失败原因
     */
    fun loginResult(
        loginType: String?,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", "登录页")
            jsonObject.put("loginType", loginType)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.loginResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 绑定手机号  有登录结果时触发
     *
     * @param loginType   登录类型  手机号，微信授权，支付宝授权
     * @param isSuccess   是否成功
     * @param errorReason 失败原因
     */
    fun bandPhoneLoginResult(
        loginType: String?,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("loginType", loginType)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.bandPhoneLoginResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 底部导航
     *
     * @param tabName
     * @param isSuccess
     * @param errorReason
     */
    fun tabResult(
        tabName: String?,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("tabName", tabName)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.tabResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击切换门店  首页点击切换门店按钮时触发
     */
    fun clickChangeShop() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.clickChangeShop, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 切换门店结果  有切换门店结果时触发
     *
     * @param currentShop 当前门店名称
     * @param changeShop  切换后门店名称
     * @param loginType   切换类型  重新定位，附近门店，搜索切换
     * @param isSuccess   是否成功
     * @param errorReason 失败原因
     */
    fun changeShopResult(
        currentShop: String?,
        changeShop: String?,
        loginType: String?,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("currentShop", currentShop)
            jsonObject.put("changeShop", changeShop)
            jsonObject.put("changeType", loginType)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.changeShopResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击消息中心  点击消息中心按钮时触发
     */
    fun clickMessage() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.clickMessage, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 查看消息详情  点击消息查看详情时触发
     *
     * @param currentShop 页面来源  首屏推送，消息中心，其它
     * @param changeShop  消息类型  活动消息，系统消息，通知消息
     * @param loginType   消息ID
     * @param errorReason 消息名称
     */
    fun viewMessageDetail(
        currentShop: String?,
        changeShop: String?,
        loginType: String?,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", currentShop)
            jsonObject.put("messageType", changeShop)
            jsonObject.put("messageID", loginType)
            jsonObject.put("messageName", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.viewMessageDetail, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 运营位点击  点击banner、按钮、卡片、版区运营位时触发
     *
     * @param moduleID     所在版区ID
     * @param moduleName   版区名称  轮播图、icon、入口图等
     * @param mktID        运营位ID
     * @param mktType      运营位类型  banner，按钮，卡片，分类bar
     * @param mktName      运营位名称
     * @param index        运营位排序  1，5
     * @param productID    商品ID
     * @param productName  商品名称  养乐多，奥利奥饼干，海飞丝
     * @param activityID   活动品ID
     * @param activityName 活动品名称
     * @param mktLink      跳转地址
     */
    fun mktClick(
        moduleID: String?,
        moduleName: String?, mktID: String?,
        mktType: String?, mktName: String?,
        index: Int, productID: String?,
        productName: String?, activityID: String?,
        activityName: String?, mktLink: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", "首页")
            jsonObject.put("moduleID", moduleID)
            jsonObject.put("moduleName", moduleName)
            jsonObject.put("mktID", mktID)
            jsonObject.put("mktType", mktType)
            jsonObject.put("mktName", mktName)
            jsonObject.put("index", index)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("activityID", activityID)
            jsonObject.put("activityName", activityName)
            jsonObject.put("mktLink", mktLink)
            SensorsUtil.clickTrack(SensorsEventName.mktClick, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 商品详情页浏览  点击「新品上市」版区，跳转到商品详情页时触发
     *
     *
     * 模块来源  签到，首页新品上市…
     */
    fun newProductView(
        productListID: List<String?>?,
        productListName: List<String?>?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", "首页")
            jsonObject.put("campaignID", "")
            jsonObject.put("campaignName", "新品上市")
            jsonObject.put("productListID", JSONArray(productListID))
            jsonObject.put("productListName", JSONArray(productListName))
            SensorsUtil.clickTrack(SensorsEventName.newProductView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 退出详情页浏览  从商品详情页退出时触发
     *
     * @param viewpointHeight 视区距顶部位置
     */
    fun exitDetailView(
        viewpointHeight: Number?,
        campaignID: String?,
        campaignName: String?,
        productListID: List<String?>?,
        productListName: List<String?>?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("viewpointHeight", viewpointHeight)
            jsonObject.put("campaignID", campaignID)
            jsonObject.put("campaignName", campaignName)
            jsonObject.put("productListID", JSONArray(productListID))
            jsonObject.put("productListName", JSONArray(productListName))
            SensorsUtil.clickTrack(SensorsEventName.exitDetailView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击进入「新品抢先知」页面时触发
     *
     *
     * 当前日期 模块来源  签到，新品抢先知…
     */
    fun enterComingProduct(
        currentDate: String?,
        productNum: Int,
        productListID: List<String?>?,
        productListName: List<String?>?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", "首页")
            jsonObject.put("currentDate", currentDate)
            jsonObject.put("productNum", productNum)
            jsonObject.put("campaignName", "新品抢先知")
            jsonObject.put("productListID", JSONArray(productListID))
            jsonObject.put("productListName", JSONArray(productListName))
            SensorsUtil.clickTrack(SensorsEventName.enterComingProduct, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 退出「新品抢先知」时触发
     *
     *
     * 当前日期 模块来源  签到，新品抢先知…
     */
    fun exitComingProductView(
        currentDate: String?,
        productListID: List<String?>?,
        productListName: List<String?>?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("currentDate", currentDate)
            jsonObject.put("campaignName", "新品抢先知")
            jsonObject.put("productListID", JSONArray(productListID))
            jsonObject.put("productListName", JSONArray(productListName))
            SensorsUtil.clickTrack(SensorsEventName.exitComingProductView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 切换「新品抢先知顶部日期」时触发
     *
     *
     * 当前日期 模块来源  签到，新品抢先知…
     */
    fun clickDate(
        fromDate: String?,
        changeDate: String?,
        productListID: List<String?>?,
        productListName: List<String?>?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("fromDate", fromDate)
            jsonObject.put("changeDate", changeDate)
            jsonObject.put("productListID", JSONArray(productListID))
            jsonObject.put("productListName", JSONArray(productListName))
            SensorsUtil.clickTrack(SensorsEventName.clickDate, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 活动列表页浏览  点击「品牌专区」「今日特惠」版区，跳转到活动详情页时触发
     *
     * @param pageFrom 页面来源
     */
    fun todayDetailView(
        pageFrom: String?,
        sourceMtkID: String?,
        sourceMtkType: String?,
        sourceMktName: String?,
        sourceIndex: String?,
        categoryCodes: String?,
        categoryName: String?,
        promotionNo: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            jsonObject.put("sourceMtkID", sourceMtkID)
            jsonObject.put("sourceMtkType", sourceMtkType)
            jsonObject.put("sourceMktName", sourceMktName)
            jsonObject.put("sourceIndex", sourceIndex)
            jsonObject.put("categoryCodes", categoryCodes)
            jsonObject.put("categoryName", categoryName)
            jsonObject.put("promotionNo", promotionNo)
            SensorsUtil.clickTrack(SensorsEventName.todayDetailView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun exitTodayDetail(
        sourceMtkID: String?,
        sourceMtkType: String?, sourceMktName: String?,
        sourceIndex: String?, categoryCodes: String?,
        remainTime: Number?, viewpointHeight: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("sourceMtkID", sourceMtkID)
            jsonObject.put("sourceMtkType", sourceMtkType)
            jsonObject.put("sourceMktName", sourceMktName)
            jsonObject.put("sourceIndex", sourceIndex)
            jsonObject.put("categoryCodes", categoryCodes)
            jsonObject.put("remainTime", remainTime)
            jsonObject.put("viewpointHeight", viewpointHeight)
            SensorsUtil.clickTrack(SensorsEventName.exitTodayDetail, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击切换今日特惠页分类
     *
     * @param categoryCodes
     * @param remainTime
     * @param viewpointHeight
     */
    fun clickTodayTab(
        sourceCategoryCodes: String?, categoryCodes: String?,
        remainTime: Number?, viewpointHeight: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("sourceCategoryCodes", sourceCategoryCodes)
            jsonObject.put("categoryCodes", categoryCodes)
            jsonObject.put("remainTime", remainTime)
            jsonObject.put("viewpointHeight", viewpointHeight)
            SensorsUtil.clickTrack(SensorsEventName.clickTodayTab, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 退出活动详情页  点击「品牌专区」「今日特惠」版区，跳转到活动详情页时触发
     *
     * @param campaignID      活动ID
     * @param campaignName    活动名称
     * @param campaignStatus  活动状态  进行中，已结束
     * @param moduleID        所在版区
     * @param moduleName      版区名称   品牌专区、今日特惠
     * @param productID       商品ID
     * @param productName     商品名称  养乐多，奥利奥饼干，海飞丝
     * @param productBrand    商品品牌   雀巢，哇哈哈
     * @param productTypeID   商品分类ID
     * @param productType     商品分类名称
     * @param viewpointHeight 视区距顶部位置
     */
    fun exitCampaignDetail(
        viewpointHeight: Number?, campaignID: String?,
        campaignName: String?, campaignStatus: String?,
        moduleID: String?, moduleName: String?,
        productID: String?, productName: String?,
        productBrand: String?, productTypeID: String?, productType: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("viewpointHeight", viewpointHeight)
            jsonObject.put("campaignID", campaignID)
            jsonObject.put("campaignName", campaignName)
            jsonObject.put("campaignStatus", campaignStatus)
            jsonObject.put("moduleID", moduleID)
            jsonObject.put("moduleName", moduleName)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("productBrand", productBrand)
            jsonObject.put("productTypeID", productTypeID)
            jsonObject.put("productType", productType)
            SensorsUtil.clickTrack(SensorsEventName.exitCampaignDetail, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 主tab页访问   首页，会员码，领券，我的、社群
     *
     * @param tab_name tab页面名称
     */
    fun tabClick(tab_name: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("tabName", tab_name)
            SensorsUtil.clickTrack(SensorsEventName.tabClick, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击签到  点击签到按钮时触发
     */
    fun checkIn(name: String?, type: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("activityName", name)
            jsonObject.put("activityType", type)
            SensorsUtil.clickTrack(SensorsEventName.checkIn, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击签到成功   签到成功
     */
    fun checkInResult(
        activityName: String?,
        signType: String?,
        signDateCount: Int,
        giftPackage: String?,
        giftNumber: Int,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("activityName", activityName)
            jsonObject.put("activityType", signType)
            jsonObject.put("signDateCount", signDateCount)
            jsonObject.put("giftPackage", giftPackage)
            jsonObject.put("grantNum", giftNumber)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.checkInResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 兑换商品列表页浏览   点击签到-更多，发米粒页等进入兑换商品列表页时触发
     *
     * @param pageFrom 签到，发米粒兑换…
     */
    fun exchangeProductListView(pageFrom: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            SensorsUtil.clickTrack(SensorsEventName.exchangeProductListView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 兑换商品点击  点击首页新品上市商品、签到页商品，商品列表页等兑换商品位时触发
     *
     * @param pageFrom       页面来源
     * @param mktLink        活动名称
     * @param moduleID       所在版区
     * @param moduleName     版区名称   品牌专区、今日特惠
     * @param index          商品品牌   雀巢，哇哈哈
     * @param productID      商品ID
     * @param productName    商品名称  养乐多，奥利奥饼干，海飞丝
     * @param fmlPoint       商品分类ID
     * @param memberFmlPoint 商品分类名称
     */
    fun clickExchangeProduct(
        pageFrom: String?, mktLink: String?,
        moduleID: String?, moduleName: String?,
        index: Number?,
        productID: String?, productName: String?,
        fmlPoint: Number?, memberFmlPoint: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            jsonObject.put("mktLink", mktLink)
            jsonObject.put("moduleID", moduleID)
            jsonObject.put("moduleName", moduleName)
            jsonObject.put("index", index)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("fmlPoint", fmlPoint)
            jsonObject.put("memberFmlPoint", memberFmlPoint)
            SensorsUtil.clickTrack(SensorsEventName.clickExchangeProduct, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 兑换商品详情页浏览  点击进入兑换商品详情页时触发
     *
     * @param pageFrom       页面来源  发米粒兑换，签到…
     * @param productID      商品ID
     * @param productName    商品名称  养乐多，奥利奥饼干，海飞丝
     * @param fmlPoint       商品分类ID
     * @param memberFmlPoint 商品分类名称
     */
    fun exchangeProductDetailView(
        pageFrom: String?,
        productID: String?, productName: String?,
        fmlPoint: Number?, memberFmlPoint: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("fmlPoint", fmlPoint)
            jsonObject.put("memberFmlPoint", memberFmlPoint)
            SensorsUtil.clickTrack(SensorsEventName.exchangeProductDetailView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 退出商品详情页浏览  退出兑换商品详情页时触发
     *
     * @param productID       商品ID
     * @param productName     商品名称  养乐多，奥利奥饼干，海飞丝
     * @param viewpointHeight 视区距顶部位置
     * @param fmlPoint        商品分类ID
     * @param memberFmlPoint  商品分类名称
     */
    fun exitExchangeDetail(
        viewpointHeight: Number?,
        productID: String?, productName: String?,
        fmlPoint: Number?, memberFmlPoint: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("viewpointHeight", viewpointHeight)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("fmlPoint", fmlPoint)
            jsonObject.put("memberFmlPoint", memberFmlPoint)
            SensorsUtil.clickTrack(SensorsEventName.exitExchangeDetail, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击商品兑换  点击兑换按钮时触发
     *
     * @param productID       商品ID
     * @param productName     商品名称  养乐多，奥利奥饼干，海飞丝
     * @param fmlPoint        商品分类ID
     * @param memberFmlPoint  商品分类名称
     * @param viewpointHeight 视区距顶部位置
     */
    fun clickProductExchange(
        viewpointHeight: Number?,
        productID: String?, productName: String?,
        fmlPoint: Number?, memberFmlPoint: Number?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("viewpointHeight", viewpointHeight)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("fmlPoint", fmlPoint)
            jsonObject.put("memberFmlPoint", memberFmlPoint)
            SensorsUtil.clickTrack(SensorsEventName.clickProductExchange, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 提交商品兑换  点击兑换按钮时触发
     *
     * @param productID       商品ID
     * @param productName     商品名称  养乐多，奥利奥饼干，海飞丝
     * @param fmlPoint        商品分类ID
     * @param memberFmlPoint  商品分类名称
     * @param useFmlPoint     发米粒消耗
     * @param productQuantity 商品数量
     * @param isSuccess       是否成功
     * @param errorReason     失败原因
     */
    fun summitExchange(
        useFmlPoint: Number?, productQuantity: Number?,
        productID: String?, productName: String?,
        fmlPoint: Number?, memberFmlPoint: Number?,
        isSuccess: Boolean, errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("useFmlPoint", useFmlPoint)
            jsonObject.put("productQuantity", productQuantity)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("productID", productID)
            jsonObject.put("productName", productName)
            jsonObject.put("fmlPoint", fmlPoint)
            jsonObject.put("memberFmlPoint", memberFmlPoint)
            SensorsUtil.clickTrack(SensorsEventName.summitExchange, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 优惠券列表页浏览  点击进入优惠券列表页时触发
     *
     * @param pageFrom
     */
    fun couponListView(pageFrom: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            SensorsUtil.clickTrack(SensorsEventName.couponListView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 优惠券详情浏览  点击进入查看优惠券详情时触发
     *
     * @param pageFrom
     * @param couponID     优惠券ID
     * @param couponType   优惠券类型  '回馈老客户，新客户专属，节日活动
     * @param couponAmount 优惠券金额
     * @param couponStatus 优惠券状态  '将过期，长期可用，已使用，已过期
     */
    fun couponDetail(
        pageFrom: String?, couponAmount: Number?,
        couponID: String?, couponType: String?,
        couponStatus: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            jsonObject.put("couponAmount", couponAmount)
            jsonObject.put("couponID", couponID)
            jsonObject.put("couponType", couponType)
            jsonObject.put("couponStatus", couponStatus)
            SensorsUtil.clickTrack(SensorsEventName.couponDetail, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 扫付款码  点击相应按钮时触发
     */
    fun enterScanPay(pageFrom: String?, codeType: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageFrom", pageFrom)
            jsonObject.put("codeType", codeType)
            SensorsUtil.clickTrack(SensorsEventName.enterScanPay, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 扫付款码  点击相应按钮时触发
     */
    fun clickScanAlipay() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.clickScanAlipay, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 扫付款码  点击相应按钮时触发
     */
    fun clickScanWechat() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.clickScanWechat, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击储值金
     */
    fun clickStoreCard(
        currentStoreCardNum: String?,
        currentStoreCardMoney: Float
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("currentStoreCardNum", currentStoreCardNum)
            jsonObject.put("currentStoreCardMoney", currentStoreCardMoney.toDouble())
            SensorsUtil.clickTrack(SensorsEventName.clickStoreCard, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 切换储值卡成功状态
     */
    fun changeStoreCardResult(
        isSuccess: Boolean,
        errorReason: String?,
        storeCardNum: String?,
        storeCardAmount: Float
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("storeCardNum", storeCardNum)
            jsonObject.put("storeCardAmount", storeCardAmount.toDouble())
            SensorsUtil.clickTrack(SensorsEventName.changeStoreCardResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 切换储值卡
     */
    fun changeStoreCard(
        changeStoreCardNum: String?,
        changeStoreCardMoney: Float,
        changeStoreCardSort: Int,
        currentStoreCardNum: String?,
        currentStoreCardMoney: Float,
        currentStoreCardSort: Int
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("changeStoreCardNum", changeStoreCardNum)
            jsonObject.put("changeStoreCardMoney", changeStoreCardMoney.toDouble())
            jsonObject.put("changeStoreCardSort", changeStoreCardSort)
            jsonObject.put("currentStoreCardNum", currentStoreCardNum)
            jsonObject.put("currentStoreCardMoney", currentStoreCardMoney.toDouble())
            jsonObject.put("currentStoreCardSort", currentStoreCardSort)
            SensorsUtil.clickTrack(SensorsEventName.changeStoreCard, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 查看会员资料  点击进入会员资料页面时触发
     *
     * @param isContent 是否已有资料
     */
    fun memberPageView(isContent: Boolean) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isContent", isContent)
            SensorsUtil.clickTrack(SensorsEventName.memberPageView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 提交会员资料  有提交修改结果时触发
     *
     * @param isSuccess   是否
     * @param errorReason
     * @param sex         性别
     * @param familyName  姓
     * @param name        名
     * @param nickname    昵称
     * @param birth       生日
     */
    fun summitMemberPage(
        isSuccess: Boolean, errorReason: String?, sex: String?,
        familyName: String?, name: String?,
        nickname: String?, birth: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("sex", sex)
            jsonObject.put("familyName", familyName)
            jsonObject.put("name", name)
            jsonObject.put("nickname", nickname)
            jsonObject.put("birth", birth)
            SensorsUtil.clickTrack(SensorsEventName.summitMemberPage, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 修改设置功能  修改消息免打扰或订阅通知时触发
     *
     * @param act      动作   打开，关闭
     * @param function 功能  消息免打扰、订阅通知
     */
    fun changeFunction(act: String?, function: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("act", act)
            jsonObject.put("function", function)
            SensorsUtil.clickTrack(SensorsEventName.changeFunction, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击分享  点击分享按钮时触发
     *
     * @param shareID   分享ID   topiccode
     * @param shareName 分享内容  单品名称或者是专题名称
     */
    fun shareClick(
        shareID: String?,
        shareName: String?,
        screen_name: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("shareID", shareID)
            jsonObject.put("shareName", shareName)
            jsonObject.put("screen_name", screen_name)
            SensorsUtil.clickTrack(SensorsEventName.shareClick, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 选择分享方式  点击选择分享方式时触发
     *
     * @param shareID      分享ID   topiccode
     * @param shareName    分享内容  单品名称或者是专题名称
     * @param share_method 分享方式  微信、QQ、取消
     */
    fun shareOption(
        shareID: String?, shareName: String?,
        share_method: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("shareID", shareID)
            jsonObject.put("shareName", shareName)
            jsonObject.put("share_method", share_method)
            SensorsUtil.clickTrack(SensorsEventName.shareOption, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 分享结果  点击选择分享方式时触发
     *
     * @param isSuccess
     * @param errorReason
     * @param share_method 分享方式  微信、QQ、取消
     */
    fun shareResult(
        isSuccess: Boolean, errorReason: String?,
        share_method: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("share_method", share_method)
            SensorsUtil.clickTrack(SensorsEventName.shareResult, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 查看我的账单   加载我的账单、我的账单-数据统计页成功时触发
     *
     * @param pageType   页面类型  账单页，数据统计页
     * @param selectType 所选类型  默认，自定义
     */
    fun billPageView(pageType: String?, selectType: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pageType", pageType)
            jsonObject.put("selectType", selectType)
            SensorsUtil.clickTrack(SensorsEventName.billPageView, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 滑动运营位   我的账单-滑动顶部运营位（向右滑）并停留x秒时触发
     *
     * @param viewpointWidth 视区距左侧位置
     */
    fun slideBanner(viewpointWidth: Number?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("viewpointWidth", viewpointWidth)
            SensorsUtil.clickTrack(SensorsEventName.slideBanner, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 订单详情电子发票电子  在订单详情页点击申请电子发票时触发
     */
    fun applyElectronicInvoice() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.applyElectronicInvoice, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Push 通知送达  跳转页面、分群名称、Push 类型三个字段通过Push 服务的自定义参数实现数值定义，前端要协调好获取数据后上报
     *
     * @param pushID           PushID
     * @param pushContentID    Push内容 ID
     * @param pushContent      Push 内容
     * @param pageRedirect     跳转页面
     * @param segmentationName 分群名称
     * @param pushType         Push 类型
     */
    fun pushReceived(
        pushID: String?, pushContentID: String?,
        pushContent: String?, pageRedirect: String?,
        segmentationName: String?, pushType: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pushID", pushID)
            jsonObject.put("pushContentID", pushContentID)
            jsonObject.put("pushContent", pushContent)
            jsonObject.put("pageRedirect", pageRedirect)
            jsonObject.put("segmentationName", segmentationName)
            jsonObject.put("pushType", pushType)
            SensorsUtil.clickTrack(SensorsEventName.PushReceived, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Push Push点击  跳转页面、分群名称、Push 类型三个字段通过Push 服务的自定义参数实现数值定义，前端要协调好获取数据后上报
     *
     * @param pushID           PushID
     * @param pushContentID    Push内容 ID
     * @param pushContent      Push 内容
     * @param pageRedirect     跳转页面
     * @param segmentationName 分群名称
     * @param pushType         Push 类型
     */
    fun pushClick(
        pushID: String?, pushContentID: String?,
        pushContent: String?, pageRedirect: String?,
        segmentationName: String?, pushType: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("pushID", pushID)
            jsonObject.put("pushContentID", pushContentID)
            jsonObject.put("pushContent", pushContent)
            jsonObject.put("pageRedirect", pageRedirect)
            jsonObject.put("segmentationName", segmentationName)
            jsonObject.put("pushType", pushType)
            SensorsUtil.clickTrack(SensorsEventName.PushClick, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 扫码加入社群  在App内，社群扫码成功后上报
     *
     * @param isSuccess
     * @param errorReason
     * @param groupID     社群ID
     */
    fun scanToGroup(
        isSuccess: Boolean, errorReason: String?,
        groupID: String?, groupName: String?, pageFrom: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("groupID", groupID)
            jsonObject.put("groupName", groupName)
            jsonObject.put("pageFrom", pageFrom)
            SensorsUtil.clickTrack(SensorsEventName.scanToGroup, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 打开社群页面  进入社群页面时触发
     *
     * @param groupID 社群ID
     */
    fun enterGroup(
        groupID: String?, groupName: String?,
        pageFrom: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("groupID", groupID)
            jsonObject.put("groupName", groupName)
            jsonObject.put("pageFrom", pageFrom)
            SensorsUtil.clickTrack(SensorsEventName.enterGroup, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 兑换码页面曝光时触发
     */
    fun exchangeCodeExposure(
        exchangeActivityID: String?, exchangeActivityName: String?,
        isSuccess: Boolean, errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("exchangeActivityID", exchangeActivityID)
            jsonObject.put("exchangeActivityName", exchangeActivityName)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.exchangeCodeExposure, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击兑换页面按钮（跳过/提交）时触发
     */
    fun exchangeCodeOperation(operationType: String?) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("operationType", operationType)
            SensorsUtil.clickTrack(SensorsEventName.exchangeCodeOperation, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击提交后，前端收到后端返回结果时触发
     */
    fun exchangeCodeSummit(
        isSuccess: Boolean, errorReason: String?, isOperant: Boolean,
        exchangeActivityID: String?, exchangeActivityName: String?,
        exchangeCode: String?, exchangeItemType: String?,
        exchangeItemName: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            jsonObject.put("isOperant", isOperant)
            jsonObject.put("exchangeActivityID", exchangeActivityID)
            jsonObject.put("exchangeActivityName", exchangeActivityName)
            jsonObject.put("exchangeCode", exchangeCode)
            jsonObject.put("exchangeItemType", exchangeItemType)
            jsonObject.put("exchangeItemName", exchangeItemName)
            SensorsUtil.clickTrack(SensorsEventName.exchangeCodeSummit, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 用户属性
     */
    fun loginProfileSet(
        gender: String?, birthday: String?,
        phone_number: String?, userType: String?, is_login: Boolean
    ) {
        try {
            val properties = JSONObject()
            properties.put("gender", gender)
            properties.put("birthday", birthday)
            properties.put("phone_number", phone_number)
            properties.put("userType", userType)
            properties.put("is_login", is_login)
            // 设定用户属性
            SensorsDataAPI.sharedInstance().profileSet(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 用户属性
     */
    fun loginProfileSet(gender: String?, birthday: String?) {
        try {
            val properties = JSONObject()
            properties.put("gender", gender)
            properties.put("birthday", birthday)
            // 设定用户属性
            SensorsDataAPI.sharedInstance().profileSet(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    /**
     * 公共属性
     */
    //    public static void registerSuperProperties(String userType, boolean is_login) {
    //        // 将应用名称作为事件公共属性，后续所有 track() 追踪的事件都会自动带上 "AppName" 属性
    //        try {
    //            JSONObject properties = new JSONObject();
    //            properties.put("AppName", SensorsUtil.getAppName(AppApplication.getAppContext()));
    //            properties.put("userType", userType);
    //            properties.put("is_login", is_login);
    //            SensorsDataAPI.sharedInstance().registerSuperProperties(properties);
    //        } catch (JSONException e) {
    //            e.printStackTrace();
    //        }
    //    }
    /**
     * https://manual.sensorsdata.cn/sa/latest/tech_sdk_client_android_super-7538650.html#id-.SDKAPI(Android)v1.13-%E8%AE%BE%E7%BD%AE%E5%8A%A8%E6%80%81%E5%85%AC%E5%85%B1%E5%B1%9E%E6%80%A7
     * 公共属性可以用我们的动态公共属性去设置下
     * 不需要去清空公共属性，登录和退出登录的时候设置下
     *
     * @param isLogin
     */
    fun registerDynamicSuperProperties(
        userType: String,
        isLogin: Boolean
    ) {
        // 初始化 SDK 后，设置动态公共属性
        SensorsDataAPI.sharedInstance()
            .registerDynamicSuperProperties(object : SensorsDataDynamicSuperProperties {
                // 比如 isLogin() 是用于获取用户当前的登录状态，SDK 会自动获取 getDynamicSuperProperties 中的属性添加到触发的事件中。
                override fun getDynamicSuperProperties(): JSONObject? {
                    try {
                        val properties = JSONObject()
                        properties.put("userType", userType)
                        properties.put("is_login", isLogin)
                        return properties
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    return null
                }
            })
        Log.e(
            "zhh",
            " registerDynamicSuperProperties ： $userType , eventValue : $isLogin"
        )
    }

    /**
     * 社群公共属性
     */
    fun groupProfileSet(groupShopID: String?, groupShopName: String?) {
        try {
            val properties = JSONObject()
            properties.put("groupShopID", groupShopID)
            properties.put("groupShopName", groupShopName)
            // 设定用户属性
            SensorsDataAPI.sharedInstance().profileSet(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 标签页返回
     */
    fun onLableBackPressed() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.onLableBackPressed, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 门店码返回
     */
    fun onInviteBackPressed() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.onInviteBackPressed, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * main销毁
     */
    fun onMainOnDestroy() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.onMainOnDestroy, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 个人资料销毁
     */
    fun onSaveInfoDestroy() {
        try {
            val jsonObject = JSONObject()
            SensorsUtil.clickTrack(SensorsEventName.onSaveInfoDestroy, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 预发券
     */
    fun preCoupon(
        source: String?,
        isSuccess: Boolean,
        errorReason: String?
    ) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("source", source)
            jsonObject.put("isSuccess", isSuccess)
            jsonObject.put("errorReason", errorReason)
            SensorsUtil.clickTrack(SensorsEventName.preCoupon, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 个人中心埋点 积分 优惠券 米粒   用户属性
     *
     * @param mDatas
     */
//    fun profileSet(mDatas: PersonalBean) {
//        if (isNotEmpty(mDatas.getDiscountCoupon())) {
//            try {
//                SensorsDataAPI.sharedInstance().profileSet(
//                    "balance",
//                    NumberFormat.getNumberInstance(Locale.FRENCH)
//                        .parse(mDatas.getDiscountCoupon())
//                )
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//        }
//        if (isNotEmpty(mDatas.getMiliCount())) {
//            try {
//                SensorsDataAPI.sharedInstance().profileSet(
//                    "fmlPoint",
//                    NumberFormat.getNumberInstance(Locale.FRENCH)
//                        .parse(mDatas.getMiliCount())
//                )
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//        }
//        if (isNotEmpty(mDatas.getIntegral())) {
//            try {
//                SensorsDataAPI.sharedInstance().profileSet(
//                    "point",
//                    NumberFormat.getNumberInstance(Locale.FRENCH)
//                        .parse(mDatas.getIntegral())
//                )
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//        }
//        SensorsDataAPI.sharedInstance()
//            .profileSet("userType", if (mDatas.isIsZX()) "尊享会员" else "集享会员")
//        registerDynamicSuperProperties(
//            if (mDatas.isIsZX()) "尊享会员" else "集享会员",
//            true
//        )
//    }

    /**
     * 登录成功后埋点   绑定电话页面    用户属性   动态公共属性
     */
//    fun bandPhoneLogin(
//        context: Context?,
//        datas: UserInfoBean,
//        etPhone: String?,
//        loginType: Int
//    ) {
//        SensorsUtil.loginTrack(context)
//        loginProfileSet(
//            if (1 == datas.getGenderCd()) "男" else "女",
//            datas.getBirthday(),
//            etPhone,
//            if (datas.isZxMemberFlag()) "尊享会员" else "集享会员",
//            true
//        )
//        SensorsDataAPI.sharedInstance()
//            .profileSetOnce("registerTime", DateUtil.getDateFormatYMD()) //用户属性 注册时间
//        registerDynamicSuperProperties(
//            if (datas.isZxMemberFlag()) "尊享会员" else "集享会员",
//            true
//        )
//        if (loginType == 6) {
//            bandPhoneLoginResult("微信授权", true, "")
//        } else if (loginType == 3) {
//            bandPhoneLoginResult("支付宝授权", true, "")
//        }
//    }
//
//    /**
//     * 登录成功后埋点  手机号登录      用户属性   动态公共属性
//     */
//    fun login(
//        context: Context?,
//        datas: UserInfoBean,
//        etPhone: String?,
//        loginType: Int
//    ) {
//        SensorsUtil.loginTrack(context)
//        loginProfileSet(
//            if (1 == datas.getGenderCd()) "男" else "女",
//            datas.getBirthday(),
//            etPhone,
//            if (datas.isZxMemberFlag()) "尊享会员" else "集享会员",
//            true
//        )
//        SensorsDataAPI.sharedInstance().profileSetOnce("registerTime", DateUtil.getDateFormatYMD())
//        registerDynamicSuperProperties(
//            if (datas.isZxMemberFlag()) "尊享会员" else "集享会员",
//            true
//        )
//        if (loginType == 1) {
//            loginResult("手机号", true, "")
//        } else if (loginType == 2) {
//            loginResult("微信授权", true, "")
//        } else if (loginType == 3) {
//            loginResult("支付宝授权", true, "")
//        }
//    }
}