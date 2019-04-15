package io.zhihao.library.android.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 17:29

 */
class DatetimeUtil {
    companion object {
        fun unixTimeStampMill(): Long = System.currentTimeMillis()
        fun unixTimeStamp(): Long = System.currentTimeMillis() / 1000L

        @SuppressLint("SimpleDateFormat")
        fun nowDatetime(withoutTime: Boolean = false): String {
            val formatter = SimpleDateFormat(if (withoutTime) "yyyy/MM/dd" else "yyyy/MM/dd HH:mm:ss")
            val curDate = Date()
            return formatter.format(curDate)
        }

        fun unixTime2ChinaDate(time: Long): String {
            return unixTime2date(time, Locale.CHINA)
        }

        fun unixTime2date(time: Int, locale: Locale): String {
            return SimpleDateFormat("yyyy/MM/dd HH:mm:ss", locale).format(Date(time.toLong())) as String
        }

        fun unixTime2date(time: Long, locale: Locale): String {
            return SimpleDateFormat("yyyy/MM/dd HH:mm:ss", locale).format(Date(time)) as String
        }
    }

}