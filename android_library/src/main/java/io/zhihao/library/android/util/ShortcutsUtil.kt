package io.zhihao.library.android.util

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.graphics.drawable.toBitmap
import io.zhihao.library.android.ZLibrary
import io.zhihao.library.android.kotlinEx.appName
import io.zhihao.library.android.kotlinEx.getAppIcon
import io.zhihao.library.android.kotlinEx.isNotNullAndEmpty

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:48

 */

class ShortcutsUtil {
    private val mContext = ZLibrary.getAppContext()
    private val shortcutManager = mContext.getSystemService(ShortcutManager::class.java)
    fun getCount(): Int {
        return getAllShortcuts().size
    }

    fun getMaxCount(): Int {
        return ShortcutManagerCompat.getMaxShortcutCountPerActivity(mContext)
    }

    fun hasFullShortcuts(): Boolean {
        return getCount() >= getMaxCount()
    }

    fun getAllShortcuts(): List<ShortcutInfoCompat> {
        return ShortcutManagerCompat.getDynamicShortcuts(mContext)
    }

    fun hasShortcut(id: String): Boolean {
        return getAllShortcuts().any { it.id == id }
    }

    fun pushShortcut(id: String, intent: Intent, shortcutName: String, icon: IconCompat) {
        if (id.isNotNullAndEmpty()) {
            val shortcut =
                ShortcutInfoCompat.Builder(mContext, id)
                    .setShortLabel(shortcutName)
//                    .setLongLabel(appInfo.appName)
                    .setIcon(icon)
                    .setIntent(intent)
                    .build()
            ShortcutManagerCompat.pushDynamicShortcut(mContext, shortcut)
        }
    }

    fun removeShortcut(shortCutId: String) {
        ShortcutManagerCompat.removeDynamicShortcuts(
            mContext, listOf(shortCutId)
        )
    }

    fun addPinShortcut(id: String, intent: Intent, shortcutName: String, icon: Icon): Boolean {
        return if (shortcutManager.isRequestPinShortcutSupported) {
            intent.action = Intent.ACTION_VIEW //action必须设置，不然报错
            val pinShortcutInfo = ShortcutInfo.Builder(mContext, id)
                .setIcon(icon)
                .setShortLabel(shortcutName)
                .setIntent(intent)
                .build()
            val pinnedShortcutCallbackIntent =
                shortcutManager.createShortcutResultIntent(pinShortcutInfo)
            val successCallback = PendingIntent.getBroadcast(
                mContext, 0, pinnedShortcutCallbackIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
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