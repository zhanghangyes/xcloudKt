package com.example.xcloudkt.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.xcloudkt.util.refresh.RefreshCallBack
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 *BaseFragment
 */
abstract class BaseFragment : Fragment() {

    protected var mRefreshPage = 1

    protected var mRefreshCount = 10

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    lateinit var mContext: Context
    lateinit var mActivity: Activity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView(view, savedInstanceState)
        initData()
        lazyLoadDataPrepared()
    }

    override fun onHiddenChanged(hidden: Boolean) {//单个fragment
        super.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {//结合viewpager
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataPrepared()
        }
    }

    fun lazyLoadDataPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化 View
     */
    open fun initView(view: View?, savedInstanceState: Bundle?) {

    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 懒加载
     */
    open fun lazyLoad() {}

    /**
     * 设置刷新控件
     *
     * @param refreshLayout
     * @param callBack      访问网络回调
     */
    fun setRefresh(refreshLayout: SmartRefreshLayout, callBack: RefreshCallBack) {
        refreshLayout.setEnableOverScrollDrag(true)
            .setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    mRefreshPage++
                    callBack.getRefreshDate(2, mRefreshPage, mRefreshCount)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    mRefreshPage = 1
                    callBack.getRefreshDate(1, mRefreshPage, mRefreshCount)
                }
            })
    }

    /**
     * 网络访问完成 刷新控件
     *
     * @param refreshLayout
     * @param isLoadMore    false禁用下拉加载更多
     */
    protected fun setFinishRefresh(refreshLayout: SmartRefreshLayout?, isLoadMore: Boolean) {
        when (refreshLayout?.state) {
            RefreshState.Refreshing -> {
                refreshLayout.finishRefresh()
            }
            RefreshState.Loading -> {
                refreshLayout.finishLoadMore()
            }
            else -> {}
        }
        refreshLayout?.setEnableLoadMore(isLoadMore)
    }

}