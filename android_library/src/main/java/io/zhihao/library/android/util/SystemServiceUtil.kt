package io.zhihao.library.android.util

import android.app.DownloadManager
import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
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
        private fun getSystemService(serviceName: String): Any? {
            return ZLibrary.getAppContext().getSystemService(serviceName)
        }

        fun getPackageManager(): PackageManager {
            return ZLibrary.getAppContext().packageManager
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

        fun getConnectivityManager(): ConnectivityManager {
            return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

        fun getWifiManager(): WifiManager {
            return getSystemService(Context.WIFI_SERVICE) as WifiManager
        }
    }
}