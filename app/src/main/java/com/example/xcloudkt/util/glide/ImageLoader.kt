package com.example.xcloudkt.util.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.xcloudkt.constant.IntentParams
import com.example.xcloudkt.eventBus.EventBusUtil
import com.example.xcloudkt.eventBus.EventT

/**
 * Created by Administrator on 2017/11/27 0027.
 * 图片加载工具类
 */
class ImageLoader {

    companion object {
        var mInstance: ImageLoader? = null
        fun getInstance(): ImageLoader? {
            if (null == mInstance) {
                synchronized(ImageLoader::class.java) {
                    if (null == mInstance) {
                        mInstance = ImageLoader()
                    }
                }
            }
            return mInstance
        }
    }

    /**
     * 普通的加载图片 带占位图
     *
     * @param context
     * @param url
     * @param imageView
     * @param multiTransformation 同时设置scaleType在设置圆角或者圆形
     */
    fun loadingImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?,
        multiTransformation: MultiTransformation<*>?,
        placeholder: Int
    ) {
        var requestOptions = RequestOptions()
        if (null != context) {
            if (placeholder != -1) requestOptions =
                requestOptions.placeholder(placeholder).error(placeholder)
            if (null != multiTransformation) requestOptions =
                requestOptions.transform(multiTransformation as Transformation<Bitmap>)
            Glide.with(context).load(url).apply(requestOptions).into(imageView!!)
        }
    }

    /**
     * 普通的加载图片 带占位图
     *
     * @param context
     * @param url
     * @param imageView
     * @param multiTransformation 同时设置scaleType在设置圆角或者圆形
     */
    fun loadingImageWithWH(
        context: Context?,
        url: Any?,
        imageView: ImageView?,
        multiTransformation: MultiTransformation<*>?,
        placeholder: Int,
        width: Int,
        height: Int
    ) {
        var requestOptions = RequestOptions()
        if (null != context) {
            if (placeholder != -1) requestOptions =
                requestOptions.placeholder(placeholder).error(placeholder)
            if (null != multiTransformation) requestOptions =
                requestOptions.transform(multiTransformation as Transformation<Bitmap>)
            Glide.with(context).load(url).override(width, height).apply(requestOptions)
                .into(imageView!!)
        }
    }

    /**
     * 普通的加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    fun loadingImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?
    ) {
        loadingImage(context, url, imageView, null, -1)
    }

    /**
     * 普通的加载图片 带展位图
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated("")
    fun loadImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?,
        requestOptions: RequestOptions,
        placeholder: Int
    ) {
        var requestOptions = requestOptions
        if (null != context) {
            if (placeholder != -1) requestOptions =
                requestOptions.placeholder(placeholder).error(placeholder)
            Glide.with(context).load(url).apply(requestOptions).into(imageView!!)
        }
    }

    /**
     * 普通的加载图片 不带占位图的
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated("")
    fun loadImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?,
        placeholder: Int
    ) {
        val requestOptions = RequestOptions()
        loadImage(context, url, imageView, requestOptions, placeholder)
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated("")
    fun loadCircleImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?,
        placeholder: Int
    ) {
        if (null != context) {
            loadImage(context, url, imageView, RequestOptions.circleCropTransform(), placeholder)
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated("")
    fun loadCircleImage(
        context: Context?,
        url: Any?,
        imageView: ImageView?
    ) {
        if (null != context) {
            loadCircleImage(context, url, imageView, -1)
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param round
     * @param imageView
     */
    @Deprecated("")
    fun loadRoundImage(
        context: Context?,
        url: Any?,
        round: Int,
        imageView: ImageView?,
        placeholder: Int
    ) {
        if (null != context) {
            val requestOptions = RequestOptions()
            requestOptions.transform(GlideRoundTransform(context, round))
            loadImage(context, url, imageView, requestOptions, placeholder)
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param round
     * @param imageView
     */
    @Deprecated("")
    fun loadRoundImage(
        context: Context?,
        url: Any?,
        round: Int,
        imageView: ImageView?
    ) {
        if (null != context) {
            loadRoundImage(context, url, round, imageView, -1)
        }
    }

    /**
     * 加载圆形带圆环图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @SuppressLint("CheckResult")
    fun loadCircleWithBorderImage(
        context: Context?,
        url: Any?,
        borderWidth: Int,
        borderColor: Int,
        imageView: ImageView?,
        placeholder: Int
    ) {
        if (null != context) {
            val requestOptions = RequestOptions()
            requestOptions.transform(
                GlideCircleWithBorder(
                    context,
                    borderWidth,
                    ContextCompat.getColor(context, borderColor)
                )
            )
            loadImage(context, url, imageView, requestOptions, placeholder)
        }
    }

    /**
     * 加载圆形带圆环图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated("")
    fun loadCircleWithBorderImage(
        context: Context?,
        url: Any?,
        borderWidth: Int,
        borderColor: Int,
        imageView: ImageView?
    ) {
        if (null != context) {
            loadCircleWithBorderImage(context, url, borderWidth, borderColor, imageView, -1)
        }
    }

    /**
     * 预加载图片
     * //缓存所有版本的图像
     * .diskCacheStrategy(DiskCacheStrategy.ALL)
     * //跳过磁盘缓存
     * .diskCacheStrategy(DiskCacheStrategy.NONE)
     * //只缓存原来分辨率的图片
     * .diskCacheStrategy(DiskCacheStrategy.DATA)
     * //只缓存最终的图片
     * .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
     * @param mUrl
     */
    fun preloadImage(context: Context?, mUrl: Any?) {
        if (null != context) {
            Glide.with(context).load(mUrl).diskCacheStrategy(DiskCacheStrategy.DATA)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("zhh", "预加载失败")
                        EventBusUtil.sendEvent(EventT(IntentParams.JUMP_MAIN, "")) //跳转到首页
                        return true //预加载失败
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("zhh", "预加载完成")
                        EventBusUtil.sendEvent(
                            EventT(
                                IntentParams.JUMP_MAIN_DATA,
                                model
                            )
                        ) //跳转到首页,加载图片
                        return true //预加载完成
                    }
                }).preload()
        }
    }

}