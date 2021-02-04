package com.example.xcloudkt.mvp.contract

import com.example.xcloudkt.base.IBasePresenter
import com.example.xcloudkt.base.IBaseView
import com.example.xcloudkt.bean.SplashBean

/**
 *欢迎页
 */
interface WelcomeContract {

    interface View : IBaseView {

        fun showAD(splashBean: SplashBean)

        fun onError(throwable: Throwable)

    }

    interface Presenter : IBasePresenter<View> {

        fun getAD(cityName: String, adTypeCd: String)
    }
}