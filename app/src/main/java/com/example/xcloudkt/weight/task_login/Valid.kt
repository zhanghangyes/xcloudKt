package com.example.xcloudkt.weight.task_login

/**
 * Created by jinyabo on 8/12/2017.
 */
interface Valid {
    /**
     * 是否满足检验器的要求，如果不满足的话，则执行doAction方法。如果满足，则执行目标action
     * @return
     */
    fun check(): String?
    fun doValid(type: Int) //判断是从什么地方进入的
}