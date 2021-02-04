package com.example.xcloudkt.net.api

import com.example.xcloudkt.BuildConfig

/**
 * ApiConstants
 */
object ApiConstants {
    /**
     * 切换环境
     */
    var environment: Environment? = null

    //服务协议       https://fmapp-h5.chinafamilymart.com.cn/h5/service.html
    var USER_SERVICE_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/service.html"

    //隐私协议   个人信息保护政策     https://fmapp-h5.chinafamilymart.com.cn/h5/agreement.html
    var USER_PRIVACY_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/agreement.html"

    //中国移动
    var USER_PRIVACY_CM = "https://wap.cmpassport.com/resources/html/contract.html"

    //中国联通
    var USER_PRIVACY_CU =
        "https://opencloud.wostore.cn/authz/resource/html/disclaimer.html?fromsdk=true"

    //中国电信
    var USER_PRIVACY_CT = "https://ctaccount.21cn.com/agreementList.html?hidetop=true&appKey="

    //证照资质
    var USER_LINECE = "http://fmapp-h5.chinafamilymart.com.cn/h5/license.html"

    //消息
    var MESSAGE = "http://fmapp-h5.chinafamilymart.com.cn/module/message.html?"

    //品牌专区
    var CONTENT = "http://fmapp-h5.chinafamilymart.com.cn/module/content.html?"

    //签到规则
    var SIGN_RULE_URL = "http://fmapp-h5.chinafamilymart.com.cn/module/member.html?"

    //门店搜索
    var ADDRESS = "https://nearstoreh5.sandload.cn/nearstore/index.html#/index"

    //领券
    var COUPONS = "https://static.maxxipoint.com/static/couponMain/index.html?"

    //领券中心灵活接口(从首页聚合接口)
    var COUPONS_FLEX = ""

    //积分购(从首页聚合接口 活动专区获取)
    var INTEGRAL = ""

    //积分换购微信支付 referer   https://parcoffeeh5test.sandload.cn
    //                         https://parcoffeeh5.sandload.cn
    var INTEGRAL_WX_DEBUG = "https://parcoffeeh5test.sandload.cn"
    var INTEGRAL_WX = "https://parcoffeeh5.sandload.cn"

    //分享社群二维码
    var SHARE_QR = "https://fmapp-download.chinafamilymart.com.cn/index.html?"

    //扫码购h5:
    var SCAN_GO = "https://scanfmh5.sandload.cn/saomagou/index.html?"

    //分享领券中心
    var SHARE_COUPONS = "https://image-uat2.maxxipoint.com/static/couponMain/index.html?"

    //非码外卖
    var TAKE_OUT_URL = "https://parcoffeeh5.sandload.cn/h5/index.html?"

    /**
     * 是否开启请求参数请求参数日志
     */
    const val IS_OPEN_DEBUG = true

    /*********************************************后台系统配置-开始 *************************************************************/
    //正式环境地址
    const val JAVA_RELEASE_HOST = "https://fmapp.chinafamilymart.com.cn/api/app/"

    //测试地址
    const val JAVA_UAT_HOST = "https://fmapp-uat.chinafamilymart.com.cn/api/app/"

    //dev环境
    const val JAVA_DEBUG_HOST = "https://fmapp-dev.chinafamilymart.com.cn/api/app/"

    /*********************************************后台系统配置-结束 *************************************************************/

    /**
     * host切换
     */
    enum class Environment(
        /**
         * JAVA接口
         */
        var javaHost: String
    ) {
        /**
         * 正式环境
         */
        RELEASE(JAVA_RELEASE_HOST),

        /**
         * dev环境
         */
        DEBUG(JAVA_DEBUG_HOST),

        /**
         * UAT环境
         */
        UAT(JAVA_UAT_HOST),
    }

