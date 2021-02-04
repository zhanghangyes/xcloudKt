package com.example.xcloudkt.bean.icon

import com.example.xcloudkt.net.response.BaseBean
import java.util.*

/**
 * author : zhanghang
 * date : 2020/11/18 14:54
 */
class NavigatorsBean(val data: Data) : BaseBean() {
    data class Data(
        /**
         * 是否默认金刚位图标组:0 否 1 是
         */
        val defaultIconGroup: Int = 0,

        /**
         * 是否默认导航栏图标组:0 否 1 是
         */
        val defaultNavigatorGroup: Int = 0,
        val iconLists: List<IconBean> = ArrayList(),//金刚区
        val navigatorLists: List<IconBean> = ArrayList() //底部导航
    )
}