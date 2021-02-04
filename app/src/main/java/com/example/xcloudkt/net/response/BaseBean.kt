package com.example.xcloudkt.net.response

import java.io.Serializable

/**
 * 封装返回的数据
 * {"code":200,"msg":"成功!","data":{"id":"1","name":"zhh"}}
 */
open class BaseBean : Serializable {
    val code: Int? = null
    val msg: String = ""
}