    init {
        val envType: Int = BuildConfig.ENV_TYPE
        when (envType) {
            EnvType.DEVELOP -> {
                //开发环境
                environment = Environment.DEBUG
                USER_SERVICE_AGREEMENT = "http://fmapp-dev-h5.chinafamilymart.com.cn/h5/service.html"
                USER_PRIVACY_AGREEMENT = "http://fmapp-dev-h5.chinafamilymart.com.cn/h5/agreement.html"
                USER_LINECE = " http://fmapp-dev-h5.chinafamilymart.com.cn/h5/license.html"
                MESSAGE = "http://fmapp-dev-h5.chinafamilymart.com.cn/module/message.html?"
                CONTENT = "http://fmapp-dev-h5.chinafamilymart.com.cn/module/content.html?"
                SIGN_RULE_URL = "http://fmapp-dev-h5.chinafamilymart.com.cn/module/member.html?"
                SCAN_GO = "https://scanfmh5test.sandload.cn/saomagou/index.html?"
                SHARE_COUPONS = "https://image-uat2.maxxipoint.com/static/couponMain/index.html?"
                TAKE_OUT_URL = "https://parcoffeeh5test.sandload.cn/h5/index.html?"
            }
            EnvType.CHECK -> {
                //测试环境（uat）
                environment = Environment.UAT
                USER_SERVICE_AGREEMENT = "http://fmapp-uat-h5.chinafamilymart.com.cn/h5/service.html"
                USER_PRIVACY_AGREEMENT = "http://fmapp-uat-h5.chinafamilymart.com.cn/h5/agreement.html"
                USER_LINECE = " http://fmapp-uat-h5.chinafamilymart.com.cn/h5/license.html"
                MESSAGE = "http://fmapp-uat-h5.chinafamilymart.com.cn/module/message.html?"
                CONTENT = "http://fmapp-uat-h5.chinafamilymart.com.cn/module/content.html?"
                SIGN_RULE_URL = "http://fmapp-uat-h5.chinafamilymart.com.cn/module/member.html?"
                SCAN_GO = "https://scanfmh5test.sandload.cn/saomagou/index.html?"
                SHARE_COUPONS = "https://image-uat2.maxxipoint.com/static/couponMain/index.html?"
                TAKE_OUT_URL = "https://parcoffeeh5test.sandload.cn/h5/index.html?"
            }
            EnvType.PRODUCT -> {
                //生产环境
                environment = Environment.RELEASE
                USER_SERVICE_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/service.html"
                USER_PRIVACY_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/agreement.html"
                USER_LINECE = "http://fmapp-h5.chinafamilymart.com.cn/h5/license.html"
                MESSAGE = "http://fmapp-h5.chinafamilymart.com.cn/module/message.html?"
                CONTENT = "http://fmapp-h5.chinafamilymart.com.cn/module/content.html?"
                SIGN_RULE_URL = "http://fmapp-h5.chinafamilymart.com.cn/module/member.html?"
                SCAN_GO = "https://scanfmh5.sandload.cn/saomagou/index.html?"
                SHARE_COUPONS = "https://static.maxxipoint.com/static/couponMain/index.html?"
                TAKE_OUT_URL = "https://parcoffeeh5.sandload.cn/h5/index.html?"
            }
            else -> {
                //生产环境
                environment = Environment.RELEASE
                USER_SERVICE_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/service.html"
                USER_PRIVACY_AGREEMENT = "https://fmapp-h5.chinafamilymart.com.cn/h5/agreement.html"
                USER_LINECE = "http://fmapp-h5.chinafamilymart.com.cn/h5/license.html"
                MESSAGE = "http://fmapp-h5.chinafamilymart.com.cn/module/message.html?"
                CONTENT = "http://fmapp-h5.chinafamilymart.com.cn/module/content.html?"
                SIGN_RULE_URL = "http://fmapp-h5.chinafamilymart.com.cn/module/member.html?"
                SCAN_GO = "https://scanfmh5.sandload.cn/saomagou/index.html?"
                SHARE_COUPONS = "https://static.maxxipoint.com/static/couponMain/index.html?"
                TAKE_OUT_URL = "https://parcoffeeh5.sandload.cn/h5/index.html?"
            }
        }
    }
}