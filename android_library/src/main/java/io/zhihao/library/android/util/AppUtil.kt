package io.zhihao.library.android.util

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.annotation.RequiresPermission
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.graphics.drawable.toBitmap
import io.zhihao.library.android.ZLibrary
import io.zhihao.library.android.kotlinEx.collapseNotificationBar
import io.zhihao.library.android.kotlinEx.getAppName
import java.io.File


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-07 19:51

 */
class AppUtil {
    companion object {
        fun isDebug(): Boolean {
            return try {
                this.isAppDebug(ZLibrary.getAppContext().packageName)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun collapseNotificationBar() {
            ZLibrary.getAppContext().collapseNotificationBar()
        }

        fun getString(stringRes: Int, formatArgs: Any? = null): String = ZLibrary.getAppContext().getString(
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
            return when {
                packageName.isEmpty() -> false
                !this.isAppInstalled(packageName) -> false
                else -> try {
                    val appInfo = SystemServiceUtil.getPackageManager()
                        .getApplicationInfo(packageName, 0)
                    (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }
        }

        fun getAppVersionCode(): Int? = this.getAppVersionCode(ZLibrary.getAppContext().packageName)
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

        fun getAppVersionName(): String? = this.getAppVersionName(ZLibrary.getAppContext().packageName)
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

        fun getInstalledAppList(): MutableList<ApplicationInfo>? {
            return SystemServiceUtil.getPackageManager().getInstalledApplications(0)
        }

        fun launchApp(packageName: String): Boolean {
            val mIntent = IntentUtil.getLaunchAppIntent(packageName)
            return try {
                this.startActivity(mIntent ?: return false)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
        fun launchApp(context: Context, packageName: String): Boolean {
            val mIntent = context.packageManager.getLaunchIntentForPackage(packageName)
            return try {
                mIntent?.let { context.startActivity(it) }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        @RequiresPermission(android.Manifest.permission.REQUEST_INSTALL_PACKAGES)
        fun installApp(filePath: String, authority: String): Boolean {
            return this.installApp(FileUtil.getFileByPath(filePath) ?: return false, authority)
        }

        @RequiresPermission(android.Manifest.permission.REQUEST_INSTALL_PACKAGES)
        fun installApp(file: File, authority: String): Boolean {
            return if (FileUtil.isFileExists(file)) {
                try {
                    this.startActivity(IntentUtil.getInstallAppIntent(file, authority, true) ?: return false)
                    true
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            } else {
                false
            }
        }

        fun uninstallApp(packageName: String): Boolean {
            return if (this.isAppInstalled(packageName)) {
                val mIntent = IntentUtil.getUninstallAppIntent(packageName, true) ?: return false
                this.startActivity(mIntent)
                false
            } else {
                false
            }

        }

        fun startActivity(mIntent: Intent) = ZLibrary.getAppContext().startActivity(mIntent)
    }
}