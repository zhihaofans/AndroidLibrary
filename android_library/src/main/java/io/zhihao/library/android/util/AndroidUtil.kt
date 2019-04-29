package io.zhihao.library.android.util

import io.zhihao.library.android.ZLibrary
import java.io.File

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-29 18:32

 */
class AndroidUtil {
    companion object {
        fun notifyMediaStore(file: File): Boolean {
            return if (file.isFile) {
                try {
                    ZLibrary.getAppContext().sendBroadcast(IntentUtil.getNotifyMediaStoreIntent(file))
                    true
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            } else {
                false
            }
        }
    }
}