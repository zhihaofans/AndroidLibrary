package io.zhihao.library.android.util

import io.zhihao.library.android.kotlinEx.getBytes
import java.io.File
import java.io.FileOutputStream

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 19:41

 */
class FileUtil {
    companion object {
        // File
        fun getFileSize(path: String): Long {
            return if (path.isEmpty()) -1 else this.getFileSize(File(path))
        }

        fun getFileSize(file: File): Long {
            return if (file.exists() && file.isFile) file.length() else -1
        }

        fun fileSizeLong2string(fileSize: Long): String = this.fileSizeInt2string(fileSize.toInt())
        fun fileSizeInt2string(fileSize: Int): String {
            var result = fileSize.toFloat()
            var times = 0
            while (result >= 1024) {
                result /= 1024
                times++
            }
            val units = mutableListOf("B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB", "BB", "NB", "DB", "CB")
            return if (times >= units.size) "$fileSize B" else "$result ${units[times]}"
        }

        fun getFileName(filePath: String?): String? {
            if (filePath.isNullOrEmpty()) return null
            val lastSep = filePath.lastIndexOf(File.separator)
            return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
        }

        fun deleteFile(filePath: String): Boolean = this.deleteFile(File(filePath))
        fun deleteFile(file: File): Boolean {
            return if (file.exists() && !file.isDirectory) {
                try {
                    file.delete()
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            } else {
                false
            }

        }

        fun saveFile(filePath: String, fileName: String, fileContent: String): Boolean {
            return if (filePath.isEmpty() || fileName.isEmpty() || fileContent.isEmpty()) {
                false
            } else {
                try {
                    if (this.createFolder(filePath)) {
                        FileOutputStream(File(filePath, fileName)).apply {
                            write(fileContent.getBytes())
                            close()
                        }
                        true
                    } else {
                        false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }

            }
        }

        fun getFileSuffix(filePath: String): String {
            val suffix = this.getFileSuffixWithoutDot(filePath)
            return if (suffix.isEmpty()) "" else ".$suffix"
        }

        fun getFileSuffixWithoutDot(filePath: String): String {
            // 无小数点
            val fileName = this.getFileName(filePath)
            return when {
                fileName.isNullOrEmpty() || fileName.indexOf(".") < 0 -> ""
                else -> fileName.split(".").last()
            }
        }

        // Folder
        fun createFolder(filePath: String): Boolean {
            return if (filePath.isEmpty()) {
                false
            } else {
                try {
                    this.createFolder(File(filePath))
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }
        }

        fun createFolder(file: File): Boolean {
            return try {
                if (file.exists()) {
                    file.isDirectory
                } else {
                    file.mkdirs()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}