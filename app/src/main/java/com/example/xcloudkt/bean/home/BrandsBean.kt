package com.example.xcloudkt.bean.home

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 16:07
 */
class BrandsBean : Serializable {
    /**
     * name : 首页品牌专区1555555
     * jumpTypeCd : 2
     * jumpPageValue : null
     * jumpLinkUrl : http://www.baidu.com
     * relGiftPackId : null
     * imgUrl : https://yuwenjie-test-1259127987.cos.ap-shanghai.myqcloud.com/111/20200530/1590834500772/WechatIMG5.png
     * displayNo : 2
     */
    var name: String? = null
    val id: String? = null
    var jumpTypeCd = 0
    var jumpPageValue = 0
    var jumpLinkUrl: String? = null
    var relGiftPackId: String? = null
    var imgUrl: String? = null
    var displayNo = 0
    var menuValue: String? = null
    var jointParams: List<Int>? = null

}