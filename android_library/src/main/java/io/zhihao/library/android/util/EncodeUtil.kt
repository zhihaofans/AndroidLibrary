package io.zhihao.library.android.util

import android.graphics.Bitmap
import android.util.Base64
import io.zhihao.library.android.kotlinEx.toIntNew
import java.io.ByteArrayOutputStream
import java.net.URLDecoder
import java.net.URLEncoder
/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-05-15 22:35

 */
class EncodeUtil {
    // 参考:https://github.com/afkT/DevUtils/blob/master/DevLibUtils/src/main/java/dev/utils/app/EncodeUtils.java
    companion object {
        // 半角/全角
        fun toDBC(string: String): String? {
            // 半角
            return if (string.isEmpty()) {
                ""
            } else {
                try {
                    val chars = string.toCharArray()
                    var i = 0
                    while (i < chars.size) {
                        when {
                            chars[i].toIntNew() == 12288 -> chars[i] = ' '
                            chars[i].toIntNew() in 65281..65374 -> chars[i] = (chars[i].toIntNew() - 65248).toChar()
                            else -> chars[i] = chars[i]
                        }
                        i++
                    }
                    String(chars)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun toSBC(string: String): String? {
            // 全角
            return if (string.isEmpty()) {
                ""
            } else {
                try {
                    val chars = string.toCharArray()
                    var i = 0
                    while (i < chars.size) {
                        when {
                            chars[i] == ' ' -> chars[i] = 12288.toChar()
                            chars[i].toIntNew() in 33..126 -> chars[i] = (chars[i].toIntNew() + 65248).toChar()
                            else -> chars[i] = chars[i]
                        }
                        i++
                    }
                    String(chars)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        // Unicode
        fun toUnicode(string: String): String? {
            return if (string.isEmpty()) {
                ""
            } else {
                try {
                    StringBuffer().apply {
                        string.toCharArray().map {
                            append("\\u").append(Integer.toHexString(it.toIntNew()))
                        }
                    }.toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        // Url encode
        fun urlEncode(url: String, charsetName: String = "UTF-8"): String? {
            return if (url.isEmpty()) {
                ""
            } else {
                try {
                    URLEncoder.encode(url, charsetName)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun urlDecode(url: String, charsetName: String = "UTF-8"): String? {
            return if (url.isEmpty()) {
                ""
            } else {
                try {
                    URLDecoder.decode(url, charsetName)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        // Base64 encode
        fun base64Encode(input: ByteArray): ByteArray? {
            return if (input.isEmpty()) {
                null
            } else {
                try {
                    Base64.encode(input, Base64.NO_WRAP)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun base64Encode(input: String): String? {
            return if (input.isEmpty()) {
                null
            } else {
                try {
                    Base64.encodeToString(input.toByteArray(), Base64.NO_WRAP)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun base64Decode(input: ByteArray): ByteArray? {
            return if (input.isEmpty()) {
                null
            } else {
                try {
                    Base64.decode(input, Base64.NO_WRAP)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun base64Decode(input: String): String? {
            return if (input.isEmpty()) {
                null
            } else {
                try {
                    Base64.decode(input.toByteArray(), Base64.NO_WRAP).apply { if (this.isEmpty()) return null }
                        .toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun bitmapToBase64String(bitmap: Bitmap): String? {
            return try {
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()
                Base64.encodeToString(byteArray, Base64.DEFAULT)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}