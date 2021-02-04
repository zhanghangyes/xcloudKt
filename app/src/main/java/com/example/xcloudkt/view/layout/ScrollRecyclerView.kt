package com.example.xcloudkt.view.layout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * author : zhanghang
 * date : 2020/6/23 10:32
 * 解决ScrollView与RecyclerView横向滚动时的事件冲突
 */
class ScrollRecyclerView : RecyclerView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    private var lastX = 0f
    private var lastY = 0f
    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        var intercept = super.onInterceptTouchEvent(e)
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = e.x
                lastY = e.y
            }
            MotionEvent.ACTION_MOVE -> {
                // 只要横向大于竖向，就拦截掉事件。
                // 部分机型点击事件（slopx==slopy==0），会触发MOVE事件。
                // 所以要加判断(slopX > 0 || sloy > 0)
                val slopX = Math.abs(e.x - lastX)
                val slopY = Math.abs(e.y - lastY)
                //  Log.log("slopX=" + slopX + ", slopY="  + slopY);
                if ((slopX > 0 || slopY > 0) && slopX > slopY) {
                    requestDisallowInterceptTouchEvent(true)
                    intercept = true
                }
            }
            MotionEvent.ACTION_UP -> intercept = false
        }
        // Log.log("intercept"+e.getAction()+"=" + intercept);
        return intercept
    }
}