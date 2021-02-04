package com.example.xcloudkt.bean.home

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 16:07
 */
class NewarrivalsBean : Serializable {
    /**
     * productCode : 20618681
     * productName : 虎镖足立爽洁肤喷剂
     * price : 2800
     * picUrl : http://oi46l3pzo.bkt.clouddn.com/quanjia0362122-hd_1522393178.jpg
     * desc : 用于脚部异味、脚痒、手足多汗、手足脱皮等。用温水洗净脚后，喷洒于脚部，一周内使用2-3次即可
     * bigCategory : 73
     * midCategory : 730
     * subCategory : 730002
     */
    var productCode: String? = null
    var productName: String? = null
    var price: String? = null
    var picUrl: String? = null
    var desc: String? = null
    var bigCategory: String? = null
    var midCategory: String? = null
    var subCategory: String? = null
    val relayUrl //跳转飞马
            : String? = null
    val statusCd //生效状态 1已生效 0未生效
            : String? = null
    val newEffectiveDate //新品生效时间
            : String? = null
    val cartCd //0不展示购物车图标   1展示
            = 0

}