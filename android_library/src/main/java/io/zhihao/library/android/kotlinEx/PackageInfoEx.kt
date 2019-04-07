package io.zhihao.library.android.kotlinEx

import android.content.pm.PackageInfo
import io.zhihao.library.android.util.SystemServiceUtil

/**

 * @author: zhihaofans

 * @date: 2019-01-04 13:02

 */
fun PackageInfo.getAppName(): String = this.applicationInfo.loadLabel(SystemServiceUtil.getPackageManager()).toString()

fun PackageInfo.getAppName(packageName: String): String {
    val packageManager = SystemServiceUtil.getPackageManager()
    return packageManager.getApplicationInfo(
        packageName,
        0
    ).loadLabel(packageManager)
        .toString()
}