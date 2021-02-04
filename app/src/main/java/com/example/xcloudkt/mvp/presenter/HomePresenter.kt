package com.example.xcloudkt.mvp.presenter

import com.example.xcloudkt.base.BaseRxPresenter
import com.example.xcloudkt.bean.home.HomeInfoBean
import com.example.xcloudkt.bean.home.HomeToDayBean
import com.example.xcloudkt.mvp.contract.HomeContract
import com.example.xcloudkt.net.rx.ObserverSubscriberManager
import com.example.xcloudkt.net.rx.OkHttpRequestBodyFactory
import com.example.xcloudkt.net.rx.RetrofitManager
import com.example.xcloudkt.net.rx.RxManage
import com.example.xcloudkt.util.GsonHandler
import okhttp3.RequestBody
import java.util.*

/**
 * 首页
 */
class HomePresenter : BaseRxPresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun queryHomeInfo(lat: String, lon: String, cityName: String) {
//        data.addProperty("lat", lat);
//        data.addProperty("lon", lon);
//        data.addProperty("cityName", cityName);
        val paramMap: MutableMap<String, String> = HashMap()
        paramMap["lat"] = lat
        paramMap["lon"] = lon
        paramMap["cityName"] = cityName
        val requestBody: RequestBody =
            OkHttpRequestBodyFactory.getJsonRequestBody(GsonHandler.toJson(paramMap))

        addSubscription(
            RetrofitManager.apiService.queryHomeInfo(requestBody)
                .compose(RxManage.rxSchedulerObservableHelper())
                .compose(RxManage.handleObservableResultT())
                .subscribeWith(
                    object : ObserverSubscriberManager<HomeInfoBean>(mView, true) {
                        override fun onNext(t: HomeInfoBean) {
                            mView?.dismissLoading()
                            this@HomePresenter.mView?.showHomeInfo(t)
                        }

                        override fun onError(e: Throwable) {
                            mView?.dismissLoading()
                            super.onError(e)
//                            ToastUtils.showToast("HomePresenter")
                        }
                    })
        )
    }

    override fun loadSpecialList(storeCode: String, cityName: String) {
        val paramMap: MutableMap<String, String> = HashMap()
        paramMap["storeCode"] = storeCode
        paramMap["cityName"] = cityName
        val requestBody: RequestBody =
            OkHttpRequestBodyFactory.getJsonRequestBody(GsonHandler.toJson(paramMap))

        addSubscription(
            RetrofitManager.apiService.querySpecialList(requestBody)
                .compose(RxManage.rxSchedulerObservableHelper())
                .compose(RxManage.handleObservableResult())
                .subscribeWith(object : ObserverSubscriberManager<HomeToDayBean>(mView, false) {
                    override fun onNext(t: HomeToDayBean) {
                        this@HomePresenter.mView?.returnSpecialList(t)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
        )
    }
}



