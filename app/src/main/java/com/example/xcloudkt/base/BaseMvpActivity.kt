package com.example.xcloudkt.base

import android.os.Bundle
import com.example.xcloudkt.util.ToastUtils
import com.example.xcloudkt.view.LoadingUtil
import com.example.xcloudkt.view.layout.MultipleStatusView

/**
 *BaseMvpActivity
 */
abstract class BaseMvpActivity<in V : IBaseView, P : IBasePresenter<V>> : BaseActivity(),
    IBaseView {

    var mPresenter: P? = null

    /**
     * 多种状态的 View 的切换
     */
    var mLayoutStatusView: MultipleStatusView? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    /**
     * 创建Presenter
     */
    abstract fun createPresenter(): P

    override fun initListener() {
        super.initListener()
        mLayoutStatusView?.setOnClickListener { initData()}
    }

    /**
     * 释放一些资源
     */
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    /**
     * 加载中
     */
    override fun showLoading() {
        mLayoutStatusView?.showLoading() ?: LoadingUtil.showLoading(mActivity, "加载中...")
    }

    /**
     * 取消加载
     */
    override fun dismissLoading() {
        LoadingUtil.dismissLoading()
    }

    /**
     * 无网络
     */
    override fun showNoNetwork() {
        mLayoutStatusView?.showNoNetwork()
    }

    /**
     * 显示内容视图
     */
    override fun showContent() {
        mLayoutStatusView?.showContent()
    }

    /**
     * 显示错误视图
     */
    override fun showError() {
        mLayoutStatusView?.showError()
    }

    /**
     * 显示错误提示
     */
    override fun showErrorMsg(msg: String) {
        ToastUtils.showToastLong(msg)
    }

}