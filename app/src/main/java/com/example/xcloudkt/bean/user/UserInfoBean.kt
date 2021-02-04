package com.example.xcloudkt.bean.user

import com.example.xcloudkt.util.StringUtils.isNotEmpty
import java.io.Serializable

/**
 * author : zhanghang
 * date : 2020/5/19 15:43
 */
class UserInfoBean : Serializable {
    /**
     * token : eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo0jj1uAjEUhO_y6rW0fu-t_26QhoLazfPaFhsl0gqDhED0HIOaM3CdSDlGFgjdzKcZzZzgczdBABEaGbNWZqxacWanvDFeVRSsox8H4xx00PZpCZ8i7FvZruS7RAgRfu7X38stQvfiH_lJyZNmzdn27IiG3iKzyeQxYbUDJpOMGGFitJYy5Wdf5vm_viiVpmOE87I7tfY4-UIPLzsIevC9d9pZ20E5zBBYO3yDqa1L3Za2gVDlq5XzHwAAAP__.ZdxvIMzYZv-r9_geZXOYX0o8LIo143aSAMiwec3EZalbY-nKgqnZplzV7cVISwRY9Cd1uklgRySXfndf7BNBdQ
     * memberCode : 3931414d7048335072446d392b2f752b6b6a6a4342773d3d
     * lastName : 张
     * realName : 张航
     * genderCd : 1
     * birthday : null
     * picUrl : null
     * nickName : null
     * firstLoginFlag : false
     * perfectInfoFlag : true
     * open3rd : null
     */
    var token: String? = null
    var memberCode: String? = null
        get() = if (isNotEmpty(field)) field else ""
    var lastName: String? = null
    var realName: String? = null
    var genderCd = 0
    var birthday: String? = null
    var picUrl: String? = null
    var nickName: String? = null
    var isFirstLoginFlag = false
    var isPerfectInfoFlag = false

    //是否尊享等级
    var isZxMemberFlag = false
    private var open3rd: String? = null
    val activityId: String? = null
    var phoneNumber: String? = null

    fun setOpen3rd(open3rd: String?) {
        this.open3rd = open3rd
    }

    fun getOpen3rd(): Any? {
        return open3rd
    }

}