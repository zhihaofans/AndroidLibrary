package io.zhihao.library.android.kotlinEx

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import io.zhihao.library.android.util.SystemServiceUtil

/**

 * @author: zhihaofans

 * @date: 2019-01-04 13:02

 */
//fun ApplicationInfo.getAppName(): String =s

//fun PackageInfo.getAppName(): String = this.applicationInfo!!.getAppName()

fun ApplicationInfo.getAppIcon(): Drawable? = this.loadIcon(SystemServiceUtil.getPackageManager())
fun PackageInfo.getAppIcon(): Drawable? = this.applicationInfo!!.getAppIcon()
val ApplicationInfo.appName: String
    get() =
        this.loadLabel(SystemServiceUtil.getPackageManager()).toString()
val PackageInfo.appName: String
    get() = this.applicationInfo!!.loadLabel(SystemServiceUtil.getPackageManager()).toString()

val ApplicationInfo.versionName: String
    get() = this.appVersionName

val ApplicationInfo.appVersionName: String
    get() = SystemServiceUtil
        .getPackageManager()
        .getPackageInfoCompat(packageName)
        .versionName ?: ""

val ApplicationInfo.versionCode: Int
    get() = this.appVersionCode

val ApplicationInfo.appVersionCode: Int
    get() = SystemServiceUtil
        .getPackageManager()
        .getPackageInfoCompat(packageName)
        .versionCode

val ApplicationInfo.versionCodeString: String
    get() = this.appVersionCodeString

val ApplicationInfo.appVersionCodeString: String
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        SystemServiceUtil
            .getPackageManager()
            .getPackageInfoCompat(packageName)
            .longVersionCode
    } else {
        @Suppress("DEPRECATION")
        SystemServiceUtil
            .getPackageManager()
            .getPackageInfoCompat(packageName)
            .versionCode.toLong()
    }.toString()

val ApplicationInfo.longVersionCode: Long
    @RequiresApi(Build.VERSION_CODES.P)
    get() = this.appLongVersionCode

val ApplicationInfo.appLongVersionCode: Long
    @RequiresApi(Build.VERSION_CODES.P)
    get() = SystemServiceUtil
        .getPackageManager()
        .getPackageInfoCompat(packageName)
        .longVersionCode