package com.example.xcloudkt.net.response

import java.io.Serializable

/**
 * 通用的封装返回的数据
 */
class BaseResponseBean<T> : Serializable {
    var data: T? = null
    val code: Int? = null
    val msg: String = ""
}