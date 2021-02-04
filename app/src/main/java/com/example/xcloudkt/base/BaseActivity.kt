package com.example.xcloudkt.base

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.xcloudkt.R
import com.example.xcloudkt.eventBus.EventBusUtil
import com.example.xcloudkt.eventBus.EventT
import com.example.xcloudkt.util.ToastUtils
import com.example.xcloudkt.util.activity.ActivityManager
import com.example.xcloudkt.view.theme.StatusBarCompat
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *描述:BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        //设置状态栏主题什么之类
        doBeforeSetcontentView()
        super.onCreate(savedInstanceState)
        mContext = this.applicationContext
        mActivity = this
        ActivityManager.instance.addActivity(this)
        setContentView(getLayoutId())
        if (isRegisterEventBus()) {
            EventBusUtil.register(this)
        }
        initView(savedInstanceState)
        initData()
        initListener()
    }

    /**
     *  加载布局
     */
    abstract fun getLayoutId(): Int
    open fun initView(savedInstanceState: Bundle?) {}
    abstract fun initData()
    open fun initListener() {}

    /**
     * 设置layout前配置
     */
    private fun doBeforeSetcontentView() {
        //设置无标题主题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //设置方向
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //设置状态栏
        if (Build.VERSION.SDK_INT >= 20) {
            // 默认着色状态栏
            setStatusBarColor(R.color.white, 1)
        }
        //设置软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        //修复部分 Android 8.0 手机在TargetSDK 大于 26 时，在透明主题时指定 Activity 方向时崩溃的问题
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating) {
            fixOrientation()
        }
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected fun setTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this)
    }

    /**
     * 设置标题栏的颜色 着色状态栏（4.4以上系统有效）
     */
    fun setStatusBarColor(color: Int, flag: Int) {
        when (flag) {
            0 -> {
                StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color), 0)
            }
            1 -> {
                StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color), 0)
                StatusBarCompat.changeToLightStatusBar(this)
            }
        }
    }

    /**
     * 释放一些资源
     */
    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
        ActivityManager.instance.removeActivity(this)
    }

    protected fun addOnClickListeners(listener: View.OnClickListener, @IdRes vararg ids: Int) {
        for (id in ids) {
            findViewById<View>(id).setOnClickListener(listener)
        }
    }

    protected fun showToast(message: String?) {
        ToastUtils.showToast(message.toString())
    }

    protected fun showToast(@IdRes messageId: Int) {
        ToastUtils.showToast(messageId)
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected open fun isRegisterEventBus(): Boolean {
        return false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventBusCome(event: EventT<*>?) {
        if (event != null) {
            receiveEvent(event)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    open fun onStickyEventBusCome(event: EventT<*>?) {
        if (event != null) {
            receiveStickyEvent(event)
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected open fun receiveEvent(event: EventT<*>?) {}

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected open fun receiveStickyEvent(event: EventT<*>?) {}

    private var mExitTime: Long = 0
    open fun onDoubleCheck() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showToast("再按一次退出应用")
            mExitTime = System.currentTimeMillis()
        } else {
            exitApp()
        }
    }

    /**
     * 退出应用
     */
    open fun exitApp() {
        ActivityManager.instance.removeActivity(this)
    }

    /**
     * 判断当前主题是否是透明悬浮
     *
     * @return
     */
    private val isTranslucentOrFloating: Boolean
        private get() {
            var isTranslucentOrFloating = false
            try {
                val styleableRes = Class.forName("com.android.internal.R\$styleable")
                    .getField("Window")[null] as IntArray
                val ta = obtainStyledAttributes(styleableRes)
                val m = ActivityInfo::class.java.getMethod(
                    "isTranslucentOrFloating",
                    TypedArray::class.java
                )
                m.isAccessible = true
                isTranslucentOrFloating = m.invoke(null, ta) as Boolean
                m.isAccessible = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return isTranslucentOrFloating
        }

    /**
     * 改变当前的 Activity 的显示方向
     * 解决当前Android 8.0 系统在透明主题时设定显示方向时崩溃的问题
     */
    private fun fixOrientation(): Boolean {
        try {
            val field = Activity::class.java.getDeclaredField("mActivityInfo")
            field.isAccessible = true
            val o = field[this] as ActivityInfo
            o.screenOrientation = -1
            field.isAccessible = false
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        /*
         * 修复 Android 8.0 手机在TargetSDK 大于 26 时，指定 Activity 方向时崩溃的问题
         */
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating) {
            return
        }
        super.setRequestedOrientation(requestedOrientation)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 获取到Activity下的Fragment
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}