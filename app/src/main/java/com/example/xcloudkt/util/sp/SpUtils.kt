package com.example.xcloudkt.util.sp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.xcloudkt.app.MyApp
import com.example.xcloudkt.bean.icon.NavigatorsBean
import com.example.xcloudkt.bean.user.UserInfoBean

object SpUtils {
    const val SP_NAME = "xcloud_data"

    val appSp: SharedPreferences
        get() = MyApp.mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        appSp.edit().putString(key, value).apply()
    }

    fun getString(key: String, defValue: String): String? {
        return appSp.getString(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        appSp.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, value: Int): Int {
        return appSp.getInt(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        appSp.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return appSp.getBoolean(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        appSp.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        return appSp.getLong(key, defValue)
    }

    fun remove(key: String) {
        appSp.edit().remove(key).apply()
    }

    /**
     * 移除所有数据
     */
    fun removeAll() {
        val sp = MyApp.mContext.getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE)
        sp.edit().clear().apply()
    }

    //=====================================================底部导航数据=============================================
    const val NAVIGATORS = "navigators"
    val navigatorsSp: SharedPreferences
        get() = MyApp.mContext.getSharedPreferences(NAVIGATORS, Context.MODE_PRIVATE)

    /**
     * 存底部导航数据
     * @param json
     */
    fun setNavigators(json: String?) {
        navigatorsSp.edit().putString(NAVIGATORS, json).apply()
    }

    /**
     * 拿底部导航数据
     */
    fun getNavigators(): NavigatorsBean.Data? {
        return JsonUtils.instance?.fromJson(
            navigatorsSp.getString(NAVIGATORS, ""), NavigatorsBean.Data::class.java
        )
    }

    /**
     * 清除底部导航，金刚区数据
     */
    fun cleanNavigators() {
        navigatorsSp.edit().remove(NAVIGATORS).apply()
    }

    //=====================================================用户bean数据=============================================
    const val USER_INFO_KEY = "user_info_key"
    val userSp: SharedPreferences
        get() = MyApp.mContext.getSharedPreferences(USER_INFO_KEY, Context.MODE_PRIVATE)

    fun setUserInfo(json: String?) {
        userSp.edit().putString(USER_INFO_KEY, json).apply()
    }

    fun getUserInfo(): UserInfoBean? {
        return JsonUtils.instance?.fromJson(
            userSp.getString(USER_INFO_KEY, ""),
            UserInfoBean::class.java
        )
    }

    fun cleanUserInfo() {
        userSp.edit().remove(USER_INFO_KEY).apply()
    }

}
