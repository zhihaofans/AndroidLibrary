package io.zhihao.library.android.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import io.zhihao.library.android.kotlinEx.getBytes
import io.zhihao.library.android.kotlinEx.remove
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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
            val units = mutableListOf(
                "B",
                "KB",
                "MB",
                "GB",
                "TB",
                "PB",
                "EB",
                "ZB",
                "YB",
                "BB",
                "NB",
                "DB",
                "CB"
            )
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


        @SuppressLint("NewApi")
        fun saveImageWEBP(bitmap: Bitmap, filePath: String): Boolean {
            return if (AndroidUtil.isMoreThenSDKR()) {
                this.saveImage(bitmap, filePath, Bitmap.CompressFormat.WEBP_LOSSLESS)
            } else {
                this.saveImage(bitmap, filePath, Bitmap.CompressFormat.WEBP)
            }
        }

        fun saveImagePng(bitmap: Bitmap, filePath: String): Boolean {
            return this.saveImage(bitmap, filePath, Bitmap.CompressFormat.PNG)
        }

        fun saveImage(
            bitmap: Bitmap,
            filePath: String,
            imageFormat: Bitmap.CompressFormat
        ): Boolean {
            File(filePath).apply {
                if (!createFolder(this.parent)) return false
            }
            return bitmap.compress(imageFormat, 100, FileOutputStream(filePath))
        }

        // Folder/Dir
        fun createDir(filePath: String) = createFolder(filePath)
        fun createDir(file: File) = createFolder(file)
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
                    false
                } else {
                    file.mkdirs()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        /**
         * 判断目录是否存在, 不存在则判断是否创建成功
         * @param dirPath 目录路径
         * @return `true` 存在或创建成功, `false` 不存在或创建失败
         */
        fun createOrExistsDir(dirPath: String): Boolean {
            return createOrExistsDir(getFileByPath(dirPath))
        }

        /**
         * 判断目录是否存在, 不存在则判断是否创建成功
         * @param file 文件
         * @return `true` 存在或创建成功, `false` 不存在或创建失败
         */
        fun createOrExistsDir(file: File?): Boolean {
            // 如果存在, 是目录则返回 true, 是文件则返回 false, 不存在则返回是否创建成功
            return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
        }

        /**
         * 判断文件是否存在, 存在则在创建之前删除
         * @param file 文件
         * @return `true` 创建成功, `false` 创建失败
         */
        fun createFileByDeleteOldFile(file: File?): Boolean {
            if (file == null) return false
            // 文件存在并且删除失败返回 false
            if (file.exists() && !file.delete()) return false
            // 创建目录失败返回 false
            return if (!createOrExistsDir(file.parentFile)) false else try {
                file.createNewFile()
            } catch (e: IOException) {
                Log.e("FileUtil.createFileByDeleteOldFile", e.message.toString())
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