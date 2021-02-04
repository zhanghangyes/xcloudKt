package com.example.xcloudkt.bean

import java.io.Serializable

/**
 * author : zhanghang   不继承basebean
 * date : 2020/5/15 14:22
 */
class SplashBean : Serializable {

    var jumpPageValue: Int = 0
    var relayDisplayUrl: String? = null
    var replayTypeCd: Int = 0
    var giftPackId: String? = null
    var relayTargetUrl: String? = null
    var menuValue: String? = null
    var jointParams: ArrayList<Int>? = null

}