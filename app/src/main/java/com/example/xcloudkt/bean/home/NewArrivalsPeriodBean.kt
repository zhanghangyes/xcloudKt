package com.example.xcloudkt.bean.home

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 16:07
 */
class NewArrivalsPeriodBean : Serializable {
    val periodName: String? = null
    val list: List<ListBean>? = null

    class ListBean : Serializable {
        var productCode: String? = null
        var productName: String? = null
        var price: String? = null
        val picUrl: String? = null
        val desc: String? = null
        val bigCategory: String? = null
        val midCategory: String? = null
        val subCategory: String? = null
        val relayUrl: String? = null
        val sort: String? = null
        val relayTypeCd: String? = null
        val statusCd: String? = null
        val newEffectiveDate: String? = null
        val randomCd: String? = null
        val effectCd: String? = null
        val newEffectiveTimestamp: String? = null

    }
}