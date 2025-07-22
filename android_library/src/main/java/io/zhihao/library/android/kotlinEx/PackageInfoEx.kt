package io.zhihao.library.android.kotlinEx

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import io.zhihao.library.android.util.SystemServiceUtil

/**

 * @author: zhihaofans

 * @date: 2019-01-04 13:02

 */
fun ApplicationInfo.getAppName(): String =
    this.loadLabel(SystemServiceUtil.getPackageManager()).toString()

fun PackageInfo.getAppName(): String = this.applicationInfo!!.getAppName()

fun ApplicationInfo.getAppIcon(): Drawable? = this.loadIcon(SystemServiceUtil.getPackageManager())
fun PackageInfo.getAppIcon(): Drawable? = this.applicationInfo!!.getAppIcon()
