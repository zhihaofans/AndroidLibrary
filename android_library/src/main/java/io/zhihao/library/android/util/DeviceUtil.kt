package io.zhihao.library.android.util

import android.content.res.Configuration
import android.os.Build
import android.telephony.TelephonyManager
import io.zhihao.library.android.ZLibrary

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-07 20:24

 */
class DeviceUtil {
    companion object {
        fun getManufacturer(): String = Build.MANUFACTURER
        fun isXiaomi(): Boolean = getManufacturer().toLowerCase() == "xiaomi"
        fun isSamsung(): Boolean = getManufacturer().toLowerCase() == "samsung"
        fun isPhone(): Boolean {
            // 检测是否有可用的移动网络，带SIM卡的平板可能出现误判
            return try {
                SystemServiceUtil.getTelephonyManager().phoneType != TelephonyManager.PHONE_TYPE_NONE
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun isTablet(): Boolean {
            // 通过屏幕大小判断是否为平板
            return ZLibrary.getAppContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }
    }
}