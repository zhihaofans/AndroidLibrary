package io.zhihao.library.android.kotlinEx

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-14 23:40

 */
fun CoordinatorLayout.snackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

fun CoordinatorLayout.snackbar(message: Int) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
fun CoordinatorLayout.longSnackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
fun CoordinatorLayout.longSnackbar(message: Int) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
fun CoordinatorLayout.indefiniteSnackbar(message: String) =
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).show()

fun CoordinatorLayout.indefiniteSnackbar(message: Int) = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).show()