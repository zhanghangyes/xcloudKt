package com.example.xcloudkt.app

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex
import com.example.xcloudkt.R
import com.example.xcloudkt.service.DownloadIconService
import com.example.xcloudkt.view.ClassicsRefeshHeaderView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import kotlin.properties.Delegates

/**
 *Application
 */
class MyApp : Application() {

    companion object {
        var mContext: Context by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        initConfig()
    }

    private fun initConfig() {
        startService(Intent(mContext, DownloadIconService::class.java))

    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return@setDefaultRefreshHeaderCreator ClassicsRefeshHeaderView(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(
                context
            )
        }
    }

    /**
     * 分包
     *
     * @param base
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}