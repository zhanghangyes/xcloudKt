package com.example.xcloudkt.base

/**
 *IBasePresenter
 */
interface IBasePresenter<in V : IBaseView> {

    fun attachView(view: V)

    fun detachView()
}