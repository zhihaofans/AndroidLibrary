package io.zhihao.library.android.kotlinEx

import android.content.Intent
import io.zhihao.library.android.util.SystemServiceUtil


/**
 * Created by zhihaofans on 2018/12/8.
 */
fun Intent?.isNull() = this == null

fun Intent.getPackageName(): String? {
    val packageManager = SystemServiceUtil.getPackageManager()
    val packages = packageManager.queryIntentActivities(this, 0)
    for (res in packages) {
        return res.activityInfo.packageName
    }
    return null
}

val Intent.isActionSend: Boolean
    get() = this.action == Intent.ACTION_SEND
val Intent.isActionView: Boolean
    get() = this.action == Intent.ACTION_VIEW
val Intent.isTypeTextPlain: Boolean
    get() {
        return if (this.isActionSend) {
            this.type == "text/plain"
        } else {
            false
        }
    }

val Intent.isActionProcessText: Boolean
    get() = this.action == Intent.ACTION_PROCESS_TEXT
fun Intent.getTextPlain(): String? {
    return if (this.isTypeTextPlain) {
        this.getStringExtra(Intent.EXTRA_TEXT)
    } else {
        null
    }
}

fun Intent.getProcessText(): String? {
    return if (this.isActionProcessText) {
        this.getStringExtra(Intent.EXTRA_PROCESS_TEXT)
    } else {
        null
    }
}