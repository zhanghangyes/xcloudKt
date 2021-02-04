package com.example.xcloudkt.weight.task_login

/**
 * Created by jinyabo on 8/12/2017.
 */
interface Action {
    fun call(type: Int) //强制登陆,根据类型判断是否登录
}