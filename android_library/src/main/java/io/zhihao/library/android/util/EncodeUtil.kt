package io.zhihao.library.android.util

import android.util.Base64
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
        //Url encode
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
    }
}