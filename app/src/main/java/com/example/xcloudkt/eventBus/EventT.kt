package com.example.xcloudkt.eventBus

/**
 * author : zhanghang
 * date : 2020/5/16 15:38
 */
class EventT<T> {
    var code: String? = null
    var data: T? = null
        private set

    constructor(code: String?, data: T) {
        this.code = code
        this.data = data
    }

    constructor(data: T) {
        this.data = data
    }

    constructor(code: String?) {
        this.code = code
    }

    fun setData(data: T) {
        this.data = data
    }
}