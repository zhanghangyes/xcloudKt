package com.example.xcloudkt.util.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.xcloudkt.ui.activity.login.LoginActivity

import java.io.Serializable

/**
 * <pre>
 * author : zhanghang
 * date : 2020/5/7 13:59
 * desc  : Activity跳转相关工具类
</pre> *
 */
object AppActivityUtils : ActivityUtils() {
    /**
     * 跳转login页面,关闭所有打开activity
     */
    fun startLoginTaskActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /**
     * 跳转切换门店界面
     */
//    fun openAddressActivity(
//        context: Context?, storeCode: String?,
//        CityName: String?, AreaName: String?, StoreName: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putString("storeCode", storeCode)
//        bundle.putString("CityName", CityName)
//        bundle.putString("AreaName", AreaName)
//        bundle.putString("StoreName", StoreName)
//        startActivity(context!!, AddressActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转绑定手机号界面
//     */
//    fun openBandPhoneActivity(
//        context: Context?,
//        mOpenid: String?,
//        type: Int,
//        unionid: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putString("mOpenid", mOpenid)
//        bundle.putString("mUnionid", unionid)
//        bundle.putInt("login_type", type)
//        startActivity(context!!, BandPhoneActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转修改头像界面
//     */
//    fun openImageHeadViewActivity(
//        context: Context?,
//        ImageHeadView: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putString("ImageHeadView", ImageHeadView)
//        startActivity(context!!, ImageHeadViewActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转消息详情界面
//     */
//    fun openMessageDetailActivity(
//        context: Context?,
//        msgListBean: MsgListBean.ListBean?
//    ) {
//        val bundle = Bundle()
//        bundle.putSerializable("MsgListBean", msgListBean)
//        startActivity(context!!, MessageDetailActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转新品上市详情界面
//     */
//    fun openActivityNewDetail(
//        context: Context?,
//        newarrivalsBean: List<NewarrivalsBean?>?,
//        position: Int,
//        url: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putSerializable("NewarrivalsBean", newarrivalsBean as Serializable?)
//        bundle.putInt("position", position)
//        bundle.putString("url", url)
//        startActivity(context!!, ActivityNewDetail::class.java, bundle)
//    }
//
//    /**
//     * 跳转使用会员码页面
//     */
//    fun openCardUseActivity(
//        context: Context?,
//        couponId: String?,
//        CouponName: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putString("couponId", couponId)
//        bundle.putString("CouponName", CouponName)
//        startActivity(context!!, CardUseActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转卡券包详情界面
//     */
//    fun openCardDetailsActivity(
//        context: Context?,
//        storeCode: CardcouponBean.ListBean?
//    ) {
//        val bundle = Bundle()
//        bundle.putSerializable("bean", storeCode)
//        startActivity(context!!, CardDetailsActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转新品预先知详情界面
//     */
//    fun openNewProDetailsActivity(
//        context: Context?,
//        newArrivalsPeriodBeans: List<NewArrivalsPeriodBean?>?,
//        productCode: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putSerializable("bean", newArrivalsPeriodBeans as Serializable?)
//        bundle.putString("productCode", productCode)
//        startActivity(context!!, NewProDetailsActivity::class.java, bundle)
//    }
//
//    /**
//     * 会员吗跳转
//     */
//    fun openMemberActivity(mFragmentActivity: FragmentActivity?) {
//        val intent = Intent(mFragmentActivity, MemberActivity::class.java)
//        mFragmentActivity.startActivity(intent)
//        mFragmentActivity.overridePendingTransition(R.anim.scale_in, 0)
//    }
//
//    /**
//     * 打電話
//     */
//    fun openCallMineActivity(context: Context) {
//        val intent = Intent(Intent.ACTION_DIAL)
//        intent.data = Uri.parse("tel:" + "4008-200-996")
//        context.startActivity(intent)
//    }
//
//    /**
//     * 跳转今日特惠界面
//     */
//    fun openSpecialActivity(
//        context: Context?,
//        categoryCode: Array<String?>?,
//        mStoreCode: String?,
//        city: String?,
//        groupFlag: String?
//        ,
//        promotionNo: String?,
//        pos: String?
//    ) {
//        val bundle = Bundle()
//        bundle.putStringArray("categoryCode", categoryCode)
//        bundle.putString("storeCode", mStoreCode)
//        bundle.putString("city", city)
//        bundle.putString("groupFlag", groupFlag)
//        bundle.putString("promotionNo", promotionNo)
//        bundle.putString("position", pos)
//        bundle.putString("time", TimeUtil.getTimeStame())
//        startActivity(context!!, SpecialActivity::class.java, bundle)
//    }
//    /**
//     * 跳转前需要登陆
//     */
//    /**
//     * 跳转前需要登陆
//     */
//    @JvmOverloads
//    fun startActivityByLogin(
//        context: Context?,
//        clz: Class<*>,
//        bundle: Bundle? = null
//    ) {
//        if (SPUtils.hasUserInfo(context)) {
//            startActivity(context!!, clz, bundle)
//        } else {
//            startActivity(context!!, LoginActivity::class.java)
//        }
//    }
//
//    fun startActivityByInfo(
//        context: Context?,
//        clz: Class<*>,
//        bundle: Bundle?
//    ) {
//        startActivity(context!!, clz, bundle)
//    }
//
//    fun startForResultActivity(
//        context: Context?,
//        clz: Class<*>,
//        bundle: Bundle?
//    ) {
//        startActivity(context!!, clz, bundle)
//    }
//    /**
//     * 跳转web页面
//     *
//     * @param context
//     * @param title
//     * @param url
//     * @param description 附加参数
//     */
//    /**
//     * 跳转web页面
//     *
//     * @param context
//     * @param title
//     * @param url
//     */
//    @JvmOverloads
//    fun openWebViewActivity(
//        context: Context?,
//        title: String?,
//        url: String?,
//        description: String? = ""
//    ) {
//        val bundle = Bundle()
//        bundle.putString("title", title)
//        bundle.putString("url", url)
//        bundle.putString("description", description)
//        startActivity(context!!, WebActivity::class.java, bundle)
//    }
//
//    /**
//     * 跳转本地界面
//     */
//    fun openLocalActivity(
//        context: FragmentActivity?,
//        type: Int,
//        storeCode: String?
//    ) {
//        val bundle = Bundle()
//        when (type) {
//            GRAIN_MALL_PAGE -> startActivity(context, GrainExchangeActivity::class.java)
//            SIGN_IN_PAGE -> startActivity(context, SignActivity::class.java)
//            MINE_GRAIN_PAGE -> startActivity(context, MineGrainActivity::class.java)
//            NEW_PRODUCT_PAGE -> startActivity(context, ActivityNewDetail::class.java)
//            BRAND_ACTIVITY -> com.x2era.xcloud.app.utils.AppActivityUtils.openWebViewActivity(
//                context,
//                context.resources.getString(R.string.detail),
//                ActivityUtils.getContentHtmlUrl(
//                    ApiConstants.CONTENT,
//                    SPUtils.get(context, Constant.APP_AUTH_TOKEN, "") as String
//                )
//            )
//            DISCOUNT_TODAY_LIST_PAGE -> {
//                bundle.putString("storeCode", storeCode)
//                bundle.putString(
//                    "city",
//                    SPUtils.get(
//                        context,
//                        Constant.ADDRESS,
//                        Constant.DEFULT_LOCATION
//                    ) as String
//                )
//                startActivity(context, SpecialActivity::class.java, bundle)
//            }
//            PERSON_CENTER_PAGE -> {
//                val intent = Intent(context, MainActivity::class.java)
//                intent.putExtra(Constant.HOME_MAIN, 4)
//                context.startActivity(intent)
//            }
//            COUPONS_PACKAGE_LIST_PAGE -> startActivity(context, CardActivity::class.java)
//            GRAIN_DESCRIPTION -> startActivity(context, GrainInstructionActivity::class.java)
//            BILL_LIST_PAGE -> startActivity(context, BillActivity::class.java)
//            MESSAGE_CENTER_LIST -> startActivity(context, HomeMessageActivity::class.java)
//            MEMBER_INFO_PAGE -> {
//            }
//            MEMBER_RIGHTS_PAGE -> {
//            }
//            ABOUT_WE_PAGE -> startActivity(context, AboutMineActivity::class.java)
//            SYSTEM_SETTING_PAGE -> startActivity(context, SystemSettingActivity::class.java)
//            else -> {
//            }
//        }
//    }
}