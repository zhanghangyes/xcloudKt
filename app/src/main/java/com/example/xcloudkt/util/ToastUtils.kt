package com.example.xcloudkt.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.IdRes
import com.example.xcloudkt.app.MyApp

object ToastUtils {

    //主线程
    private val handler = Handler(Looper.getMainLooper())
    private var mToast: Toast? = null

    private object SingleHolder {
        internal val instance: ToastUtils = ToastUtils
    }

    val instance: ToastUtils
        get() = SingleHolder.instance

    private fun cancel() {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
        }
    }

    fun showToast(message: String) {
        showToastDefault(message)
    }

    @SuppressLint("ResourceType")
    fun showToast(@IdRes messageId: Int) {
        val context: Context = MyApp.mContext ?: return
        showToastDefault(context.getString(messageId))
    }

    @SuppressLint("ResourceType")
    fun showToast(@IdRes messageId: Int, gravity: Int) {
        val context: Context = MyApp.mContext ?: return
        showToastByPosition(context.getString(messageId), gravity)
    }

    private fun showToastDefault(message: String) {
        val context: Context = MyApp.mContext ?: return
        if (TextUtils.isEmpty(message)) {
            return
        }
        handler.post {
            cancel()
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            mToast?.show()
        }
    }

    fun showToastLong(message: String) {
        val context: Context = MyApp.mContext ?: return
        if (TextUtils.isEmpty(message)) {
            return
        }
        handler.post {
            cancel()
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            mToast?.show()
        }
    }

    fun showToastByPosition(message: String?, gravity: Int) {
        val context: Context = MyApp.mContext ?: return
        if (TextUtils.isEmpty(message)) {
            return
        }
        handler.post {
            cancel()
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            mToast?.setGravity(gravity, 0, 0)
            mToast?.show()
        }
    }


}