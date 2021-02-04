package com.example.xcloudkt.sensors

import android.content.Context
import android.util.Log
import com.example.xcloudkt.BuildConfig
import com.example.xcloudkt.app.MyApp
import com.example.xcloudkt.bean.user.UserInfoBean
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.net.api.EnvType
import com.example.xcloudkt.util.StringUtils.isNotEmpty
import com.example.xcloudkt.util.sp.SpUtils
import com.sensorsdata.analytics.android.sdk.SAConfigOptions
import com.sensorsdata.analytics.android.sdk.SensorsAnalyticsAutoTrackEventType
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI
import org.json.JSONException
import org.json.JSONObject

/**
 * author : zhanghang
 * date : 2019/12/21 11:23
 */
object SensorsUtil {
    /**
     * 神策SDK初始化
     *
     * @param context
     * @param umeng_channel
     */
    fun init(context: Context?, umeng_channel: String?) {
        // 数据接收的 URL
        var SA_SERVER_URL = ""
        SA_SERVER_URL = if (BuildConfig.ENV_TYPE != EnvType.PRODUCT) {
            Constant.SA_SERVER_URL_DEBUG
        } else {
            Constant.SA_SERVER_URL
        }

        //设置 SAConfigOptions，传入数据接收地址 SA_SERVER_URL
        val saConfigOptions = SAConfigOptions(SA_SERVER_URL)

        //通过 SAConfigOptions 设置神策 SDK，每个条件都非必须，开发者可根据自己实际情况设置，更多设置可参考 SAConfigOptions 类中方法注释
        saConfigOptions.setAutoTrackEventType(
            SensorsAnalyticsAutoTrackEventType.APP_CLICK or  // 开启全埋点点击事件
                    SensorsAnalyticsAutoTrackEventType.APP_START or  //开启全埋点启动事件
                    SensorsAnalyticsAutoTrackEventType.APP_END or  //开启全埋点退出事件
                    SensorsAnalyticsAutoTrackEventType.APP_VIEW_SCREEN
        ) //开启全埋点浏览事件
            .enableLog(if (BuildConfig.ENV_TYPE == EnvType.PRODUCT) false else true) //开启神策调试日志，默认关闭(调试时，可开启日志)。
            .enableJavaScriptBridge(true) // 开启 App 打通 H5
            .enableTrackAppCrash() //开启 crash 采集
            .enableHeatMap(true) //开启 SDK 的点击分析功能
        //需要在主线程初始化神策 SDK
        SensorsDataAPI.startWithConfigOptions(context, saConfigOptions)
        AppInstall(umeng_channel)
        // 初始化 SDK 之后，开启自动采集 Fragment 页面浏览事件
        SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen()
        registerSuperProperties()
    }

    /**
     * 登录注册后的统计
     *
     * @param mainActivity
     */
    fun loginTrack(mainActivity: Context?) {
        val userInfoBean: UserInfoBean? = SpUtils.getUserInfo()
        if (userInfoBean != null) {
            val memberId: String = userInfoBean.memberCode.toString()
            if (isNotEmpty(memberId)) {
                // 关联 DistinctId（匿名 ID），Sensors Analytics 将 "9771C579-71F0-4650-8EE8-8999FA717761" 与 "developer@sensorsdata.cn" 关联，并认为两个 DistinctId 为同一个用户
                SensorsDataAPI.sharedInstance().login(memberId)
            }
        }
    }

    /**
     * app激活事件
     *
     * @param umeng_channel
     */
    fun AppInstall(umeng_channel: String?) {
        //记录 AppInstall 激活事件
        try {
            val properties = JSONObject()
            //这里的 DownloadChannel 负责记录下载商店的渠道，值应传入具体应用商店包的标记。如果没有为不同商店打多渠道包，则可以忽略该属性的代码示例。
            properties.put("DownloadChannel", umeng_channel)
            // 触发激活事件
            SensorsDataAPI.sharedInstance().trackInstallation("AppInstall", properties)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * app激活事件
     */
    fun registerSuperProperties() {
        // 将应用名称作为事件公共属性，后续所有 track() 追踪的事件都会自动带上 "AppName" 属性
        try {
            val properties = JSONObject()
            properties.put("AppName", getAppName(MyApp.mContext))
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 点击事件统计
     *
     * @param eventName  事件名称
     * @param eventValue 事件值
     */
    fun clickTrack(eventName: String, eventValue: JSONObject) {
        try {
            if (BuildConfig.DEBUG) Log.e(
                "zhh",
                " eventName ： $eventName , eventValue : $eventValue"
            )
            SensorsDataAPI.sharedInstance().track(eventName, eventValue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAppName(context: Context?): String? {
        if (context == null) {
            return null
        }
        try {
            val packageManager = context.packageManager
            val packageInfo =
                packageManager.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: Throwable) {
        }
        return null
    }
}