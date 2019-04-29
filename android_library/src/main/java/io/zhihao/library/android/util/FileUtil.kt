package io.zhihao.library.android.util

import android.graphics.Bitmap
import android.os.Environment
import io.zhihao.library.android.kotlinEx.getBytes
import io.zhihao.library.android.kotlinEx.remove
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
        fun isFileExists(filePath: String): Boolean {
            return this.isFileExists(this.getFileByPath(filePath))
        }

        fun isFileExists(file: File?): Boolean {
            return file?.exists() ?: false
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

        fun getFileSize(path: String): Long {
            return if (path.isEmpty()) -1 else this.getFileSize(File(path))
        }

        fun getFileSize(file: File): Long {
            return if (file.exists() && file.isFile) file.length() else -1
        }


        fun getFileName(filePath: String?): String? {
            if (filePath.isNullOrEmpty()) return null
            val lastSep = filePath.lastIndexOf(File.separator)
            return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
        }

        fun getFileByPath(filePath: String): File? {
            return if (filePath.isEmpty()) {
                null
            } else {
                File(filePath)
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

        fun saveFile(filePath: String, content: String): Boolean {
            return try {
                val fileName = this.getFileName(filePath) ?: return false
                val saveTo = filePath.remove(fileName)
                this.saveFile(saveTo, fileName, content)
            } catch (e: Exception) {
                e.printStackTrace()
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

        fun saveImageJPEG(bitmap: Bitmap, filePath: String): Boolean {
            return this.saveImage(bitmap, filePath, Bitmap.CompressFormat.JPEG)
        }

        fun saveImageWEBP(bitmap: Bitmap, filePath: String): Boolean {
            return this.saveImage(bitmap, filePath, Bitmap.CompressFormat.WEBP)
        }

        fun saveImagePng(bitmap: Bitmap, filePath: String): Boolean {
            return this.saveImage(bitmap, filePath, Bitmap.CompressFormat.PNG)
        }

        fun saveImage(bitmap: Bitmap, filePath: String, imageFormat: Bitmap.CompressFormat): Boolean {
            File(filePath).apply {
                if (!FileUtil.createFolder(this.parent)) return false
            }
            return bitmap.compress(imageFormat, 100, FileOutputStream(filePath))
                ?: false
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

        fun getPicturePath(): File {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        }

        fun getPicturePathString(): String {
            val p = getPicturePath().path
            return if (p.endsWith("/")) p else "$p/"
        }

        fun getDownloadPath(): File {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        }

        fun getDownloadPathString(): String {
            val p = getDownloadPath().path
            return if (p.endsWith("/")) p else "$p/"
        }

    }
}