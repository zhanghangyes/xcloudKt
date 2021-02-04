package com.example.xcloudkt.net.rx

import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

object OkHttpRequestBodyFactory {
    private val MEDIA_TYPE =
        MediaType.parse("application/json; charset=utf-8")

    fun getJsonRequestBody(request: String?): RequestBody {
        var request = request
        if (request == null) {
            request = ""
        }
        return RequestBody.create(MEDIA_TYPE, request)
    }

    fun getMultipartRequestBody(params: Map<String?, String?>): RequestBody {
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        for ((key, value) in params) {
            builder.addFormDataPart(key, value)
        }
        return builder.build()
    }

    fun getFormRequestBody(params: Map<String?, String?>): RequestBody {
        val builder = FormBody.Builder()
        for ((key, value) in params) {
            builder.add(key, value)
        }
        return builder.build()
    }
}