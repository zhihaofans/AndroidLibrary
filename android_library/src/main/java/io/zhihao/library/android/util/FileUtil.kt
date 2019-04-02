package io.zhihao.library.android.util

import java.io.File

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 19:41

 */
class FileUtil {
    companion object {

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
    }
}