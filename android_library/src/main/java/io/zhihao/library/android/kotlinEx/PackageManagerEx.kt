package io.zhihao.library.android.kotlinEx

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build


fun PackageManager.getPackageInfoCompat(
    packageName: String
): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(
            packageName,
            PackageManager.PackageInfoFlags.of(0)
        )
    } else {
//        @Suppress("DEPRECATION")
        getPackageInfo(packageName, 0)
    }
}