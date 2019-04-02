package io.zhihao.library.android.util

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 17:29

 */
class DatetimeUtil {
    companion object {
        fun unixTimeStampMill(): Long = System.currentTimeMillis()
        fun unixTimeStamp(): Long = System.currentTimeMillis() / 1000L
    }

}