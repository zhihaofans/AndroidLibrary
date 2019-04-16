package io.zhihao.library.android.util

import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-16 16:38

 */
class NetworkUtil {
    // TODO: NetworkInfo在Android SDK Q被弃用，寻找替代品。https://developer.android.com/reference/android/net/NetworkInfo
    companion object {
        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        private fun getNetWork(): Network? {
            return SystemServiceUtil.getConnectivityManager().activeNetwork
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        private fun getNetWorkInfo(): NetworkInfo? {
            return SystemServiceUtil.getConnectivityManager().activeNetworkInfo
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isConnected(): Boolean {
            return try {
                val networkInfo = this.getNetWorkInfo()
                networkInfo !== null && networkInfo.isConnected
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
                val networkCapabilities = connectivityManager.getNetworkCapabilities(this.getNetWork())
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } else {
                false
            }
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isMobile(): Boolean {
            val connectivityManager = SystemServiceUtil.getConnectivityManager()
            return connectivityManager.getNetworkCapabilities(this.getNetWork())
                .hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        }

        @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
        fun isEthernet(): Boolean {
            // 以太网
            val connectivityManager = SystemServiceUtil.getConnectivityManager()
            return connectivityManager.getNetworkCapabilities(this.getNetWork())
                .hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }

    }
}