package com.example.xcloudkt.util

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.io.IOException
import java.net.URL
import java.util.concurrent.ExecutionException

/**
 * author : zhanghang
 * date : 2020/11/20 15:29
 */
object TextSelectUtil {
    /**
     * 对TextView设置不同状态时其文字颜色。
     */
    fun flushColorSelector(checkedColor: Int, uncheckedColor: Int): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(R.attr.state_selected),
                intArrayOf(-R.attr.state_selected)
            ), intArrayOf(checkedColor, uncheckedColor)
        )
    }

    /*
     *构建Drawable选择器
     */
    fun flushDrawableSelector(
        checked: Drawable?,
        unchecked: Drawable?
    ): StateListDrawable {
        val stateList =
            StateListDrawable()
        val state_selected = R.attr.state_selected
        stateList.addState(intArrayOf(state_selected), checked)
        stateList.addState(intArrayOf(-state_selected), unchecked)
        return stateList
    }

    /*
     * 将转换文件成Drawable
     * pathName就是图片存放的绝对路径
     */
    fun getDrawableFromPath(path: String?): Drawable? {
        return Drawable.createFromPath(path)
    }

    fun loadImageFromNetwork(imageUrl: String?): Drawable? {
        var drawable: Drawable? = null
        try {
            drawable = Drawable.createFromStream(URL(imageUrl).openStream(), "image.jpg")
        } catch (e: IOException) {
            Log.d("zhh", e.message!!)
        }
        if (drawable == null) {
            Log.d("zhh", "null drawable")
        } else {
            Log.d("zhh", "not null drawable")
        }
        return drawable
    }

    /**
     * 设置底部导航 textView 的selector
     *
     * @param textView
     * @param normalUrl  默认图标URl
     * @param checkedUrl 选中图标URL
     */
    fun setTSelectorDrawable(
        mFragmentActivity: FragmentActivity?,
        textView: TextView,
        normalUrl: String?,
        checkedUrl: String?
    ) {
        val stateListDrawable =
            StateListDrawable()
        Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<Drawable?> ->
            //先加载默认样式
            Glide.with(mFragmentActivity!!)
                .load(normalUrl)
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        emitter.onNext(resource)
                        emitter.onComplete()
                    }
                })
        }).subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Drawable?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(normalDrawable: Drawable) {
                    stateListDrawable.addState(
                        intArrayOf(-R.attr.state_checked),
                        normalDrawable
                    )
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    //再加载选中的样式
                    Glide.with(mFragmentActivity!!)
                        .load(checkedUrl)
                        .into(object : SimpleTarget<Drawable?>() {
                            override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable?>?
                            ) {
                                stateListDrawable.addState(
                                    intArrayOf(R.attr.state_checked),
                                    resource
                                )
                                //给textView设置selector   对应xml布局里的android:drawableTop 属性
                                textView.setCompoundDrawables(null, stateListDrawable, null, null)
                            }
                        })
                }
            })
    }

    /**
     * 获取图片缓存地址
     *
     * @param context
     * @param url
     * @return
     */
    fun getPath(context: Context?, url: String?): String {
        if (TextUtils.isEmpty(url)) return ""
        try {
            val cacheFile = Glide.with(context!!)
                .load(url)
                .downloadOnly(
                    Target.SIZE_ORIGINAL,
                    Target.SIZE_ORIGINAL
                )
                .get()
            return if (null != cacheFile) cacheFile.absolutePath else ""
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return ""
    }
}