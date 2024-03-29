package io.zhihao.library.android.util

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import io.zhihao.library.android.ZLibrary

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:48

 */

class ShortcutsUtil {

    private val mContext = ZLibrary.getAppContext()
    private val shortcutManager = mContext.getSystemService(ShortcutManager::class.java)

    fun addPinShortcut(id: String, intent: Intent, shortcutName: String, icon: Icon): Boolean {
        return if (shortcutManager.isRequestPinShortcutSupported) {
            intent.action = Intent.ACTION_VIEW //action必须设置，不然报错
            val pinShortcutInfo = ShortcutInfo.Builder(mContext, id)
                .setIcon(icon)
                .setShortLabel(shortcutName)
                .setIntent(intent)
                .build()
            val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo)
            val successCallback = PendingIntent.getBroadcast(mContext, 0, pinnedShortcutCallbackIntent,
                PendingIntent.FLAG_IMMUTABLE)
            try {
                shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.intentSender)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        } else {
            false
        }
    }
}