package io.zhihao.library.android.util

import android.app.WallpaperManager
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import io.zhihao.library.android.ZLibrary
import io.zhihao.library.android.kotlinEx.collapseNotificationBar

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-07 19:51

 */
class AppUtil {
    companion object {
        fun isDebug(): Boolean {
            return try {
                ZLibrary.getContext().applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun collapseNotificationBar() {
            ZLibrary.getContext().collapseNotificationBar()
        }

        fun getWallpaperBitmap(): Bitmap? {
            val wmInstance = WallpaperManager.getInstance(ZLibrary.getContext())
            return if (wmInstance.isWallpaperSupported) wmInstance.drawable.toBitmap() else null
        }

        fun getString(stringRes: Int, formatArgs: Any? = null): String = ZLibrary.getContext().getString(
            stringRes,
            formatArgs
        )

    }
}