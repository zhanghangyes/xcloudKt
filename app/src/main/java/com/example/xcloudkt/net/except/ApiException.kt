package com.example.xcloudkt.net.except

/**
 *描述:异常处理
 */
class ApiException(var code: Int?, var msg: String?) : Exception()