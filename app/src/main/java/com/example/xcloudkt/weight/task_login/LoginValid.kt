package com.example.xcloudkt.weight.task_login

import android.app.Activity
import android.content.Context
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.ui.activity.login.LoginActivity
import com.example.xcloudkt.util.sp.SpUtils

/**
 * author : zhanghang
 * date : 2020/6/2 19:23
 */
class LoginValid(private val context: Context) : Valid {

    /**
     * check whether it login in or not
     *
     * @return
     */
    override fun check(): String {
        return SpUtils.getString(Constant.APP_AUTH_TOKEN, "").toString()
    }

    /**
     * if check() return false. then doValid was called
     */
    override fun doValid(type: Int) {
        LoginActivity.start(context as Activity, 0, type)
    }

}