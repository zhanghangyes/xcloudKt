package com.example.xcloudkt.util.sp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import java.lang.reflect.Type

/**
 * JSON解析二次封装
 */
class JsonUtils {
    // 采取单例模式
    private var mGson: Gson? = null
    val gson: Gson?
        get() {
            if (null == mGson) {
                mGson = GsonBuilder().serializeNulls().create()
            }
            return mGson
        }

    fun <T> fromJson(json: String?, type: Class<T>?): T? {
        try {
            return gson?.fromJson(json, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @param json
     * @param typeOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为 new
     * TypeToken<List></List><T>>(){}.getType()
     * ，其它类也可以用此方法调用，就是将List<T>替换为您想要转成的类
    </T></T> */
    fun fromJson(json: String?, typeOfT: Type?): Any? {
        try {
            return mGson?.fromJson<Any>(json, typeOfT)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private val mUtils: JsonUtils? = null
        val instance: JsonUtils?
            get() = mUtils ?: JsonUtils()

        /**
         * 获取json中的某个值
         *
         * @param json
         * @param key
         * @return
         */
        fun getValue(json: String?, key: String?): String? {
            try {
                val `object` = JSONObject(json)
                return `object`.getString(key)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * 获取json中的list值
         *
         * @param json
         * @return
         */
        fun getListValue(json: String?): String? {
            try {
                val `object` = JSONObject(json)
                return `object`.getString("list")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun getDoubleValue(json: String?, key: String?): Double? {
            try {
                val `object` = JSONObject(json)
                return `object`.getDouble(key)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun getIntValue(json: String?, key: String?): Int {
            try {
                val `object` = JSONObject(json)
                return `object`.getInt(key)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return 0
        }
    }
}