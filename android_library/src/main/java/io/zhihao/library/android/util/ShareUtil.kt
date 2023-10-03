package io.zhihao.library.android.util

import android.content.Context
import android.content.Intent
import io.zhihao.library.android.util.AppUtil.Companion.startActivity

class ShareUtil(context: Context) {
    private val _context = context
    fun shareText(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        _context.startActivity(shareIntent)
    }
}