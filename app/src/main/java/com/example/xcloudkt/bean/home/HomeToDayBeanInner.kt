package com.example.xcloudkt.bean.home

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 17:25
 */
class HomeToDayBeanInner : Serializable {

        var categoryName: String? = null
        var picUrl: String? = null
        var categoryCodes: Array<String>? = null
        var promotionNo: String? = null
        var remainingDay = 0
        var remainingHour = 0
        var orderBy = 0
        var groupFlag: String? = null
        var activitySlogan: String? = null

}