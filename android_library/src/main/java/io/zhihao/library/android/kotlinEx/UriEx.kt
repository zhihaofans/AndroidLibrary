package io.zhihao.library.android.kotlinEx

import android.net.Uri
import java.net.URL

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:16

 */

fun Uri.isDownloadsDocument(): Boolean {
    return this.authority == "com.android.providers.downloads.documents" || this.authority == "com.android.providers.downloads.documents"
}

fun Uri?.toURL(): URL? {
    return if (this == null) null else URL(this.toString())
}