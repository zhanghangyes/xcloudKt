package com.example.xcloudkt.bean.home

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 16:07
 */
class BannersBean : Serializable {
    /**
     * name : 首页轮播图1
     * jumpTypeCd : 1
     * jumpPageValue : 222
     * jumpLinkUrl :
     * relGiftPackId :
     * imgUrl : http://img/ghj6k5436g5hj465hj4.jpg
     * displayNo : 8
     */
    var id: String? = null
    var name: String? = null
    var jumpTypeCd = 0
    var jumpPageValue = 0
    var jumpLinkUrl: String? = null
    var relGiftPackId: String? = null
    var imgUrl: String? = null
    var displayNo = 0
    var menuValue: String? = null
    var jointParams: List<Int>? = null

}