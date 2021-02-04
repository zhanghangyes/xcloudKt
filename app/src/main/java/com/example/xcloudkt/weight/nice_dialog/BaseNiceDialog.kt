package com.example.xcloudkt.weight.nice_dialog

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.xcloudkt.R
import com.example.xcloudkt.weight.nice_dialog.Utils.dp2px
import com.example.xcloudkt.weight.nice_dialog.Utils.getScreenWidth

abstract class BaseNiceDialog : DialogFragment() {

    companion object {
        private const val MARGIN = "margin"
        private const val WIDTH = "width"
        private const val HEIGHT = "height"
        private const val DIM = "dim_amount"
        private const val GRAVITY = "gravity"
        private const val CANCEL = "out_cancel"
        private const val THEMES = "themes"
        private const val ANIM = "anim_style"
        private const val LAYOUT = "layout_id"
    }

    private var margin = 0 //左右边距 = 0
    private var width = 0 //宽度 = 0
    private var height = 0 //高度 = 0
    private var dimAmount = 0.5f //灰度深浅
    private var gravity = Gravity.CENTER //显示的位置
    private var outCancel = true //是否点击外部取消

    @StyleRes
    protected var themes: Int = R.style.NiceDialogStyle // dialog主题

    @StyleRes
    private var animStyle = 0

    @LayoutRes
    protected var layoutId = 0
    abstract fun intLayoutId(): Int
    abstract fun convertView(
        holder: ViewHolder?,
        dialog: BaseNiceDialog?
    )

    open fun initTheme(): Int {
        return themes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, initTheme())

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN)
            width = savedInstanceState.getInt(WIDTH)
            height = savedInstanceState.getInt(HEIGHT)
            dimAmount = savedInstanceState.getFloat(DIM)
            gravity = savedInstanceState.getInt(GRAVITY)
            outCancel = savedInstanceState.getBoolean(CANCEL)
            themes = savedInstanceState.getInt(THEMES)
            animStyle = savedInstanceState.getInt(ANIM)
            layoutId = savedInstanceState.getInt(LAYOUT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutId = intLayoutId()
        val view = inflater.inflate(layoutId, container, false)
        convertView(ViewHolder.create(view), this)
        return view
    }

    override fun onStart() {
        super.onStart()
        initParams()
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MARGIN, margin)
        outState.putInt(WIDTH, width)
        outState.putInt(HEIGHT, height)
        outState.putFloat(DIM, dimAmount)
        outState.putInt(GRAVITY, gravity)
        outState.putBoolean(CANCEL, outCancel)
        outState.putInt(THEMES, themes)
        outState.putInt(ANIM, animStyle)
        outState.putInt(LAYOUT, layoutId)
    }

    private fun initParams() {
        val window = dialog?.window
        if (window != null) {
            val lp = window.attributes
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount
            if (gravity != 0) {
                lp.gravity = gravity
            }
            when (gravity) {
                Gravity.LEFT, Gravity.LEFT or Gravity.BOTTOM, Gravity.LEFT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.LeftAnimation
                }
                Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.TopAnimation
                }
                Gravity.RIGHT, Gravity.RIGHT or Gravity.BOTTOM, Gravity.RIGHT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.RightAnimation
                }
                Gravity.BOTTOM -> if (animStyle == 0) {
                    animStyle = R.style.BottomAnimation
                }
                else -> {
                }
            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = getScreenWidth(context as Context) - 2 * dp2px(
                    context as Context,
                    margin.toFloat()
                )
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = dp2px(
                    context as Context,
                    width.toFloat()
                )
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.height = dp2px(
                    context as Context,
                    height.toFloat()
                )
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle)
            window.attributes = lp
        }
        isCancelable = outCancel
    }

    fun setMargin(margin: Int): BaseNiceDialog {
        this.margin = margin
        return this
    }

    fun setWidth(width: Int): BaseNiceDialog {
        this.width = width
        return this
    }

    fun setHeight(height: Int): BaseNiceDialog {
        this.height = height
        return this
    }

    fun setDimAmount(dimAmount: Float): BaseNiceDialog {
        this.dimAmount = dimAmount
        return this
    }

    fun setGravity(gravity: Int): BaseNiceDialog {
        this.gravity = gravity
        return this
    }

    fun setOutCancel(outCancel: Boolean): BaseNiceDialog {
        this.outCancel = outCancel
        return this
    }

    fun setAnimStyle(@StyleRes animStyle: Int): BaseNiceDialog {
        this.animStyle = animStyle
        return this
    }

    fun show(manager: FragmentManager): BaseNiceDialog {
        val ft = manager.beginTransaction()
        if (this.isAdded) {
            ft.remove(this).commit()
        }
        ft.add(this, System.currentTimeMillis().toString())
        ft.commitAllowingStateLoss()
        return this
    }

    fun showWelcome(manager: FragmentManager): BaseNiceDialog {
        val ft = manager.beginTransaction()
        if (this.isAdded) {
            ft.remove(this).commitAllowingStateLoss() //用于欢迎页，不需要必须确保状态被保存
        }
        ft.add(this, System.currentTimeMillis().toString())
        ft.commitAllowingStateLoss()
        return this
    }

}