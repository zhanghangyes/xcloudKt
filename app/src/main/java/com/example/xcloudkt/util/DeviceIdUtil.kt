package com.example.xcloudkt.util

import android.content.Context
import android.os.Build
import android.os.Environment
import android.telephony.TelephonyManager
import java.io.*
import java.net.NetworkInterface
import java.net.SocketException
import java.security.MessageDigest
import java.util.*

/**
 * author : zhanghang
 * date : 2020/6/22 11:25
 */
object DeviceIdUtil {
    //保存文件的路径
    private const val CACHE_DEVICES_DIR = "csair-mmp-devices/devices"

    //保存的文件 采用隐藏文件的形式进行保存
    private const val DEVICES_FILE_NAME = ".DEVICES"

    /**
     * 获取设备唯一标识符
     *
     * @param context
     * @return
     */
    fun getDeviceId(context: Context): String {
        //读取保存的在sd卡中的唯一标识符
        var deviceId = readDeviceID(context)
        //判断是否已经生成过,
        if (deviceId != null && "" != deviceId) {
            return deviceId
        }
        //用于生成最终的唯一标识符
        val s = StringBuffer()
        try {
            //获取IMES(也就是常说的DeviceId)
            deviceId = getIMIEStatus(context)
            s.append(deviceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            //获取设备的MACAddress地址 去掉中间相隔的冒号
            deviceId = getLocalMac(context).replace(":", "")
            s.append(deviceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //        }

        //如果以上搜没有获取相应的则自己生成相应的UUID作为相应设备唯一标识符
        if (s == null || s.length <= 0) {
            val uuid = UUID.randomUUID()
            deviceId = uuid.toString().replace("-", "")
            s.append(deviceId)
        }
        //为了统一格式对设备的唯一标识进行md5加密 最终生成32位字符串
        val md5 = getMD5(s.toString(), false)
        if (s.length > 0) {
            //持久化操作, 进行保存到SD卡中
            saveDeviceID(md5, context)
        }
        return md5
    }

    /**
     * 读取固定的文件中的内容,这里就是读取sd卡中保存的设备唯一标识符
     *
     * @param context
     * @return
     */
    private fun readDeviceID(context: Context): String? {
        val file = getDevicesDir(context)
        val buffer = StringBuffer()
        return try {
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis, "UTF-8")
            val `in`: Reader = BufferedReader(isr)
            var i: Int
            while (`in`.read().also { i = it } > -1) {
                buffer.append(i.toChar())
            }
            `in`.close()
            buffer.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取设备的DeviceId(IMES) 这里需要相应的权限<br></br>
     * 需要 READ_PHONE_STATE 权限
     *
     * @param context
     * @return
     */
    private fun getIMIEStatus(context: Context): String {
        val tm = context
            .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.deviceId
    }

    /**
     * 获取设备MAC 地址 由于 6.0 以后 WifiManager 得到的 MacAddress得到都是 相同的没有意义的内容
     * 所以采用以下方法获取Mac地址
     *
     * @param context
     * @return
     */
    private fun getLocalMac(context: Context): String {
//        WifiManager wifi = (WifiManager) context
//                .getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = wifi.getConnectionInfo();
//        return info.getMacAddress();
        var macAddress: String? = null
        val buf = StringBuffer()
        var networkInterface: NetworkInterface? = null
        try {
            networkInterface = NetworkInterface.getByName("eth1")
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0")
            }
            if (networkInterface == null) {
                return ""
            }
            val addr = networkInterface.hardwareAddress
            for (b in addr) {
                buf.append(String.format("%02X:", b))
            }
            if (buf.length > 0) {
                buf.deleteCharAt(buf.length - 1)
            }
            macAddress = buf.toString()
        } catch (e: SocketException) {
            e.printStackTrace()
            return ""
        }
        return macAddress
    }

    /**
     * 保存 内容到 SD卡中,  这里保存的就是 设备唯一标识符
     *
     * @param str
     * @param context
     */
    private fun saveDeviceID(str: String, context: Context) {
        val file = getDevicesDir(context)
        try {
            val fos = FileOutputStream(file)
            val out: Writer = OutputStreamWriter(fos, "UTF-8")
            out.write(str)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 对挺特定的 内容进行 md5 加密
     *
     * @param message   加密明文
     * @param upperCase 加密以后的字符串是是大写还是小写  true 大写  false 小写
     * @return
     */
    private fun getMD5(message: String, upperCase: Boolean): String {
        var md5str = ""
        try {
            val md = MessageDigest.getInstance("MD5")
            val input = message.toByteArray()
            val buff = md.digest(input)
            md5str = bytesToHex(buff, upperCase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return md5str
    }

    private fun bytesToHex(bytes: ByteArray, upperCase: Boolean): String {
        val md5str = StringBuffer()
        var digital: Int
        for (i in bytes.indices) {
            digital = bytes[i].toInt()
            if (digital < 0) {
                digital += 256
            }
            if (digital < 16) {
                md5str.append("0")
            }
            md5str.append(Integer.toHexString(digital))
        }
        return if (upperCase) {
            md5str.toString().toUpperCase()
        } else md5str.toString().toLowerCase()
    }

    /**
     * 统一处理设备唯一标识 保存的文件的地址
     *
     * @param context
     * @return
     */
    private fun getDevicesDir(context: Context): File {
        var mCropFile: File? = null
        mCropFile = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val cropdir = File(
                Environment.getExternalStorageDirectory(),
                CACHE_DEVICES_DIR
            )
            if (!cropdir.exists()) {
                cropdir.mkdirs()
            }
            File(cropdir, DEVICES_FILE_NAME)
        } else {
            val cropdir =
                File(context.filesDir, CACHE_DEVICES_DIR)
            if (!cropdir.exists()) {
                cropdir.mkdirs()
            }
            File(cropdir, DEVICES_FILE_NAME)
        }
        return mCropFile
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    val systemVersion: String
        get() = Build.VERSION.RELEASE
}