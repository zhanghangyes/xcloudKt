package com.example.xcloudkt.ui.fragment

import com.example.xcloudkt.R
import com.example.xcloudkt.base.BaseMvpFragment
import com.example.xcloudkt.bean.home.HomeInfoBean
import com.example.xcloudkt.bean.home.HomeToDayBean
import com.example.xcloudkt.mvp.contract.HomeContract
import com.example.xcloudkt.mvp.presenter.HomePresenter

class MineFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View {
    override fun startNetwork() {
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initData() {
    }

    override fun showHomeInfo(homeInfoBean: HomeInfoBean) {

    }

    override fun returnSpecialList(homeToDayBean: HomeToDayBean) {


    }
}