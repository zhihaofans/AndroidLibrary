package io.zhihao.library.android.util

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import io.zhihao.library.android.ZLibrary

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:27

 */
class ClipboardUtil {
    companion object {
        private val clipManager = SystemServiceUtil.getClipboardManager()
        private fun isAvailable(mimeType: String): Boolean {
            return clipManager.hasPrimaryClip() || clipManager.primaryClipDescription != null || clipManager.primaryClipDescription!!.hasMimeType(
                mimeType
            )
        }

        fun copy(text: String) {
            clipManager.primaryClip = ClipData.newPlainText(ZLibrary.getContext().packageName + ".text", text)
        }

        fun paste(): String? {
            if (this.isAvailable(MIMETYPE_TEXT_PLAIN)) return null
            val pasteData = clipManager.primaryClip!!.getItemAt(0).text
            if (pasteData.isNullOrEmpty()) return null
            return pasteData.toString()
        }
    }
}