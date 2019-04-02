package io.zhihao.library.android.util

import android.app.DownloadManager
import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 20:10

 */
class SystemServiceUtil {
    companion object {
        fun getDownloadManager(context: Context): DownloadManager {
            return context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        }

        fun getNotificationManager(context: Context): NotificationManager {
            return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        fun getClipboardManager(context: Context): ClipboardManager {
            return context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        }
    }
}