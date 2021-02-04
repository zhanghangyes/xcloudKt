package com.example.xcloudkt.net.api

import com.example.xcloudkt.bean.SplashBean
import com.example.xcloudkt.bean.home.HomeInfoBean
import com.example.xcloudkt.bean.home.HomeToDayBean
import com.example.xcloudkt.bean.icon.NavigatorsBean
import com.example.xcloudkt.net.response.BaseResponseBean
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *ApiService
 */
interface ApiService {

    /**
     * 获取闪屏弹窗广告
     */
    @POST("market/start/ad")
    fun queryAD(@Body requestBody: RequestBody): Flowable<BaseResponseBean<SplashBean>>//默认通用请求

    /**
     * 首页聚合接口
     * 1.根据经纬度查询最近的门店
     * 2.banner图
     * 3.新品上市
     * 4.品牌专区
     */
    @POST("biz/home")
    fun queryHomeInfo(@Body requestBody: RequestBody): Observable<BaseResponseBean<HomeInfoBean>>

    /**
     * 今日特惠
     */
    @POST("biz/activity/today/special")
    fun querySpecialList(@Body requestBody: RequestBody): Observable<HomeToDayBean>

    //底部导航栏
    @POST("cms/icon/iconGroup")
    fun navigator(): Observable<NavigatorsBean>

}