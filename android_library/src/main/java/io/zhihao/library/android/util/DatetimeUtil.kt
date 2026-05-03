package io.zhihao.library.android.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 17:29

 */
object DatetimeUtil {

    fun unixTimestampMillis(): Long = System.currentTimeMillis()

    fun unixTimestampSeconds(): Long = System.currentTimeMillis() / 1000

    @SuppressLint("SimpleDateFormat")
    fun nowDatetime(withoutTime: Boolean = false): String {
        val pattern =
            if (withoutTime) "yyyy/MM/dd"
            else "yyyy/MM/dd HH:mm:ss"

        return SimpleDateFormat(pattern).format(Date())
    }

    fun unixTime2ChinaDate(seconds: Long): String {
        return unixTime2date(seconds, Locale.CHINA, true)
    }

    fun unixTime2date(
        time: Long,
        locale: Locale = Locale.getDefault(),
        isSeconds: Boolean = false
    ): String {
        val millis = if (isSeconds) time * 1000 else time
        return SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss",
            locale
        ).format(Date(millis))
    }
}
