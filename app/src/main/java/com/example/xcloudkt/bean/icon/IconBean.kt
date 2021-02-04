package com.example.xcloudkt.bean.icon

import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/14 16:07
 */
class IconBean : Serializable {
    //  "version":"v1.0",
    //         "endTime":"1605715200000",
    //            "name":"首页",
    //            "textColorNormal":"B0C4DE",    //未选中文字颜色
    //            "textColorLight":"F7B62D", //已选中 文字颜色
    //            "iconLight":"http://xxx.xxx",//选中图标
    //            "iconNormal":"http://xxx.xxx",//未选中图标
    //            "needParam":0,//是否需要拼接参数
    //            "jointParams":[1,2,3,4],//拼接参数 1.memberId 2.经纬度 3.城市 4.扩展(menu)5.商品货号 6.门店编码
    //            "jumpTypeCd":3//跳转类型 1：内部跳转2：内部h5 3 外部链接
    //            "iconType":0//图标类型:0普通图标1：中间图标
    //            "jumpPageValue : 1"本地跳转地址
    //            "jumpLinkUrl : 1"跳转链接
    var id = 0
    var name: String? = null
    var textColorNormal: String? = null
    var textColorLight: String? = null
    var sort = 0
    var iconLight: String? = null
    var iconNormal: String? = null
    var iconType = 0
    var menuValue: String? = null
    var jumpTypeCd = 0
    var jumpPageValue = 0
    var jumpLinkUrl: String? = null
    var jointParams: List<Int>? = null

}