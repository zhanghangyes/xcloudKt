package com.example.xcloudkt.util

object DoubleClickUtil {
    private var lastClickTime: Long = 0

    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            return if (timeD in 1..499) {
                true
            } else {
                lastClickTime = time
                false
            }
        }

    val isFastDoubleClick2: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            return if (timeD in 1..999) {
                true
            } else {
                lastClickTime = time
                false
            }
        }
}