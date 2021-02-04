package com.example.xcloudkt.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 *BaseRxPresenter:
 */
open class BaseRxPresenter<V : IBaseView> : IBasePresenter<V> {
    var mView: V? = null
        private set

    private var compositeDisposable: CompositeDisposable? = null

    fun addSubscription(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        mView = null
        compositeDisposable?.run {
            if (!isDisposed) {
                clear()
            }
        }
    }
}