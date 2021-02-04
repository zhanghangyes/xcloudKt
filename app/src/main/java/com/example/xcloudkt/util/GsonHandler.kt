package com.example.xcloudkt.util

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

object GsonHandler {
    private const val TAG = "GsonHandler"
    private val FROMGSON = GsonBuilder().create()
    private val TOGSON = GsonBuilder().create()

    @Throws(JsonSyntaxException::class)
    fun <T> fromJson(json: String?, tClass: Class<T>): T {
        return try {
            FROMGSON.fromJson(json, tClass)
        } catch (ex: JsonSyntaxException) {
            Log.d(
                TAG,
                "fromJson: Json to Object (" + tClass.simpleName + ") with error"
            )
            throw ex
        }
    }

    @Throws(JsonSyntaxException::class)
    fun <T> fromJson(json: String?, type: Type): T {
        return try {
            FROMGSON.fromJson(json, type)
        } catch (ex: JsonSyntaxException) {
            Log.d(
                TAG,
                "fromJson: Json to Object (" + type.javaClass.simpleName + ") with error"
            )
            throw ex
        }
    }

    fun toJson(`object`: Any?): String {
        return TOGSON.toJson(`object`)
    }
}