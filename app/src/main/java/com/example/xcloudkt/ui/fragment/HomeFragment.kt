package com.example.xcloudkt.ui.fragment

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.*
import com.example.xcloudkt.adapter.adapter.MultiItemTypeAdapter.OnItemClickListener
import com.example.xcloudkt.adapter.adapter.wrapper.HeaderAndFooterWrapper
import com.example.xcloudkt.base.BaseMvpFragment
import com.example.xcloudkt.bean.home.*
import com.example.xcloudkt.bean.icon.IconBean
import com.example.xcloudkt.mvp.contract.HomeContract
import com.example.xcloudkt.mvp.presenter.HomePresenter
import com.example.xcloudkt.util.ToastUtils
import com.example.xcloudkt.util.glide.ImageLoader
import com.example.xcloudkt.util.refresh.RefreshCallBack
import com.example.xcloudkt.util.sp.SpUtils
import com.example.xcloudkt.view.msg.UnreadMsgUtils
import com.example.xcloudkt.weight.decoration.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View,
    RefreshCallBack {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initData() {
        initAdapter()
        UnreadMsgUtils.show(rtv_msg_tip, 50)
        setRefresh(refreshLayout, this)
        setFinishRefresh(refreshLayout, false)
        startNetwork()
    }

    /**
     * 金刚区
     */
    private fun initAdapter() {
        val navigators = SpUtils.getNavigators()
        if (null != navigators && navigators.defaultIconGroup == 0 && !navigators.iconLists.isEmpty()) {
            recyclerView_icon.visibility = View.VISIBLE
            initIcon(navigators.iconLists)
        } else {
            recyclerView_icon.visibility = View.GONE
        }
    }

    /**
     * 金刚区
     */
    private fun initIcon(iconLists: List<IconBean>) {
        val homeIconAdapter = HomeIconAdapter(mContext, iconLists)
        val gm = GridLayoutManager(mContext, 4)
        recyclerView_icon.addItemDecoration(
            GridSpacingItemDecoration(
                4,
                40,
                resources.getDimensionPixelSize(R.dimen.dp20),
                false
            )
        )
        recyclerView_icon.layoutManager = gm
        recyclerView_icon.adapter = homeIconAdapter
        homeIconAdapter.notifyDataSetChanged()
    }

    override fun startNetwork() {
        mPresenter?.queryHomeInfo("31.173321", "121.369679", "上海")
        mPresenter?.loadSpecialList("202345", "上海")
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun showHomeInfo(homeInfoBean: HomeInfoBean) {
        setFinishRefresh(refreshLayout, false)
        tv_store.text = "上海"
        loadBanner_Newarrivals_Brand(homeInfoBean, 1)
    }

    override fun returnSpecialList(homeToDayBean: HomeToDayBean) {
        setFinishRefresh(refreshLayout, false)
        if (homeToDayBean.data.list?.isNotEmpty()!!) {
            initRecyclerViewToDay(homeToDayBean.data.list)
        } else {
        }
    }

    private fun initRecyclerViewToDay(list: List<HomeToDayBeanInner>?) {
        val linearLayoutManager: LinearLayoutManager =
            object : LinearLayoutManager(activity, VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

        val homeToDayAdapter = HomeToDayAdapter(mContext, list)
        recyclerView_tody.layoutManager = linearLayoutManager
        recyclerView_tody.setHasFixedSize(true)
        recyclerView_tody.isNestedScrollingEnabled = false
        homeToDayAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(
                view: View?,
                holder: RecyclerView.ViewHolder?,
                position: Int
            ): Boolean {
                return false
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtils.showToast("今日特惠")
            }
        })

        recyclerView_tody.adapter = homeToDayAdapter

    }


    /**
     *加载请求数据
     * @param type  1==检查版本  0==不升级
     */
    private fun loadBanner_Newarrivals_Brand(homeInfoBean: HomeInfoBean, i: Int) {

        if (homeInfoBean.banners.isNotEmpty()) {
            item_home_banner.visibility = View.VISIBLE
            initBanner(homeInfoBean.banners)
        } else {
            item_home_banner.visibility = View.GONE
        }

        if (homeInfoBean.newarrivals.isNotEmpty()) {
            rl_new.visibility = View.VISIBLE
            recyclerView_new.visibility = View.VISIBLE
            initHomeNew(homeInfoBean.newarrivals)
        } else {
            rl_new.visibility = View.GONE
            recyclerView_new.visibility = View.GONE
        }

        if (homeInfoBean.outSoonArrivals.isNotEmpty()) {
            rl_new_pro.visibility = View.VISIBLE
            recyclerView_new_pro.visibility = View.VISIBLE

            initHomeNewPro(homeInfoBean.outSoonArrivals)
        } else {
            rl_new_pro.visibility = View.GONE
            recyclerView_new_pro.visibility = View.GONE
        }

        if (homeInfoBean.activityInfos.isNotEmpty()) {
            recyclerView_activity.visibility = View.VISIBLE
            initActivity(homeInfoBean.activityInfos)
        } else {
            recyclerView_activity.visibility = View.GONE
        }
        if (homeInfoBean.brands.isNotEmpty()) {
            rl_band.visibility = View.VISIBLE
            recyclerView_band.visibility = View.VISIBLE
            initBrands(homeInfoBean.brands)
        } else {
            rl_band.visibility = View.GONE
            recyclerView_band.visibility = View.GONE
        }


    }

    private fun initBrands(brands: List<BrandsBean?>) {
        val homeBandAdapter = HomeBandAdapter(mContext, brands)
        recyclerView_band.layoutManager =
            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        homeBandAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(
                view: View?,
                holder: RecyclerView.ViewHolder?,
                position: Int
            ): Boolean {
                return false
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtils.showToast("品牌")
            }
        })
        val headerAndFooterWrapper = HeaderAndFooterWrapper<RecyclerView>(homeBandAdapter)
        val layout = layoutInflater.inflate(R.layout.item_empty_footer, recyclerView_band, false)
        headerAndFooterWrapper.addFootView(layout)
        recyclerView_band.adapter = headerAndFooterWrapper
    }

    private fun initHomeNewPro(outSoonArrivals: List<NewarrivalsBean?>) {

    }

    private fun initActivity(activityBean: List<BrandsBean?>) {
        val homeActivityAdapter = HomeActivityAdapter(mContext, activityBean)
        recyclerView_activity.layoutManager =
            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        homeActivityAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(
                view: View?,
                holder: RecyclerView.ViewHolder?,
                position: Int
            ): Boolean {
                return false
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtils.showToast("vsnfvslkdm")
            }
        })
        val headerAndFooterWrapper = HeaderAndFooterWrapper<RecyclerView>(homeActivityAdapter)
        val layout =
            layoutInflater.inflate(R.layout.item_empty_footer, recyclerView_activity, false)
        headerAndFooterWrapper.addFootView(layout)
        recyclerView_activity.adapter = headerAndFooterWrapper

    }

    private fun initHomeNew(newarrivals: List<NewarrivalsBean?>) {
        val homeIconAdapter = HomeNewAdapter(mContext, newarrivals)
        recyclerView_new.layoutManager =
            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        homeIconAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(
                view: View?,
                holder: RecyclerView.ViewHolder?,
                position: Int
            ): Boolean {
                return false
            }

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                ToastUtils.showToast("vslkjskjdvnbk")
            }
        })
        recyclerView_new.adapter = homeIconAdapter

    }

    private fun initBanner(bannersList: List<BannersBean?>) {
        item_home_banner.setAutoPlayAble(bannersList.size > 1)
        item_home_banner.setData(bannersList, null)
        item_home_banner.setAdapter(BGABanner.Adapter<ImageView?, BannersBean?> { banner: BGABanner?, itemView: ImageView?, model: BannersBean?, position: Int ->
            ImageLoader.getInstance()
                ?.loadingImage(mContext, model?.imgUrl, itemView, null, R.drawable.default_banner)
        })
    }

    override fun getRefreshDate(stat: Int, page: Int, count: Int) {
        mPresenter?.queryHomeInfo("31.173321", "121.369679", "上海")
    }

}