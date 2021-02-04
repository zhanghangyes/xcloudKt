package com.example.xcloudkt.ui.activity.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.xcloudkt.R
import com.example.xcloudkt.base.BaseMvpActivity
import com.example.xcloudkt.bean.SplashBean
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.mvp.contract.WelcomeContract
import com.example.xcloudkt.mvp.presenter.WelcomePresenter

class LoginActivity : BaseMvpActivity<WelcomeContract.View, WelcomePresenter>(),
    WelcomeContract.View, View.OnClickListener {

    companion object {
        fun start(context: Context, i: Int, type: Int) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra(Constant.WEBLOGIN, i)
            context.startActivity(intent)
        }
    }

    override fun createPresenter(): WelcomePresenter {
        return WelcomePresenter()
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initData() {
    }

    override fun showAD(splashBean: SplashBean) {
    }

    override fun onClick(v: View?) {
    }

    override fun onError(throwable: Throwable) {
    }
}