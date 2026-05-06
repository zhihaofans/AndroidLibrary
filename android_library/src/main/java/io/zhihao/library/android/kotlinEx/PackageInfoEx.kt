package io.zhihao.library.android.kotlinEx

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
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