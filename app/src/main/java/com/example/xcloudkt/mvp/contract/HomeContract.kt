package com.example.xcloudkt.mvp.contract

import com.example.xcloudkt.base.IBasePresenter
import com.example.xcloudkt.base.IBaseView
import com.example.xcloudkt.bean.home.HomeInfoBean
import com.example.xcloudkt.bean.home.HomeToDayBean

/**
 *首页
 */
interface HomeContract {

    interface View : IBaseView {

        fun showHomeInfo(homeInfoBean: HomeInfoBean)

        fun returnSpecialList(homeToDayBean: HomeToDayBean)

    }

    interface Presenter : IBasePresenter<View> {

        fun queryHomeInfo(lat: String, lon: String, cityName: String)

        fun loadSpecialList(storeCode: String, cityName: String)
    }
}