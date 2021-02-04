package com.example.xcloudkt.util

/**
 * 截取h5字段
 */
object ViewH5Substring {
    fun getValueByName(url: String, name: String): String {
        var result = ""
        val index = url.indexOf("?")
        val temp = url.substring(index + 1)
        val keyValue = temp.split("&".toRegex()).toTypedArray()
        for (str in keyValue) {
            if (str.contains(name)) {
                result = str.replace("$name=", "")
                break
            }
        }
        return result
    }
}