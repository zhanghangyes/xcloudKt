package com.example.xcloudkt.util

import android.text.TextUtils
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.experimental.and

object StringUtils {
    fun byte2hex(b: ByteArray): String {
        var str = ""
        var stmp = ""
        val length = b.size
        for (n in 0 until length) {
            stmp = Integer.toHexString((b[n] and 0XFF.toByte()).toInt())
            if (stmp.length == 1) {
                str += "0"
            }
            str += stmp
        }
        return str.toLowerCase()
    }

    fun nullToStrTrim(str: String?): String {
        var str = str
        if (str == null) {
            str = ""
        }
        return str.trim { it <= ' ' }
    }

    fun isNotEmpty(str: String?): Boolean {
        return str != null && str.trim { it <= ' ' }.length > 0
    }

    fun isEmpty(str: String?): Boolean {
        return !isNotEmpty(str)
    }

    fun getRealLength(str: String): Int {
        return getRealLength(str, "utf-8")
    }

    fun getRealLength(str: String, charsetName: String?): Int {
        var str = str
        str = nullToStrTrim(str)
        return try {
            str.toByteArray(charset(charsetName.toString())).size
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            0
        }
    }

    @JvmOverloads
    fun encode(str: String?, enc: String? = "utf-8"): String {
        var strEncode = ""
        try {
            if (str != null) strEncode = URLEncoder.encode(str, enc)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return strEncode
    }

    @JvmOverloads
    fun decode(str: String?, enc: String? = "utf-8"): String {
        var strDecode = ""
        try {
            if (str != null) strDecode = URLDecoder.decode(str, enc)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return strDecode
    }

    fun replaceEmptyValue(amount: String?): String? {
        return if (TextUtils.isEmpty(amount)) {
            "0"
        } else amount
    }
}