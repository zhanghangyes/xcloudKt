package com.example.xcloudkt.net.rx


import com.example.xcloudkt.base.IBaseView
import com.example.xcloudkt.net.except.ExceptionHandle
import io.reactivex.subscribers.ResourceSubscriber

/**
 * 描述:FlowableSubscriberManager
 */
abstract class FlowableSubscriberManager<T> : ResourceSubscriber<T> {
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
