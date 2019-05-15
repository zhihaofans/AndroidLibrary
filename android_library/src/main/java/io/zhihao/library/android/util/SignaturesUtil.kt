package io.zhihao.library.android.util

import android.annotation.SuppressLint
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-05-15 23:03

 */
class SignaturesUtil {
    companion object {
        private fun toHex(byteArray: ByteArray): String {
            //转成16进制后是32字节
            return with(StringBuilder()) {
                byteArray.forEach {
                    val hex = it.toInt() and (0xFF)
                    val hexStr = Integer.toHexString(hex)
                    if (hexStr.length == 1) {
                        append("0").append(hexStr)
                    } else {
                        append(hexStr)
                    }
                }
                toString()
            }
        }

        // MD5
        fun getMD5(string: String): String? {
            return try {
                this.toHex(MessageDigest.getInstance("MD5").digest(string.toByteArray()))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        // SHA
        fun getSHA(string: String, algorithm: String): String? {

            return try {
                this.toHex(MessageDigest.getInstance(algorithm).digest(string.toByteArray()))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun getSHA1(string: String): String? {
            return getSHA(string, "SHA-1")

        }

        fun getSHA2(string: String): String? {
            return getSHA(string, "SHA-2")

        }

        fun getSHA3(string: String): String? {
            return getSHA(string, "SHA-3")
        }

        fun getSHA4(string: String): String? {
            return getSHA(string, "SHA-4")
        }

        fun getSHA224(string: String): String? {
            return getSHA(string, "SHA-224")
        }

        fun getSHA256(string: String): String? {
            return getSHA(string, "SHA-256")
        }

        fun getSHA384(string: String): String? {
            return getSHA(string, "SHA-384")
        }

        fun getSHA512(string: String): String? {
            return getSHA(string, "SHA-512")
        }

        // DES
        fun encryptDES(string: String, key: String): String? =
            this.encryptDES(string.toByteArray(), key.toByteArray()).toString()

        @SuppressLint("GetInstance")
        fun encryptDES(string: ByteArray, key: ByteArray): ByteArray? {
            return if (string.isEmpty() || key.isEmpty()) {
                null
            } else {
                try {
                    Cipher.getInstance("DES/ECB/PKCS5Padding").apply {
                        init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "DES"))
                    }.doFinal(string)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }

        fun decryptDES(string: String, key: String): String? =
            this.decryptDES(string.toByteArray(), key.toByteArray()).toString()

        @SuppressLint("GetInstance")
        fun decryptDES(string: ByteArray, key: ByteArray): ByteArray? {
            return if (string.isEmpty() || key.isEmpty()) {
                null
            } else {
                try {
                    Cipher.getInstance("DES/ECB/PKCS5Padding").apply {
                        init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "DES"))
                    }.doFinal(string)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }
}