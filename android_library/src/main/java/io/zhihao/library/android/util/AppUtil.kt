package io.zhihao.library.android.util

import android.app.WallpaperManager
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.graphics.drawable.toBitmap
import io.zhihao.library.android.ZLibrary
import io.zhihao.library.android.kotlinEx.collapseNotificationBar
import io.zhihao.library.android.kotlinEx.getAppName


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-07 19:51

 */
class AppUtil {
    companion object {
        fun isDebug(): Boolean {
            return try {
                this.isAppDebug(ZLibrary.getContext().packageName)
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

        fun isAppDebug(packageName: String): Boolean {
            return if (this.isAppInstalled(packageName)) {
                try {
                    SystemServiceUtil.getPackageManager().getApplicationInfo(
                        packageName,
                        0
                    ).flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            } else {
                false
            }
        }

        fun isAppInstalled(packageName: String): Boolean {
            if (packageName.isEmpty()) return false
            return try {
                SystemServiceUtil.getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun isSystemApp(packageName: String): Boolean {
            if (packageName.isEmpty()) return false
            if (!this.isAppInstalled(packageName)) return false
            return try {
                val appInfo = SystemServiceUtil.getPackageManager()
                    .getApplicationInfo(packageName, 0)
                (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun getAppVersionCode(): Int? = this.getAppVersionCode(ZLibrary.getContext().packageName)
        fun getAppVersionCode(packageName: String): Int? {
            if (packageName.isEmpty()) return -1
            return try {
                val pi =
                    SystemServiceUtil.getPackageManager()
                        .getPackageInfo(packageName, 0)
                PackageInfoCompat.getLongVersionCode(pi).toInt()
            } catch (e: Exception) {
                e.printStackTrace()
                -1
            }
        }

        fun getAppVersionName(): String? = this.getAppVersionName(ZLibrary.getContext().packageName)
        fun getAppVersionName(packageName: String): String? {
            return if (this.isAppInstalled(packageName)) {
                try {
                    SystemServiceUtil.getPackageManager().getPackageInfo(packageName, 0).versionName
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } else {
                null
            }

        }

        fun getAppName(packageName: String): String? {
            return if (this.isAppInstalled(packageName)) {
                try {
                    SystemServiceUtil.getPackageManager().getPackageInfo(packageName, 0).getAppName()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } else {
                null
            }
        }

        fun getAppPath(packageName: String): String? {
            return if (this.isAppInstalled(packageName)) {
                try {
                    SystemServiceUtil.getPackageManager().getApplicationInfo(packageName, 0).sourceDir
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } else {
                null
            }
        }
    }
}