package com.example.xcloudkt.adapter

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.xcloudkt.R
import com.example.xcloudkt.adapter.adapter.CommonAdapter
import com.example.xcloudkt.adapter.adapter.base.ViewHolder
import com.example.xcloudkt.bean.home.BrandsBean
import com.example.xcloudkt.util.glide.GlideRoundTransform
import com.example.xcloudkt.util.glide.ImageLoader

/**
 * author : zhanghang
 * date : 2020/10/15 10:00
 */
class HomeActivityAdapter(context: Context?, datas: List<BrandsBean?>) :
    CommonAdapter<BrandsBean?>(context, R.layout.item_home_activity, datas) {
    override fun convert(
        holder: ViewHolder?,
        mBean: BrandsBean?,
        position: Int
    ) {
        val imageView =
            holder?.getView<ImageView>(R.id.iv_activity_invite)
        Glide.with(mContext)
            .asBitmap() //强制Glide返回一个Bitmap对象
            .load(mBean?.imgUrl)
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    bitmap: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    val mWidth = bitmap.width.toFloat()
                    val mHeight = bitmap.height.toFloat()
                    val params = imageView?.layoutParams
                    params?.height = mContext.resources.getDimensionPixelOffset(R.dimen.dp120)
                    params?.width = (mWidth / mHeight * (params?.height
                        ?: mContext.resources.getDimensionPixelOffset(R.dimen.dp120))).toInt()
                    imageView?.layoutParams = params
                    ImageLoader.getInstance()?.loadingImage(
                        mContext, mBean?.imgUrl, imageView,
                        MultiTransformation(
                            CenterCrop(),
                            GlideRoundTransform(mContext, 5)
                        ), -1
                    )
                }
            })
    }

}