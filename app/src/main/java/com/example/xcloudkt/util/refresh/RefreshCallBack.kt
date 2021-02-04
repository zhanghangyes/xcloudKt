package com.example.xcloudkt.util.refresh

/**
 * 刷新的回调
 */
interface RefreshCallBack {
    /**
     * @param stat  1刷新 2加载更多
     * @param page  当前页数
     * @param count 当前count
     */
    fun getRefreshDate(stat: Int, page: Int, count: Int)
}