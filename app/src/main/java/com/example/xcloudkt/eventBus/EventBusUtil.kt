package com.example.xcloudkt.eventBus

import org.greenrobot.eventbus.EventBus

/**
 * author : zhanghang
 * date : 2020/5/16 15:34
 */
object EventBusUtil {
    fun register(subscriber: Any?) {
        EventBus.getDefault().register(subscriber)
    }

    fun unregister(subscriber: Any?) {
        EventBus.getDefault().unregister(subscriber)
    }

    @JvmStatic
    fun sendEvent(eventT: EventT<*>?) {
        EventBus.getDefault().post(eventT)
    }

    fun sendStickyEvent(eventT: EventT<*>?) {
        EventBus.getDefault().postSticky(eventT)
    }
}