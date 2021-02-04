package com.example.xcloudkt.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.adapter.CommonAdapter
import com.example.xcloudkt.adapter.adapter.base.ViewHolder
import com.example.xcloudkt.bean.home.BrandsBean
import com.example.xcloudkt.util.glide.ImageLoader
import com.example.xcloudkt.weight.decoration.RoundedCornersTransform

/**
 * author : zhanghang
 * date : 2020/5/7 11:00
 */
class HomeBandAdapter(context: Context?, datas: List<BrandsBean?>?) :
    CommonAdapter<BrandsBean?>(context, R.layout.item_home_image, datas) {

    override fun convert(
        holder: ViewHolder?, mBean: BrandsBean?, position: Int
    ) {
        holder?.setText(R.id.tv_name, mBean?.name)
            ?.setOnClickListener(
                R.id.rl
            ) { v: View? ->
                mOnItemClickListener.onItemClick(
                    v,
                    holder,
                    position
                )
            }
        ImageLoader.getInstance()?.loadingImage(
            mContext, mBean?.imgUrl, holder?.getView(R.id.iv_picture),
            MultiTransformation(
                FitCenter(),
                RoundedCornersTransform(20f, 20f, 0f, 0f)
            ), R.drawable.default_band
        )
    }
}