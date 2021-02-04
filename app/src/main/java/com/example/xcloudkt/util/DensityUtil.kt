package com.example.xcloudkt.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.xcloudkt.app.MyApp.Companion.mContext

/**
 * 屏幕工具类，涉及到屏幕宽度、高度、密度比、(像素、dp、sp)之间的转换等。
 */
object DensityUtil {
    /**
     * 获取屏幕宽度，单位为px
     */
    val screenWidth: Int
        get() = displayMetrics.widthPixels

    /**
     * 获取屏幕高度，单位为px
     */
    val screenHeight: Int
        get() = displayMetrics.heightPixels

    /**
     * 获取系统dp尺寸密度值
     */
    val density: Float
        get() = displayMetrics.density

    /**
     * 获取系统字体sp密度值
     */
    val scaledDensity: Float
        get() = displayMetrics.scaledDensity

    /**
     * dip转换为px大小
     *
     * @param dpValue dp值
     * @return 转换后的px值
     */
    fun dp2px(dpValue: Int): Int {
        return (dpValue * density + 0.5f).toInt()
    }

    /**
     * px转换为dp值
     *
     * @param pxValue px值
     * @return 转换后的dp值
     */
    fun px2dp(pxValue: Int): Int {
        return (pxValue / density + 0.5f).toInt()
    }

    /**
     * sp转换为px
     *
     * @param spValue sp值
     * @return 转换后的px值
     */
    fun sp2px(spValue: Int): Int {
        return (spValue * scaledDensity + 0.5f).toInt()
    }

    /**
     * px转换为sp
     *
     * @param pxValue px值
     * @return 转换后的sp值
     */
    fun px2sp(pxValue: Int): Int {
        return (pxValue / scaledDensity + 0.5f).toInt()
    }

    /**
     * 获得状态栏的高度
     *
     * @return
     */
    val statusHeight: Int
        get() {
            var statusHeight = -1
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val height = clazz.getField("status_bar_height")[`object`].toString().toInt()
                statusHeight = mContext.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusHeight
        }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithStatusBar(activity: Activity): Bitmap {
        val decorView = activity.window.decorView
        decorView.isDrawingCacheEnabled = true
        decorView.buildDrawingCache()
        val bmp = decorView.drawingCache
        val width = screenWidth
        val height = screenHeight
        val bitmap: Bitmap
        bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height)
        decorView.destroyDrawingCache()
        return bitmap
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithoutStatusBar(activity: Activity): Bitmap {
        val decorView = activity.window.decorView
        decorView.isDrawingCacheEnabled = true
        decorView.buildDrawingCache()
        val bmp = decorView.drawingCache
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusHeight = frame.top
        val width = screenWidth
        val height = screenHeight
        val bitmap: Bitmap
        bitmap = Bitmap.createBitmap(bmp, 0, statusHeight, width, height - statusHeight)
        decorView.destroyDrawingCache()
        return bitmap
    }

    /**
     * 获取DisplayMetrics对象
     *
     * @return
     */
    val displayMetrics: DisplayMetrics
        get() {
            val manager =
                mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            manager.defaultDisplay.getMetrics(metrics)
            return metrics
        }
}