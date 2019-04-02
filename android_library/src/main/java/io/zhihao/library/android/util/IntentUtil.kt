package io.zhihao.library.android.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 19:23

 */

class IntentUtil {
    companion object {

        fun getChooseDirIntent(): Intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        fun getChooseFileIntent(fileType: String = "*/*"): Intent = Intent(Intent.ACTION_PICK).apply { type = fileType }
        fun getChooseImageFileIntent(): Intent = getChooseFileIntent("image/*")

        fun getSaveFileByDocumentIntent(fileName: String, mimeType: String): Intent {
            return Intent(Intent.ACTION_CREATE_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).apply {
                type = mimeType
                putExtra(Intent.EXTRA_TITLE, fileName)
            }
        }

        fun getOpenImageFileIntent(context: Context, file: File): Intent {
            val intent = Intent("android.intent.action.VIEW")
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
            intent.setDataAndType(contentUri, "image/*")
            return intent
        }

    }
}