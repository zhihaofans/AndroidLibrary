package io.zhihao.library.android.kotlinEx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

/**
 * Created by zhihaofans on 2018/11/4.
 */
fun Activity.snackbar(view: View, message: Int) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

fun Activity.snackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
fun Activity.longSnackbar(view: View, message: Int) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
fun Activity.longSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
fun Activity.closeKeyboard() {
    (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        this.window.decorView.windowToken,
        0
    )
}