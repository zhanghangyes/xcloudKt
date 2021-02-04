package com.example.xcloudkt.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.xcloudkt.bean.icon.NavigatorsBean
import com.example.xcloudkt.net.rx.ObserverSubscriberManager
import com.example.xcloudkt.net.rx.RetrofitManager
import com.example.xcloudkt.net.rx.RxManage
import com.example.xcloudkt.util.sp.JsonUtils
import com.example.xcloudkt.util.sp.SpUtils

class DownloadIconService : IntentService(TAG) {

    companion object {
        const val TAG = "DownloadIconService"
    }

    override fun onHandleIntent(intent: Intent?) {
        if (null == intent) return
        RetrofitManager.run {
            apiService.navigator()
                .compose(RxManage.rxSchedulerObservableHelper())
                .compose(RxManage.handleObservableResult<NavigatorsBean>())
                .subscribeWith(object : ObserverSubscriberManager<NavigatorsBean>() {
                    override fun onStart() {
                        super.onStart()
                        Log.d("zhh", "DownloadIconService==onStart")
                    }

                    override fun onNext(t: NavigatorsBean) {
                        Log.d("zhh", "DownloadIconService==onNext" + t.data.navigatorLists.size)
                        SpUtils.setNavigators(JsonUtils.instance?.gson?.toJson(t.data))
                    }

                    override fun onComplete() {
                        super.onComplete()
                        Log.d("zhh", "DownloadIconService==onComplete")
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        SpUtils.cleanNavigators()
                    }
                })
        }
    }


}