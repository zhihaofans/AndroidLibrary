package io.zhihao.library.android.util

import android.content.Intent
import androidx.core.content.FileProvider
import io.zhihao.library.android.ZLibrary
import java.io.File


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 19:23

 */

class IntentUtil {
    companion object {

        fun getIntent(intent: Intent, action: String? = null): Intent {
            return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        fun getDefaultIntent(action: String? = null): Intent {
            return Intent(action).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        fun getChooseDirIntent(): Intent {
            return getDefaultIntent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        }

        fun getChooseFileIntent(fileType: String = "*/*"): Intent {
            return getDefaultIntent(Intent.ACTION_PICK).apply {
                type = fileType
            }
        }

        fun getChooseImageFileIntent(): Intent = getChooseFileIntent("image/*")

        fun getSaveFileByDocumentIntent(fileName: String, mimeType: String): Intent {
            return getDefaultIntent(Intent.ACTION_CREATE_DOCUMENT).apply {
                type = mimeType
                addCategory(Intent.CATEGORY_OPENABLE)
                putExtra(Intent.EXTRA_TITLE, fileName)
            }
        }

        fun getOpenImageFileIntent(file: File): Intent {
            val context = ZLibrary.getContext()
            val contentUri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
            return getDefaultIntent("android.intent.action.VIEW").apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(contentUri, "image/*")
            }
        }

        fun isIntentHasAppToLaunch(mIntent: Intent): Boolean {
            return mIntent.resolveActivity(SystemServiceUtil.getPackageManager()) != null
        }

        fun getLaunchAppIntentWithClassName(packageName: String, className: String): Intent? {
            return this.getLaunchAppIntent(packageName)?.apply {
                setClassName(packageName, className)
            }
        }

        fun getLaunchAppIntent(packageName: String): Intent? {
            val intent = SystemServiceUtil.getPackageManager().getLaunchIntentForPackage(packageName) ?: return null
            return getIntent(intent)
        }

    }
}