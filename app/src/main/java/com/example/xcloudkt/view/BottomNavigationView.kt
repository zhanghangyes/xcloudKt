package com.example.xcloudkt.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.xcloudkt.MainActivity
import com.example.xcloudkt.R
import com.example.xcloudkt.bean.icon.IconBean
import com.example.xcloudkt.bean.icon.NavigatorsBean
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.constant.IntentParams
import com.example.xcloudkt.sensors.SensorsCommontUtil.enterScanPay
import com.example.xcloudkt.sensors.SensorsCommontUtil.tabClick
import com.example.xcloudkt.ui.fragment.HomeFragment
import com.example.xcloudkt.util.*
import com.example.xcloudkt.util.StringUtils.isNotEmpty
import com.example.xcloudkt.util.glide.GlideRoundTransform
import com.example.xcloudkt.util.glide.ImageLoader
import com.example.xcloudkt.util.sp.SpUtils
import com.example.xcloudkt.weight.task_login.Action
import com.example.xcloudkt.weight.task_login.LoginValid
import com.example.xcloudkt.weight.task_login.SingleCall
import kotlinx.android.synthetic.main.main_bottom_navigation_layout.view.*

class BottomNavigationView : FrameLayout, View.OnClickListener,
    Action {
    private lateinit var mTabs: Array<RelativeLayout?>
    private var mTabsText: Array<TextView?> = arrayOf()
    lateinit var fragments: Array<Fragment>
        private set
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null
    private var tv5: TextView? = null
    private var tabBtnMessage: RelativeLayout? = null
    private var tabBtnMail: RelativeLayout? = null
    private var tabBtnWork: RelativeLayout? = null
    private var tabBtnMine: RelativeLayout? = null
    private var tabBtnIm: RelativeLayout? = null
    private var mFragmentActivity: FragmentActivity? = null
    private var frameLayout = 0
    var index = 0
        private set
    private var position = 0
    private var currentTabIndex = 0
    private var mImageView: ImageView? = null
    private var mIconList: List<IconBean>? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.main_bottom_navigation_layout, this, true)
        tv1 = root.findViewById(R.id.tv1)
        tv2 = root.findViewById(R.id.tv2)
        tv3 = root.findViewById(R.id.tab_btn_work)
        tv4 = root.findViewById(R.id.tv4)
        tv5 = root.findViewById(R.id.tv5)
        tabBtnMessage = root.findViewById(R.id.tab_btn_main)
        tab_btn_main.setOnClickListener(this)
        tabBtnMail = root.findViewById(R.id.tab_btn_member)
        tab_btn_member.setOnClickListener(this)
        tabBtnWork = root.findViewById(R.id.star_rl)
        star_rl.setOnClickListener(this)
        tabBtnMine = root.findViewById(R.id.tab_btn_mine)
        tab_btn_mine.setOnClickListener(this)
        tabBtnIm = root.findViewById(R.id.tab_btn_im)
        tab_btn_im.setOnClickListener(this)
        root.findViewById<View>(R.id.star_rl).setOnClickListener(this)
        mImageView = root.findViewById(R.id.star_im)
    }

    fun initView(activity: FragmentActivity, frameLayout: Int, vararg fragmentArray: Fragment) {
        mFragmentActivity = activity
        this.frameLayout = frameLayout
        mTabs = arrayOfNulls(5)
        mTabsText = arrayOfNulls(5)
        mTabs[0] = tabBtnMessage
        mTabs[2] = tabBtnWork
        mTabs[1] = tabBtnMail
        mTabs[3] = tabBtnIm
        mTabs[4] = tabBtnMine
        mTabsText[0] = tv1
        mTabsText[1] = tv2
        mTabsText[2] = tv3
        mTabsText[3] = tv4
        mTabsText[4] = tv5
        mTabs[0]?.isSelected = true
        tabClick("首页")
        fragments = arrayOf(
            fragmentArray[0],
            fragmentArray[1],
            fragmentArray[2],
            fragmentArray[3],
            fragmentArray[4]
        )
        activity.supportFragmentManager.beginTransaction().add(frameLayout, fragmentArray[0])
            .show(fragmentArray[0]).commitAllowingStateLoss()
        loadNavigators()
    }

    override fun onClick(view: View) {
        if (DoubleClickUtil.isFastDoubleClick2) {
            return
        }
        val i = view.id
        if (i == R.id.tab_btn_main) {
            tabClick(mIconList?.get(0)?.name ?: "首页")
            index = 0
        } else if (i == R.id.tab_btn_member) {
            tabClick(if (null != mIconList) mIconList!![1].name else "领券")
            index = 1
        } else if (i == R.id.star_rl) {
            tabClick(if (null != mIconList) mIconList!![2].name else "会员码")
            enterScanPay("首页", if (null != mIconList) mIconList!![2].name else "会员码")
            index = 2
        } else if (i == R.id.tab_btn_im) {
            tabClick(if (null != mIconList) mIconList!![3].name else "积分换购")
            index = 3
        } else if (i == R.id.tab_btn_mine) {
            tabClick(if (null != mIconList) mIconList!![4].name else "我的")
            index = 4
        }
        disPlay(index)
    }

    fun disPlay(index: Int) {
        this.index = index
        if (null != mIconList) {
            //-------------------------------------------------------底部可配置处理--------------------------------------------------------
            when (mIconList!![index].jumpTypeCd) {
                Constant.LOCAL_OPEN, Constant.LOCAL_OPEN_NO_TOKEN -> if (index != 0) {
                    if (mIconList!![index].iconType == 2 || mIconList!![index].jumpPageValue == Constant.MEMBER_PAGE) {
                        SingleCall.instance
                            .addAction(this)
                            .addValid(LoginValid(context))
                            .doCall(IntentParams.HOME_MEMBER)
                    } else {
                        SingleCall.instance
                            .addAction(this)
                            .addValid(LoginValid(context))
                            .doCall(Constant.LOGIN_TYPE_DEF)
                    }
                } else {
                    disPlays(index) //首页跟个人中心
                }
                Constant.WEB_OPEN -> {
                    position = index
                    SingleCall.instance
                        .addAction(this)
                        .addValid(LoginValid(context))
                        .doCall(IntentParams.HOME_NAVIGATION_TYPE_WEB)
                }
                Constant.WEB_OPEN_NO_TOKEN -> {
                    position = index
                    call(IntentParams.HOME_NAVIGATION_TYPE_WEB)
                }
                else -> {
                }
            }
            return
        }
        //-------------------------------------------------------默认框架逻辑处理--------------------------------------------------------
        if (index != 0) {
            if (index == 1) {
//                AppActivityUtils.openWebViewActivity(
//                    mFragmentActivity,
//                    "",
//                    ApiConstants.COUPONS_FLEX
//                )
            } else {
                SingleCall.instance
                    .addAction(this)
                    .addValid(LoginValid(context))
                    .doCall(Constant.LOGIN_TYPE_DEF)
            }
        } else {
            disPlays(index)
        }
    }

    fun disPlays(index: Int) {
        if (currentTabIndex != index) {
            val fragmentTransaction = mFragmentActivity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.hide(fragments[currentTabIndex])
            if (!fragments[index].isAdded) {
                fragmentTransaction?.add(frameLayout, fragments[index])
            }
            fragmentTransaction?.show(fragments[index])?.commitAllowingStateLoss()
            mTabs[index]?.let { Anim.anim(it) }
        }
        mTabs[currentTabIndex]?.isSelected = false
        mTabs[index]?.isSelected = true
        currentTabIndex = index
    }

    /**
     * 底部导航
     */
    private fun loadNavigators() {
        val navigators = SpUtils.getNavigators()
        if (null == navigators || navigators.defaultNavigatorGroup === 1 || null == navigators.navigatorLists || navigators.navigatorLists.isEmpty()
        ) {
            return
        }
        mIconList = navigators.navigatorLists
        val drawable = resources.getDrawable(R.drawable.tab_home) //原始图片获取适配的大小
        val minimumHeight = drawable.minimumHeight
        val minimumWidth = drawable.minimumWidth
        for (i in mTabsText.indices) {
            if (mIconList?.get(i)?.iconType == 2) {
                ImageLoader.getInstance()?.loadingImage(
                    mFragmentActivity,
                    mIconList?.get(i)?.iconNormal,
                    mImageView,
                    MultiTransformation(FitCenter(), GlideRoundTransform(mFragmentActivity, 0)),
                    R.drawable.home_btn_zxing
                )
            } else {
                ThreadManager.instance?.runOnWorkThread {
                    val checkPath: String = TextSelectUtil.getPath(
                        mFragmentActivity,
                        if (mIconList?.get(i)?.iconType == 2) mIconList?.get(i)?.iconNormal else mIconList?.get(
                            i
                        )?.iconLight
                    )
                    val unCheckPath: String =
                        TextSelectUtil.getPath(mFragmentActivity, mIconList?.get(i)?.iconNormal)
                    val topSelector: StateListDrawable =
                        TextSelectUtil.flushDrawableSelector(
                            if (isNotEmpty(checkPath)) TextSelectUtil.getDrawableFromPath(
                                checkPath
                            ) else resources.getDrawable(R.drawable.default_new),
                            if (isNotEmpty(unCheckPath)) TextSelectUtil.getDrawableFromPath(
                                unCheckPath
                            ) else resources.getDrawable(R.drawable.default_new)
                        )
                    topSelector.setBounds(0, 0, minimumWidth, minimumHeight)
                    ThreadManager.instance?.runOnUIThread {
                        mTabsText[i]?.setCompoundDrawables(null, topSelector, null, null)
                    }
                }
            }
            mTabsText[i]?.setTextColor(
                TextSelectUtil.flushColorSelector(
                    Color.parseColor(mIconList?.get(i)?.textColorLight),
                    Color.parseColor(mIconList?.get(i)?.textColorNormal)
                )
            )
            mTabsText[i]?.text = mIconList?.get(i)?.name
        }
    }

    override fun call(type: Int) {
        //---------------------------------------------------------底部可配跳转---------------------------------------------------------------
        if (null != mIconList) {
            when (type) {
//                IntentParams.HOME_MEMBER -> AppActivityUtils.openMemberActivity(mFragmentActivity as FragmentActivity)
//                IntentParams.HOME_NAVIGATION_TYPE -> AppActivityUtils.openLocalActivity(
//                    mFragmentActivity,
//                    mIconList!![position].jumpPageValue,
//                    ""
//                )
                IntentParams.HOME_NAVIGATION_TYPE_WEB -> {
                    var activityType = "-1"
                    if (mIconList!![position].jumpLinkUrl?.contains(Constant.ACTIVITYTYPE)!!) {
                        activityType = ViewH5Substring.getValueByName(
                            mIconList!![position].jumpLinkUrl.toString(),
                            Constant.ACTIVITYTYPE
                        )
                    }
//                    AppActivityUtils.openWebViewActivity(
//                        context,
//                        if (Constant.HOME_COUPON == activityType) "" else mIconList!![position].name,
//                        ActivityUtils.getADUrl(
//                            mIconList!![position].jumpLinkUrl.toString(),
//                            mIconList!![position].jointParams,
//                            if (null != SpUtils.getUserInfo()) SpUtils.getUserInfo()?.memberCode.toString() else "",
//                            "LocationUtils.instance.getLatitude()",
//                            "LocationUtils.instance.getLongitude()",
//                            mIconList!![position].menuValue.toString(),
//                            ""
//                        )
//                    )
                }
                else -> disPlays(index)
            }
            return
        }
        when (index) {
//            1 -> AppActivityUtils.openWebViewActivity(
//                mFragmentActivity,
//                "",
//                ApiConstants.COUPONS_FLEX
//            )
//            2 -> AppActivityUtils.openMemberActivity(mFragmentActivity)
//            3 -> if (isNotEmpty(ApiConstants.INTEGRAL)) {
//                val str = ApiConstants.INTEGRAL.substring(
//                    0,
//                    ApiConstants.INTEGRAL.lastIndexOf("&")
//                ) //截取最后一个&符号
//                ApiConstants.INTEGRAL =
//                    "$str&memberId=" + if (null != SpUtils.getUserInfo()) SpUtils.getUserInfo()?.memberCode else ""
//                AppActivityUtils.openWebViewActivity(mFragmentActivity, "", ApiConstants.INTEGRAL)
//            } else {
//                showToast(resources.getString(R.string.web_url_null))
//            }
            else -> disPlays(index)
        }
    }
}