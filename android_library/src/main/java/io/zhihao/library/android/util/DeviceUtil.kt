package io.zhihao.library.android.util

import android.os.Build

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
    }
}