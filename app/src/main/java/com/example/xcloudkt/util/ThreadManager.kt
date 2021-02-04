package com.example.xcloudkt.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ThreadManager private constructor() {
    fun init(context: Context?) {}
    fun runOnWorkThread(runnable: () -> Unit) {
        mWorkThreadExecutor.execute(runnable)
    }

    fun runOnUIThread(runnable: () -> Unit) {
        mMainThreadHandler.post(runnable!!)
    }

    val isInMainThread: Boolean
        get() = Looper.getMainLooper().thread === Thread.currentThread()

    companion object {
        private const val TAG = "ThreadManager"
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        private val CORE_POOL_SIZE =
            Math.max(2, Math.min(CPU_COUNT - 1, 4))

        @Volatile
        private var sInstance: ThreadManager? = null
        private val mWorkThreadExecutor: Executor =
            Executors.newFixedThreadPool(CORE_POOL_SIZE)
        private lateinit var mMainThreadHandler: Handler
        val instance: ThreadManager?
            get() {
                if (sInstance == null) {
                    synchronized(ThreadManager::class.java) {
                        if (sInstance == null) {
                            sInstance = ThreadManager()
                        }
                    }
                }
                return sInstance
            }
    }

    init {
        mMainThreadHandler = Handler(Looper.getMainLooper())
    }
}