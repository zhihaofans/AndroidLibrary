package io.zhihao.library.android.util

import android.content.Context
import android.widget.Toast

class ToastUtil(context: Context) {
    private val _context = context
    fun showShortToast(text: String) {
        Toast.makeText(_context, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(text: String) {
        Toast.makeText(_context, text, Toast.LENGTH_LONG).show()
    }
}