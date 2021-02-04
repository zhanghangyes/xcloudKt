package com.example.xcloudkt.bean.home

import android.text.TextUtils
import java.io.Serializable
import java.util.*

/**
 * author : zhanghang
 * date : 2020/5/14 14:42
 */
class HomeInfoBean : Serializable {
    val newarrivals: List<NewarrivalsBean?> = ArrayList<NewarrivalsBean?>() //新品上市
    val outSoonArrivals: List<NewarrivalsBean?> = ArrayList<NewarrivalsBean?>() //新品抢先知
    val banners: List<BannersBean?> = ArrayList<BannersBean?>()
    val brands: List<BrandsBean?> = ArrayList<BrandsBean?>()
    val activityInfos: List<BrandsBean?> = ArrayList<BrandsBean?>()
    val newArrivalsPeriod: List<NewArrivalsPeriodBean?> = ArrayList<NewArrivalsPeriodBean?>()

    val activityId: String? = null//活动id
    val periodNum: String? = null //"periodNum":2,//Tab个数
    val store: StoreBean? = null

    class StoreBean : Serializable {
        /**
         * storeCode : 门店编号
         * storeName : 门店名称
         * address : 门店地址
         * cityCode : 地区编号
         * cityName : 上海
         * distance : 2
         */
        val storeCode: String? = null
        val storeName: String? = null
        val address: String? = null
        val cityCode: String? = null
        val cityName: String? = null
        val distance: String? = null
            get() = if (TextUtils.isEmpty(field)) {
                "0"
            } else field
        val areaName: String? = null
        val areaCode: String? = null

    }

    /**
     * 动态配置领券中心url
     */
    var couponActivityUrl: String? = null

    /**
     * 动态配置领券中心的id
     */
    var couponActivityId: String? = null

}