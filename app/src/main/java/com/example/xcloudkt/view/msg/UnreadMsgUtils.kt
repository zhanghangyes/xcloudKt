package com.example.xcloudkt.view.msg

import android.view.View
import android.widget.RelativeLayout

/**
 * 未读消息提示View,显示小红点或者带有数字的红点:
 * 数字一位,圆
 * 数字两位,圆角矩形,圆角是高度的一半
 * 数字超过两位,显示99+
 */
object UnreadMsgUtils {
    fun show(msgView: MsgView?, num: Int) {
        if (msgView == null) {
            return
        }
        val lp = msgView.layoutParams as RelativeLayout.LayoutParams
        val dm = msgView.resources.displayMetrics
        msgView.visibility = View.VISIBLE
        if (num <= 0) { //圆点,设置默认宽高
            msgView.setStrokeWidth(0)
            msgView.text = ""
            lp.width = (5 * dm.density).toInt()
            lp.height = (5 * dm.density).toInt()
            msgView.layoutParams = lp
        } else {
            lp.height = (18 * dm.density).toInt()
            if (num in 1..9) { //圆
                lp.width = (18 * dm.density).toInt()
                msgView.text = num.toString()
            } else if (num in 10..99) { //圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT
                msgView.setPadding((6 * dm.density).toInt(), 0, (6 * dm.density).toInt(), 0)
                // msgView.setText(num + "");
                //  lp.width = (int) (19 * dm.density);
                msgView.text = num.toString()
            } else { //数字超过两位,显示99+
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT
                msgView.setPadding((6 * dm.density).toInt(), 0, (6 * dm.density).toInt(), 0)
                msgView.text = "99+"
            }
            msgView.layoutParams = lp
        }
    }

    fun setSize(rtv: MsgView?, size: Int) {
        if (rtv == null) {
            return
        }
        val lp = rtv.layoutParams as RelativeLayout.LayoutParams
        lp.width = size
        lp.height = size
        rtv.layoutParams = lp
    }
}