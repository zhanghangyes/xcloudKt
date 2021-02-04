package com.example.xcloudkt.adapter

import android.content.Context
import android.widget.TextView
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.adapter.CommonAdapter
import com.example.xcloudkt.adapter.adapter.base.ViewHolder
import com.example.xcloudkt.bean.home.NewarrivalsBean
import com.example.xcloudkt.util.PriceUtil.setPricePoint
import com.example.xcloudkt.util.glide.ImageLoader
import com.example.xcloudkt.weight.decoration.RoundedCornersTransform

/**
 * author : zhanghang
 * date : 2020/5/7 11:00
 */
class HomeNewAdapter(context: Context, datas: List<NewarrivalsBean?>) :
    CommonAdapter<NewarrivalsBean>(context, R.layout.item_home_new, datas) {

    override fun convert(holder: ViewHolder?, mBean: NewarrivalsBean?, position: Int) {
        val tvPrice = holder?.getView<TextView>(R.id.tv_price)

        setPricePoint(mBean?.price, tvPrice, true, mContext)
        holder?.setText(R.id.name, mBean?.productName)
            ?.setOnClickListener(R.id.ll_new_top) { v ->
                mOnItemClickListener.onItemClick(
                    v,
                    holder,
                    position
                )
            }

        ImageLoader.getInstance()?.loadingImage(
            mContext, mBean?.picUrl, holder?.getView(R.id.iv_picture),
            MultiTransformation(
                FitCenter(),
                RoundedCornersTransform(20f, 20f, 20f, 20f)
            ), R.drawable.default_new
        )
    }

}