package com.example.xcloudkt.weight.task_login

import com.example.xcloudkt.util.StringUtils
import com.example.xcloudkt.util.StringUtils.isNotEmpty
import java.util.*

/**
 * 一个执行单元。
 * 包括一个执行目标体和一个检验队列。检验队列用来保证所有的前置条件。当所有的前置条件都通过后，才能进行执行单元。
 */
class CallUnit {
    //目标行为
    var action: Action? = null

    //先进先出验证模型
    val validQueue: Queue<Valid> = ArrayDeque()

    //上一个执行的valid
    var lastValid: Valid? = null

    constructor() {}
    constructor(action: Action?) {
        this.action = action
    }

    fun addValid(valid: Valid): CallUnit {
        validQueue.add(valid)
        return this
    }

    //检查valid.如果已经满足要求，则移出来队列
    fun check() {
        for (valid in validQueue) {
            if (StringUtils.isNotEmpty(valid.check())) {
                validQueue.remove(valid)
            }
        }
    }

    /**
     * start
     */
    fun doCall() {}

    companion object {
        fun newInstance(action: Action?): CallUnit {
            return CallUnit(action)
        }
    }
}