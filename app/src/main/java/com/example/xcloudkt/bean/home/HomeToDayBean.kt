package com.example.xcloudkt.bean.home

import com.example.xcloudkt.net.response.BaseBean

/**
 * author : zhanghang
 * date : 2020/5/14 17:25
 */
class HomeToDayBean(val data: Data) : BaseBean() {
    data class Data(
        var list: List<HomeToDayBeanInner>? = null
    )

}