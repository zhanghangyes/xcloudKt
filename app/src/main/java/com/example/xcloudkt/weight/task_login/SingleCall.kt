package com.example.xcloudkt.weight.task_login

import com.example.xcloudkt.util.StringUtils.isEmpty
import com.example.xcloudkt.util.StringUtils.isNotEmpty

/**
 * 如果CallUnit验证模型中没有嵌套的验证模型，则可以直接使用SingleCall即可
 */
class SingleCall {
    var callUnit = CallUnit()
    fun addAction(action: Action?): SingleCall {
        clear()
        callUnit.action = action
        return this
    }

    fun addValid(valid: Valid): SingleCall {
        //只添加无效的，验证不通过的。
        if (isNotEmpty(valid.check())) {
            return this
        }
        callUnit.addValid(valid)
        return this
    }

    fun doCall(type: Int) {
        //如果上一条valid难没有通过，是不允许再发起call的
        if (callUnit.lastValid != null && isEmpty(callUnit.lastValid!!.check())) {
            return
        }
        //执行action
        if (callUnit.validQueue.size == 0 && callUnit.action != null) {
            callUnit.action!!.call(type) //登录后回到目标页
            //清空
            clear()
        } else {
            //执行验证。
            val valid = callUnit.validQueue.poll()
            if (valid != null) {
                callUnit.lastValid = valid
                valid.doValid(type) //拦截器，跳转到登录
            }
        }
    }

    fun clear() {
        callUnit.validQueue.clear()
        callUnit.action = null
        callUnit.lastValid = null
    }

    // 静态内部类，第一次加载Singleton类时不会初始化mInstance，
    // 当调用getInstance()时才会初始化
    private object SingletonHolder {
        val mInstance = SingleCall()
    }

    companion object {
        // 单一全局访问点
        val instance: SingleCall
            get() = SingletonHolder.mInstance
    }
}