package io.zhihao.library.android.util

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import io.zhihao.library.android.ZLibrary

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 20:10

 */
class SystemServiceUtil {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val mContext = ZLibrary.getContext()

        private fun getSystemService(serviceName: String): Any? {
            return mContext.getSystemService(serviceName)
        }

        fun getPackageManager(): PackageManager {
            return mContext.packageManager
        }

        fun getDownloadManager(): DownloadManager {
            return getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        }

        fun getNotificationManager(): NotificationManager {
            return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        fun getClipboardManager(): ClipboardManager {
            return getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        }


        fun getInputMethodManager(): InputMethodManager {
            return getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }

        fun getLayoutInflater(): LayoutInflater {
            return getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
    }
}