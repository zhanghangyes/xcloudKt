package com.example.xcloudkt.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.adapter.CommonAdapter
import com.example.xcloudkt.adapter.adapter.base.ViewHolder
import com.example.xcloudkt.bean.icon.IconBean
import com.example.xcloudkt.util.StringUtils.isNotEmpty
import com.example.xcloudkt.util.glide.GlideRoundTransform
import com.example.xcloudkt.util.glide.ImageLoader

/**
 * 金刚区
 * author : zhanghang
 * date : 2020/11/23 11:00
 */
class HomeIconAdapter(context: Context, datas: List<IconBean>) :
    CommonAdapter<IconBean>(context, R.layout.item_home_icon, datas) {

    override fun convert(holder: ViewHolder?, mBean: IconBean?, position: Int) {
        val imageView = holder?.getView<ImageView>(R.id.iv_icon)
        holder?.setText(R.id.tv_name, mBean?.name)
            ?.setOnClickListener(R.id.ll_home_icon) { v: View? ->
                mOnItemClickListener?.onItemClick(v, holder, position)
            }
        if (isNotEmpty(mBean?.iconNormal)) {
            ImageLoader.getInstance()?.loadingImage(
                mContext,
                mBean?.iconNormal,
                holder?.getView(R.id.iv_icon),
                MultiTransformation(FitCenter(), GlideRoundTransform(mContext, 0)),
                R.drawable.default_new
            )
        }
    }
}