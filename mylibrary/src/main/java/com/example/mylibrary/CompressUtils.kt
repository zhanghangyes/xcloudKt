package com.example.mylibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.WindowManager
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

/**
 * desc   : 图片压缩工具类
 */
object CompressUtils {
    /**
     * 获得屏幕宽度
     * @param context                               上下文
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获得屏幕高度
     * @param context                               上下文
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    /**
     * 将drawable转化成bitmap
     * @param drawable                              图片
     * @return
     */
    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val intrinsicWidth = drawable.intrinsicWidth
        val intrinsicHeight = drawable.intrinsicHeight
        val config =
            if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * 此处默认设置图片的压缩宽高是屏幕宽高
     * @param is                                    图片流
     * @param context                               上下文
     * @return
     */
    fun compressDrawableByBmp(
        `is`: InputStream?,
        context: Context
    ): Bitmap? {
        val bitmap = BitmapFactory.decodeStream(`is`)
        return compressBitmapByBmp(bitmap, context)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * 此处默认设置图片的压缩宽高是屏幕宽高
     * @param drawable                              图片drawable
     * @param context                               上下文
     * @return
     */
    fun compressDrawableByBmp(drawable: Drawable, context: Context): Bitmap? {
        val bitmap = drawableToBitmap(drawable)
        return compressBitmapByBmp(bitmap, context)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * 此处默认设置图片的压缩宽高是屏幕宽高
     * @param image                                 图片Bitmap
     * @param context                               上下文
     * @return
     */
    fun compressBitmapByBmp(image: Bitmap, context: Context): Bitmap? {
        val screenHeight = getScreenHeight(context)
        val screenWidth = getScreenWidth(context)
        return compressBitmapByBmp(image, screenWidth, screenHeight)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * @param is                                    图片流
     * @param pixelW                                宽
     * @param pixelH                                高
     * @return
     */
    fun compressBitmapByBmp(`is`: InputStream?, pixelW: Int, pixelH: Int): Bitmap? {
        val bitmap = BitmapFactory.decodeStream(`is`)
        return compressBitmapByBmp(bitmap, pixelW, pixelH)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * @param drawable                              图片drawable
     * @param pixelW                                宽
     * @param pixelH                                高
     * @return
     */
    fun compressBitmapByBmp(drawable: Drawable, pixelW: Int, pixelH: Int): Bitmap? {
        val bitmap = drawableToBitmap(drawable)
        return compressBitmapByBmp(bitmap, pixelW, pixelH)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * @param file                                  图片file
     * @param pixelW                                宽
     * @param pixelH                                高
     * @return
     */
    fun getBitmap(file: File?, pixelW: Int, pixelH: Int): Bitmap? {
        if (file == null) {
            return null
        }
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.absolutePath, options)
        options.inSampleSize = calculateInSampleSize(options, pixelW, pixelH)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(file.absolutePath, options)
    }

    /**
     * 通过大小压缩，将修改图片宽高
     * @param image                                 图片Bitmap
     * @param pixelW                                宽
     * @param pixelH                                高
     * @return
     */
    fun compressBitmapByBmp(image: Bitmap, pixelW: Int, pixelH: Int): Bitmap? {
        val os = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, os)
        if (os.toByteArray().size / 1024 > 1024) {
            //判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset()
            //重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, os)
            //这里压缩50%，把压缩后的数据存放到baos中
        }
        var `is` = ByteArrayInputStream(os.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        var bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        //be=1表示不缩放
        var be = calculateInSampleSize(newOpts, pixelW, pixelH)
        if (be <= 0) {
            be = 1
        }
        //设置缩放比例
        newOpts.inSampleSize = be
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        `is` = ByteArrayInputStream(os.toByteArray())
        bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
        bitmap = Bitmap.createScaledBitmap(bitmap!!, (w / be), (h / be), true)
        //压缩好比例大小后再进行质量压缩
        //return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     * @param filePath                              文件路径
     * @param newWidth                              宽
     * @param newHeight                             高
     * @return
     */
    fun getSmallBitmap(filePath: String?, newWidth: Int, newHeight: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val decodeFile = BitmapFactory.decodeFile(filePath, options)
        // Calculate inSampleSize
        // 计算图片的缩放值
        options.inSampleSize = calculateInSampleSize(options, newWidth, newHeight)
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        val bitmap = BitmapFactory.decodeFile(filePath, options)
        val bitmapByteCount = bitmap!!.byteCount
        // 质量压缩
        val newBitmap = compressImage(bitmap, 500)
        val byteCount = newBitmap!!.byteCount
        bitmap?.recycle()
        return newBitmap
    }

    /**
     * 通过像素压缩图片，将修改图片宽高
     * @param srcPath                           图片路径
     * @param pixelW                            宽
     * @param pixelH                            高
     * @return
     */
    fun compressBitmapByPath(
        srcPath: String?,
        pixelW: Float,
        pixelH: Float
    ): Bitmap {
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        //此时返回bm为空
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        //这里设置高度为800f
        //这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        //be=1表示不缩放
        var be = 1
        //如果宽度大的话根据宽度固定大小缩放
        if (w > h && w > pixelW) {
            be = (newOpts.outWidth / pixelW).toInt()
        } else if (w < h && h > pixelH) {
            //如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / pixelH).toInt()
        }
        if (be <= 0) {
            be = 1
        }
        //设置缩放比例
        newOpts.inSampleSize = be
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        //return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * 计算图片的缩放值
     * @param options                           属性
     * @param reqWidth                          宽
     * @param reqHeight                         高
     * @return
     */
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // 源图片的高度和宽度
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            val heightRatio =
                Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio =
                Math.round(width.toFloat() / reqWidth.toFloat())
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }

    /**
     * 质量压缩
     * @param image                             bitmap
     * @param maxSize                           最大值
     * @return
     */
    fun compressImage(image: Bitmap?, maxSize: Int): Bitmap? {
        val os = ByteArrayOutputStream()
        // 缩放
        var options = 80
        // 将位图存储到输出流中(不压缩)
        image!!.compress(Bitmap.CompressFormat.JPEG, options, os)
        // 循环压缩
        while (os.toByteArray().size / 1024 > maxSize) {
            // 清除
            os.reset()
            // 每次减去10
            options -= 10
            // 循环压缩到指定的大小
            image.compress(Bitmap.CompressFormat.JPEG, options, os)
        }
        var bitmap: Bitmap? = null
        val b = os.toByteArray()
        if (b.size != 0) {
            //使用BitmapFactory工厂，加载byte得到bitmap对象
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }
        return bitmap
    }
}