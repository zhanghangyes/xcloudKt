package com.example.xcloudkt.util.glide

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * 加载圆形头像带白色边框
 */
class GlideCircleWithBorder(context: Context?, borderWidth: Int, borderColor: Int) : BitmapTransformation() {
    private val mBorderPaint: Paint?
    private val mBorderWidth: Float
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(pool, toTransform)!!
    }

    private fun circleCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
        if (source == null) {
            return null
        }
        val size =
            (Math.min(source.width, source.height) - mBorderWidth / 2).toInt()
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squared = Bitmap.createBitmap(source, x, y, size, size)
        var result: Bitmap? = pool[size, size, Bitmap.Config.ARGB_8888]
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        }
        //创建画笔 画布 手动描绘边框
        val canvas = Canvas(result!!)
        val paint = Paint()
        paint.shader = BitmapShader(
            squared,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        if (mBorderPaint != null) {
            val borderRadius = r - mBorderWidth / 2
            canvas.drawCircle(r, r, borderRadius, mBorderPaint)
        }
        return result
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    init {
        mBorderWidth =
            Resources.getSystem().displayMetrics.density * borderWidth
        mBorderPaint = Paint()
        mBorderPaint.setDither(true)
        mBorderPaint.setAntiAlias(true)
        mBorderPaint.setColor(borderColor)
        mBorderPaint.setStyle(Paint.Style.STROKE)
        mBorderPaint.setStrokeWidth(mBorderWidth)
    }
}