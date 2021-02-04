package com.example.xcloudkt.weight.nice_dialog

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes

class NiceDialog : BaseNiceDialog() {
    private var convertListener: ViewConvertListener? = null
    override fun initTheme(): Int {
        return theme
    }

    override fun intLayoutId(): Int {
        return layoutId
    }

    override fun convertView(holder: ViewHolder?, dialog: BaseNiceDialog?) {
        if (convertListener != null) {
            convertListener?.convertView(holder, dialog)
        }
    }

    fun setTheme(@StyleRes theme: Int): NiceDialog {
        this.theme = theme
        return this
    }

    fun setLayoutId(@LayoutRes layoutId: Int): NiceDialog {
        this.layoutId = layoutId
        return this
    }

    fun setConvertListener(convertListener: ViewConvertListener?): NiceDialog {
        this.convertListener = convertListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable("listener")
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("listener", convertListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        convertListener = null
    }

    companion object {
        fun init(): NiceDialog {
            return NiceDialog()
        }
    }
}