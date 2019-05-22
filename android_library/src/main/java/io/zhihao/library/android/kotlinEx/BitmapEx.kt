package io.zhihao.library.android.kotlinEx

import android.graphics.Bitmap
import io.zhihao.library.android.util.EncodeUtil

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-05-16 22:00

 */
fun Bitmap.toBase64String(): String? = EncodeUtil.bitmapToBase64String(this)