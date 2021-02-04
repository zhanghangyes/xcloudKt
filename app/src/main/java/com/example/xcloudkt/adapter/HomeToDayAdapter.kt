package com.example.xcloudkt.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.adapter.CommonAdapter
import com.example.xcloudkt.adapter.adapter.base.ViewHolder
import com.example.xcloudkt.bean.home.HomeToDayBeanInner
import com.example.xcloudkt.util.glide.ImageLoader
import com.example.xcloudkt.weight.decoration.RoundedCornersTransform

/**
 * author : zhanghang
 * date : 2020/5/7 11:00
 */
class HomeToDayAdapter(context: Context, datas: List<HomeToDayBeanInner?>?) :
    CommonAdapter<HomeToDayBeanInner?>(
        context,
        R.layout.item_home_today,
        datas
    ) {
    override fun convert(
        holder: ViewHolder?,
        mBean: HomeToDayBeanInner?,
        position: Int
    ) {
        holder?.setText(R.id.name, mBean?.categoryName)
            ?.setText(R.id.tv_name, mBean?.activitySlogan)
            ?.setOnClickListener(
                R.id.ll_home_special
            ) { v: View? ->
                mOnItemClickListener.onItemClick(
                    v,
                    holder,
                    position
                )
            }
        ImageLoader.getInstance()!!.loadingImage(
            mContext,
            mBean?.picUrl,
            holder?.getView(R.id.iv_picture),
            MultiTransformation(CenterCrop(), RoundedCornersTransform(20f, 20f, 0f, 0f)),
            R.drawable.default_today
        )
    }

}