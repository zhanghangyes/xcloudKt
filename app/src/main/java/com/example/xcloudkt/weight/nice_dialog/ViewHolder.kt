package com.example.xcloudkt.weight.nice_dialog

import android.util.SparseArray
import android.view.View
import android.widget.TextView

class ViewHolder private constructor(val convertView: View) {

    private val views: SparseArray<View?>
    fun <T : View?> getView(viewId: Int): T? {
        var view = views[viewId]
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T?
    }

    fun setText(viewId: Int, text: CharSequence?) {
        val textView = getView<TextView>(viewId)!!
        textView.text = text
    }

    fun setText(viewId: Int, textId: Int) {
        val textView = getView<TextView>(viewId)!!
        textView.setText(textId)
    }

    fun setTextColor(viewId: Int, colorId: Int) {
        val textView = getView<TextView>(viewId)!!
        textView.setTextColor(colorId)
    }

    fun setOnClickListener(
        viewId: Int,
        clickListener: View.OnClickListener?
    ) {
        val view = getView<View>(viewId)!!
        view.setOnClickListener(clickListener)
    }

    fun setBackgroundResource(viewId: Int, resId: Int) {
        val view = getView<View>(viewId)!!
        view.setBackgroundResource(resId)
    }

    fun setBackgroundColor(viewId: Int, colorId: Int) {
        val view = getView<View>(viewId)!!
        view.setBackgroundColor(colorId)
    }

    companion object {
        fun create(view: View): ViewHolder {
            return ViewHolder(view)
        }
    }

    init {
        views = SparseArray()
    }
}