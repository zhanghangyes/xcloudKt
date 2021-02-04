package com.example.xcloudkt.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xcloudkt.MainActivity
import com.example.xcloudkt.R
import com.example.xcloudkt.base.BaseMvpActivity
import com.example.xcloudkt.bean.SplashBean
import com.example.xcloudkt.constant.AdType
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.constant.IntentParams
import com.example.xcloudkt.eventBus.EventT
import com.example.xcloudkt.mvp.contract.WelcomeContract
import com.example.xcloudkt.mvp.presenter.WelcomePresenter
import com.example.xcloudkt.util.DensityUtil
import com.example.xcloudkt.util.activity.ActivityUtils
import com.example.xcloudkt.util.glide.ImageLoader
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * 欢迎页
 */
class WelcomeActivity : BaseMvpActivity<WelcomeContract.View, WelcomePresenter>(),
    WelcomeContract.View, View.OnClickListener {
    //倒计时总时间
    private val COUNT_DOWN_TIME = 5200L

    //间隔
    private val COUNT_DOWNT_IMEINTERAL = 1000L

    //倒计时
    private var countDownTimer: CountDownTimer? = null

    override fun getLayoutId(): Int = R.layout.activity_welcome
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //初始化按钮
        setJumpButtonTopMargin()
    }

    /**
     * 设置距离顶部距离
     */
    private fun setJumpButtonTopMargin() {
        val closeLayoutParams = tv_jump.layoutParams as RelativeLayout.LayoutParams
        closeLayoutParams.topMargin = DensityUtil.statusHeight
        tv_jump.layoutParams = closeLayoutParams
    }

    override fun initData() {
        setTranslanteBar()
        mPresenter?.getAD(Constant.DEFULT_LOCATION, AdType.AD_TYPE_SPLASH)
    }

    override fun initListener() {
        addOnClickListeners(this, R.id.tv_jump)
    }


    override fun createPresenter(): WelcomePresenter {
        return WelcomePresenter()
    }

    override fun showAD(splashBean: SplashBean) {
        if (null == splashBean) {
            intentToMain()
            return
        }
        if (TextUtils.isEmpty(splashBean.relayDisplayUrl)) {
            intentToMain()
            return
        }
        ImageLoader.getInstance()?.preloadImage(mContext, splashBean.relayDisplayUrl)
    }

    override fun onError(throwable: Throwable) {
        intentToMain()
    }

    /**
     * 跳转主页
     */
    private fun intentToMain() {
        ActivityUtils.startActivity(this@WelcomeActivity, MainActivity::class.java, null)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null)
            countDownTimer?.cancel()
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    override fun receiveStickyEvent(event: EventT<*>?) {
        if (event == null) return
        when (event.code) {
            IntentParams.JUMP_MAIN -> intentToMain()
            IntentParams.JUMP_MAIN_DATA -> {
                countDownTimer = MyCountDownTimer(COUNT_DOWN_TIME, COUNT_DOWNT_IMEINTERAL)
                countDownTimer?.start()
                tv_jump.visibility = View.VISIBLE
                if ((event.data as String).endsWith("gif")) {
                    iv_picture.background = resources.getDrawable(R.drawable.shape_dialog_radius8)
                }
                Glide.with(mContext).load(event.data.toString())
                    .diskCacheStrategy(DiskCacheStrategy.DATA).into(iv_picture)
            }
        }
    }

    /**
     * 倒计时计时器
     */
    private inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            tv_jump.text = (getString(R.string.welcome_jump))
            intentToMain()
        }

        override fun onTick(millisUntilFinished: Long) {
            tv_jump.text =
                ((millisUntilFinished / 1000).toString() + getString(R.string.welcome_jump))
        }
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_jump -> intentToMain()
        }
    }


}



