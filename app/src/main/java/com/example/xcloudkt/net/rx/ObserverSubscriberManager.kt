package com.example.xcloudkt.net.rx

import com.example.xcloudkt.base.IBaseView
import com.example.xcloudkt.net.except.ExceptionHandle
import io.reactivex.observers.ResourceObserver

/**
 *
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

abstract class ObserverSubscriberManager<T> : ResourceObserver<T> {
    var mView: IBaseView? = null

    /**
     * 是否显示加载中
     */
    private var isShowLoading = true

    constructor() {

    }

    constructor(mView: IBaseView?) {
        this.mView = mView
    }

    constructor(mView: IBaseView?, isShowLoading: Boolean) {
        this.mView = mView
        this.isShowLoading = isShowLoading
    }

    override fun onStart() {
        super.onStart()
        if (isShowLoading) {
            mView?.showLoading()
        }
    }

    override fun onNext(t: T) {
        mView?.showContent()
    }

    override fun onComplete() {
        if (isShowLoading) {
            mView?.dismissLoading()
        }
    }

    override fun onError(e: Throwable) {
        if (isShowLoading) {
            mView?.dismissLoading()
        }
        ExceptionHandle.handleException(e, mView)
    }
}
