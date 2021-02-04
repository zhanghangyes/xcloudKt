package com.example.xcloudkt.util.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.ui.activity.login.LoginActivity
import com.example.xcloudkt.util.sp.SpUtils

/**
 * <pre>
 * author : zhanghang
 * date : 2020/5/1 13:59
 * desc  : Activity相关工具类
 */
open class ActivityUtils {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {
        /**
         * 启动Activity
         *
         * @param context
         * @param clz
         * @param bundle
         */
        fun startActivity(context: Context, clz: Class<*>, bundle: Bundle? = null) {
            val intent = Intent(context, clz)
            if (null != bundle) intent.putExtras(bundle)
            context.startActivity(intent)
        }

        /**
         * 启动Activity
         *
         * @param context
         * @param clz
         */
        fun startActivity(context: Context, clz: Class<*>) {
            startActivity(context, clz, null)
        }

        /**
         * 启动SingleTop Activity
         */
        fun startActivitySingleTop(context: Context, clz: Class<*>) {
            val intent = Intent(context, clz)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        /**
         * 启动Activity
         */
        fun startActivityForResult(
            activity: Activity,
            clz: Class<*>,
            bundle: Bundle?,
            requestCode: Int
        ) {
            val intent = Intent(activity, clz)
            if (null != bundle) intent.putExtras(bundle)
            activity.startActivityForResult(intent, requestCode)
        }

        /**
         * 启动Activity
         */
        fun startActivityForResult(activity: Activity, clz: Class<*>, requestCode: Int) {
            startActivityForResult(activity, clz, null, requestCode)
        }

        /**
         * 跳转login页面,关闭所有打开activity
         */
        fun startLoginTaskActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        /**
         * 回到桌面
         */
        fun startHomeActivity(context: Context) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            context.startActivity(homeIntent)
        }

        /**
         * 广告跳转  "jointParams":[1,2,3,4], //拼接参数  1.memverId 2.经纬度 3.城市 4.扩展(menu) 5,productid, 6 storecode
         */
        open fun getADUrl(
            url: String,
            jointParams: List<Int?>?,
            memberId: String,
            lat: String,
            lon: String,
            menuValue: String,
            productid: String
        ): String? {
            return if (null != jointParams && jointParams.size > 0) {
                val buffer = StringBuffer()
                if (!url.contains("?")) {
                    buffer.append("?")
                } else if (url.contains("?")) {
                    if (!url.endsWith("?")) buffer.append("&")
                }
                if (jointParams.contains(2)) {
                    buffer.append("latitude=$lat&longitude=$lon&")
                }
                if (jointParams.contains(3)) {
                    buffer.append(
                        "city=" + SpUtils.getString(Constant.ADDRESS, Constant.DEFULT_LOCATION)
                            .toString() + "&"
                    )
                }
                if (jointParams.contains(4)) {
                    buffer.append("menu=$menuValue&")
                }
                if (jointParams.contains(5)) {
                    buffer.append("productid=$productid&")
                }
                if (jointParams.contains(6)) {
                    buffer.append(
                        "storecode=" + SpUtils.getString(
                            Constant.STORECODE,
                            ""
                        ).toString() + "&"
                    )
                }
                if (jointParams.contains(7)) {
                    buffer.append(
                        "citycode=" + SpUtils.getString(
                            Constant.CITYCODE,
                            ""
                        ).toString() + "&"
                    )
                }
                if (jointParams.contains(1)) {
                    buffer.append("memberId=$memberId")
                }
                val last = buffer.toString()
                if ("?" == last) url else url + if (last.endsWith("&")) last.substring(
                    0,
                    last.length - 1
                ) else last
            } else {
                url
            }
        }
    }


}