package io.zhihao.library.android.kotlinEx

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.zhihao.library.android.util.SystemServiceUtil

/**
 * Created by zhihaofans on 2018/11/4.
 */
fun Activity.snackbar(view: View, message: Int) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

fun Activity.snackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
fun Activity.longSnackbar(view: View, message: Int) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
fun Activity.longSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
fun Activity.closeKeyboard() {
    SystemServiceUtil.getInputMethodManager().hideSoftInputFromWindow(
        this.window.decorView.windowToken,
        0
    )
}