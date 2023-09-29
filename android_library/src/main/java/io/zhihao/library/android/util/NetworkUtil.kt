package io.zhihao.library.android.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresPermission
import io.zhihao.library.android.ZLibrary


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-16 16:38

 */
class NetworkUtil {
    // TODO: NetworkInfo在Android SDK Q被弃用，寻找替代品。https://developer.android.com/reference/android/net/NetworkInfo
    companion object {
        private val connectivityManager = ZLibrary.getAppContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        private fun getNetWork(): Network? {
            return SystemServiceUtil.getConnectivityManager().activeNetwork
        }


        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        @Deprecated("NetworkInfo was deprecated in API level 29.",
            ReplaceWith("SystemServiceUtil.getConnectivityManager().activeNetworkInfo")
        )
        private fun getNetWorkInfo(): NetworkInfo? {
            return SystemServiceUtil.getConnectivityManager().activeNetworkInfo
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isConnected(): Boolean {
            return try {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    //WIFI
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    //移动蜂窝数据
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //有线以太网
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //蓝牙网络
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isWifi(): Boolean {
            val connectivityManager = SystemServiceUtil.getConnectivityManager()
            val wifiManager = SystemServiceUtil.getWifiManager()
            return if (wifiManager.isWifiEnabled) {
                val networkCapabilities =
                    connectivityManager.getNetworkCapabilities(this.getNetWork())
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
            } else {
                false
            }
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isMobile(): Boolean {
            val connectivityManager = SystemServiceUtil.getConnectivityManager()
            return connectivityManager.getNetworkCapabilities(this.getNetWork())
                ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isEthernet(): Boolean {
            // 以太网
            val connectivityManager = SystemServiceUtil.getConnectivityManager()
            return connectivityManager.getNetworkCapabilities(this.getNetWork())
                ?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true
        }

    }
}