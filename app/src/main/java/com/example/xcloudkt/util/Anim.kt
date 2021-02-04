package com.example.xcloudkt.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.fragment.app.FragmentActivity

/**
 *
 * author : zhanghang
 * date : 2020/10/20 11:00
 * 动画
 */
object Anim {
    fun anim(view: View) {
        val scaleX =
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.3f, 1f)
        val scaleY =
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.3f, 1f)
        val set = AnimatorSet()
        set.playTogether(scaleX, scaleY)
        set.duration = 600
        set.start()
    }

    fun animClose(view: View) {
        val scaleX =
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1.02f, 1f)
        val scaleY =
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.02f, 1f)
        val translationY = ObjectAnimator.ofFloat(
            view,
            View.TRANSLATION_Y,
            -10f,
            0f
        )
        val set = AnimatorSet()
        set.playTogether(scaleX, scaleY, translationY)
        set.duration = 200
        set.start()
    }
}