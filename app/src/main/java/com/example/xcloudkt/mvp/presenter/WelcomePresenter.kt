package com.example.xcloudkt.mvp.presenter

import android.util.Log
import com.example.xcloudkt.base.BaseRxPresenter
import com.example.xcloudkt.bean.SplashBean
import com.example.xcloudkt.mvp.contract.WelcomeContract
import com.example.xcloudkt.net.rx.FlowableSubscriberManager
import com.example.xcloudkt.net.rx.OkHttpRequestBodyFactory
import com.example.xcloudkt.net.rx.RetrofitManager
import com.example.xcloudkt.net.rx.RxManage
import com.example.xcloudkt.util.GsonHandler
import okhttp3.RequestBody
import java.util.*

/**
 * 欢迎页
 */
class WelcomePresenter : BaseRxPresenter<WelcomeContract.View>(), WelcomeContract.Presenter {

    override fun getAD(cityName: String, adTypeCd: String) {
//            "cityName":"上海",//城市名称（必须）(移动端根据高德地图SDK获得)
//            "adTypeCd":1 //广告类型 1 闪屏 2 弹窗 3 新人礼
        val paramMap: MutableMap<String, String> = HashMap()
        paramMap["cityName"] = cityName
        paramMap["adTypeCd"] = adTypeCd
        val requestBody: RequestBody =
            OkHttpRequestBodyFactory.getJsonRequestBody(GsonHandler.toJson(paramMap))

        addSubscription(
            RetrofitManager.apiService.queryAD(requestBody)
                .compose(RxManage.rxSchedulerFlowableHelper())
                .compose(RxManage.handleFlowableResultT())
                .subscribeWith(
                    object : FlowableSubscriberManager<SplashBean>(mView, false) {
                        override fun onNext(t: SplashBean) {
                            this@WelcomePresenter.mView?.showAD(t)
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            this@WelcomePresenter.mView?.onError(e)
                            Log.d("zhh",e.toString())
                        }
                    })
        )
    }
}

