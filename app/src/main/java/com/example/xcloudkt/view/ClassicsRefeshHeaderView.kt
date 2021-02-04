package com.example.xcloudkt.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.xcloudkt.R
import com.example.xcloudkt.util.glide.GlideRoundTransform
import com.example.xcloudkt.util.glide.ImageLoader
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import kotlinx.android.synthetic.main.refresh_head.view.*

/**
 * 刷新头部加载gif
 * Created by zh on 2020/8/28.
 */
class ClassicsRefeshHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), RefreshHeader {

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.refresh_head, this)
        ImageLoader.getInstance()?.loadingImage(
            context, R.drawable.refresh_head, iv,
            MultiTransformation(
                FitCenter(),
                GlideRoundTransform(context, 0)
            ), R.drawable.refresh_head
        )
        iv.visibility = View.GONE
        tv.visibility = View.GONE
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    /**
     * 设置主题颜色 （如果自定义的Header没有注意颜色，本方法可以什么都不处理）
     *
     * @param colors 对应Xml中配置的 srlPrimaryColor srlAccentColor
     */
    override fun setPrimaryColors(vararg colors: Int) {}

    /**
     * 尺寸定义初始化完成 （如果高度不改变（代码修改：setHeader），只调用一次, 在RefreshLayout#onMeasure中调用）
     *
     * @param kernel       RefreshKernel 核心接口（用于完成高级Header功能）
     * @param height       HeaderHeight or FooterHeight
     * @param extendHeight 最大拖动高度
     */
    override fun onInitialized(
        kernel: RefreshKernel,
        height: Int,
        extendHeight: Int
    ) {
    }

    /**
     * 【仅限框架内调用】手指拖动下拉（会连续多次调用，添加isDragging并取代之前的onPulling、onReleasing）
     *
     * @param isDragging    true 手指正在拖动 false 回弹动画
     * @param percent       下拉的百分比 值 = offset/footerHeight (0 - percent - (footerHeight+maxDragHeight) / footerHeight )
     * @param offset        下拉的像素偏移量  0 - offset - (footerHeight+maxDragHeight)
     * @param height        高度 HeaderHeight or FooterHeight
     * @param maxDragHeight 最大拖动高度
     */
    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    /**
     * 【仅限框架内调用】释放时刻（调用一次，将会触发加载）
     *
     * @param refreshLayout RefreshLayout
     * @param height        高度 HeaderHeight or FooterHeight
     * @param extendHeight  最大拖动高度
     */
    override fun onReleased(
        refreshLayout: RefreshLayout,
        height: Int,
        extendHeight: Int
    ) {
    }

    override fun onStartAnimator(
        refreshLayout: RefreshLayout,
        height: Int,
        extendHeight: Int
    ) {
        iv.visibility = View.VISIBLE
        tv.visibility = View.VISIBLE
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        if (success) {
            tv.text = REFRESH_HEADER_OK
        } else {
            tv.text = REFRESH_HEADER_ERROR
        }
        return 500 //延迟500毫秒之后再弹回
    }

    /**
     * 【仅限框架内调用】水平方向的拖动
     *
     * @param percentX  下拉时，手指水平坐标对屏幕的占比（0 - percentX - 1）
     * @param offsetX   下拉时，手指水平坐标对屏幕的偏移（0 - offsetX - LayoutWidth）
     * @param offsetMax 最大的偏移量
     */
    override fun onHorizontalDrag(
        percentX: Float,
        offsetX: Int,
        offsetMax: Int
    ) {
    }

    /**
     * 是否支持水平方向的拖动（将会影响到onHorizontalDrag的调用）
     *
     * @return 水平拖动需要消耗更多的时间和资源，所以如果不支持请返回false
     */
    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None, RefreshState.PullDownToRefresh -> {
                iv.visibility = View.VISIBLE
                tv.visibility = View.VISIBLE
                tv.text = REFRESH_HEADER_PULLDOWN
            }
            RefreshState.ReleaseToRefresh -> {
                iv.visibility = View.VISIBLE
                tv.visibility = View.VISIBLE
                tv.text = REFRESH_HEADER_RELEASE
            }
            RefreshState.Refreshing -> {
                iv.visibility = View.VISIBLE
                tv.visibility = View.VISIBLE
                tv.text = REFRESH_HEADER_REFRESHING
            }
        }
    }

    companion object {
        var REFRESH_HEADER_PULLDOWN = "下拉刷新"
        var REFRESH_HEADER_REFRESHING = "正在刷新数据中..."
        var REFRESH_HEADER_RELEASE = "松开刷新"
        var REFRESH_HEADER_OK = "刷新完成"
        var REFRESH_HEADER_ERROR = "刷新失败"
    }

    init {
        initView(context)
    }
}