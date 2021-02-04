package com.example.xcloudkt.net.rx

import android.text.TextUtils
import android.util.Log
import com.example.xcloudkt.BuildConfig
import com.example.xcloudkt.app.MyApp
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.net.api.ApiConstants
import com.example.xcloudkt.net.api.ApiService
import com.example.xcloudkt.net.api.EnvType
import com.example.xcloudkt.util.DeviceIdUtil
import com.example.xcloudkt.util.NetworkUtil
import com.example.xcloudkt.util.sp.SpUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.Util
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 *RetrofitManager
 */
object RetrofitManager {

    //读超时长，单位：毫秒
    private const val READ_TIME_OUT = 30L

    //连接时长，单位：毫秒
    private const val CONNECT_TIME_OUT = 30L

    val apiService: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        // 获取retrofit的实例
        return Retrofit.Builder()
            .baseUrl(ApiConstants.environment?.javaHost)//根据--BuildConfig.ENV_TYPE--来获取地址
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? ->
                if (ApiConstants.IS_OPEN_DEBUG) {
                    message?.let {
                        Log.e(
                            "----请求http数据----", it
                        )
                    }
                }
            })
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(addCacheInterceptor)
            .addNetworkInterceptor(addCacheInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应都看到
            .build()
    }

    //增加头部信息
    private var headerInterceptor = Interceptor { chain ->
        val request = chain.request()
        val bodyContent = bodyContent(request)
        //设置头信息
        val headersMap: MutableMap<String, String> = headersMap().toMutableMap()
        val builder = request.newBuilder()
        if (headersMap.isNotEmpty()) {
            val keys: Set<String> = headersMap.keys
            for (headerKey in keys) {
                builder.addHeader(headerKey, headersMap[headerKey]).build()
            }
            //签名
            //当 signMethod 值输入为 md5 时，对整个请求头+请求体参数按 ASCII 顺序拼接好做 md5 签名后的值赋给此字段。
            // headersMap.put("appSecret", "x2era.com");
            headersMap.put("parameters", bodyContent.toString())
            builder.addHeader("Content-Type", "application/json")
            if (BuildConfig.ENV_TYPE == EnvType.DEVELOP)
                builder.addHeader("channel", "111")
            else
                builder.addHeader("channel", "333")
            val tokenContent = SpUtils.getString(Constant.APP_AUTH_TOKEN, "")
            builder.addHeader("token", tokenContent) //登录的 token，当没有时请赋值一个””空串.
            builder.addHeader("deviceId", DeviceIdUtil.getDeviceId(MyApp.mContext)) //设备验证
        }
        //请求信息
        chain.proceed(builder.build())
    }

    /**
     * 云端响应头拦截器,设置缓存
     */
    private val addCacheInterceptor = Interceptor { chain ->
        var request = chain.request()
        val cacheControl = request.cacheControl().toString()
        if (!NetworkUtil.isConnected()) {
            request = request.newBuilder()
                .cacheControl(if (TextUtils.isEmpty(cacheControl)) CacheControl.FORCE_NETWORK else CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (NetworkUtil.isConnected()) {
            val maxAge = 0
            // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
            response.newBuilder()
                .header("Cache-Control", "public, max-age= $maxAge")
                .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .build()
        } else {
            // 无网络时，设置超时为4周  只对get有用,post没有缓冲
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("nyn")
                .build()
        }
    }

    /**
     * 默认头信息
     */
    private fun headersMap(): Map<String, String> {
        val headers: MutableMap<String, String> = HashMap()
        headers["fmVersion"] = BuildConfig.VERSION_NAME//服务端根据version 号用于针对版本号区别处理
        headers["loginChannel"] = "app" //用于区分h5跟app
        return headers
    }

    /**
     * 获取请求body内容
     * @param request
     */
    @Throws(IOException::class)
    fun bodyContent(request: Request): String? {
        var stringBody = ""
        val requestBody = request.body()
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset = Util.UTF_8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(Util.UTF_8)
            }
            stringBody = buffer.readString(charset)
        }
        return stringBody
    }
}
