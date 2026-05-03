package io.zhihao.library.android.util

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import io.zhihao.library.android.ZLibrary

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:27

 */
object ClipboardUtil {
    private val clipManager = SystemServiceUtil.getClipboardManager()
    private fun isAvailable(): Boolean {
        return clipManager.hasPrimaryClip() &&
                clipManager.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN) == true
    }

    fun copy(text: String) {
        clipManager.setPrimaryClip(
            ClipData.newPlainText(
                ZLibrary.getAppContext().packageName + ".text",
                text
            )
        )
    }

    fun paste(): String? {
        if (!isAvailable()) return null

        val pasteData = clipManager.primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()

        return pasteData?.takeIf { it.isNotBlank() }
    }
}
