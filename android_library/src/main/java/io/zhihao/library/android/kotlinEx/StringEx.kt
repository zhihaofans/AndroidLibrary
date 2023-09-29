package io.zhihao.library.android.kotlinEx

import android.webkit.URLUtil
import io.zhihao.library.android.util.EncodeUtil
import io.zhihao.library.android.util.StringUtil
import java.net.URI
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.Locale


/**
 * Created by zhihaofans on 2018/9/15.
 */

// String
fun String.find(string: String, startIndex: Int = 0, ignoreCase: Boolean = false) =
    this.indexOf(string, startIndex, ignoreCase)

fun String.remove(removeString: String, ignoreCase: Boolean = false) =
    this.replace(removeString, "", ignoreCase)

fun String.replaceByList(list: Map<String, String>, ignoreCase: Boolean = false): String {
    var mStr = this
    list.map {
        mStr = mStr.replace(it.key, it.value, ignoreCase)
    }
    return mStr
}

fun String.toURLEncode() = URLEncoder.encode(this, "UTF-8")

fun String.startsWithList(list: List<String>): Boolean {
    if (list.isNullOrEmpty()) return false
    list.map {
        if (it.isEmpty()) return false
        if (this.startsWith(it)) return true
    }
    return false
}

fun String.endsWithList(list: List<String>): Boolean {
    if (list.isNullOrEmpty()) return false
    list.map {
        if (it.isEmpty()) return false
        if (this.endsWith(it)) return true
    }
    return false
}

fun String.toUrl() = URL(this)

// String?
fun String?.isNotNull() = this != null

fun String?.isNotNullAndEmpty() = !this.isNullOrEmpty()

fun String?.isUrl(): Boolean {
    if (this.isNullOrEmpty()) return false
    return try {
        URLUtil.isValidUrl(this)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun String?.toURI() = URI(this)

fun String?.startWith(prefix: String, ignoreCase: Boolean = false): Boolean {

    return this?.startsWith(prefix, ignoreCase) ?: false
}

fun String?.endWith(prefix: String, ignoreCase: Boolean = false): Boolean {
    return this?.endsWith(prefix, ignoreCase) ?: false
}

fun String.getBytes() = this.toByteArray(Charset.defaultCharset())

fun String.toUnicode() = EncodeUtil.toUnicode(this)

fun String.toSBC() = EncodeUtil.toSBC(this)

fun String.toDBC() = EncodeUtil.toDBC(this)

fun String.isChinese() = StringUtil.isChinese(this)
fun String.toLowerCase() = this.lowercase(Locale.getDefault())