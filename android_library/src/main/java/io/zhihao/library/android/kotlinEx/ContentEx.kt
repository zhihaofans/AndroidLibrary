package io.zhihao.library.android.kotlinEx

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.startActivity(clazz: Class<*>) {
    val intent = Intent(this, clazz)
    this.startActivity(intent)
}
inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}