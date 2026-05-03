package io.zhihao.library.android.util

import android.media.MediaScannerConnection
import android.os.Build
import io.zhihao.library.android.ZLibrary
import java.io.File

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-29 18:32

 */
object AndroidUtil {
    fun notifyMediaStore(file: File): Boolean {
        return if (file.isFile) {
            try {
                MediaScannerConnection.scanFile(
                    ZLibrary.getAppContext(),
                    arrayOf(file.absolutePath),
                    null,
                    null
                )
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        } else {
            false
        }
    }

    // 原来的isMoreThenSDKR
    fun isAtLeastAndroidR(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }
}
