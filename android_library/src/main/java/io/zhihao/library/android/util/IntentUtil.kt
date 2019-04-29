package io.zhihao.library.android.util

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
        fun isIntentAvailable(mIntent: Intent): Boolean {
            return SystemServiceUtil.getPackageManager().queryIntentActivities(
                mIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ).isNotEmpty()
        }

        fun isIntentHasAppToLaunch(mIntent: Intent): Boolean {
            return mIntent.resolveActivity(SystemServiceUtil.getPackageManager()) != null
        }

        fun getIntent(intent: Intent, isNewTask: Boolean = false): Intent {
            return intent.apply {
                if (isNewTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
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
            val context = ZLibrary.getAppContext()
            val contentUri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
            return getDefaultIntent("android.intent.action.VIEW").apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(contentUri, "image/*")
            }
        }


        fun getLaunchAppIntentWithClassName(packageName: String, className: String): Intent? {
            return (this.getLaunchAppIntent(packageName) ?: return null).apply {
                setClassName(packageName, className)
            }
        }

        fun getLaunchAppIntent(packageName: String): Intent? {
            return if (AppUtil.isAppInstalled(packageName)) {
                getIntent(
                    SystemServiceUtil.getPackageManager().getLaunchIntentForPackage(packageName) ?: return null,
                    true
                )
            } else {
                null
            }
        }

        fun getInstallAppIntent(file: File, authority: String, isNewTask: Boolean): Intent? {
            return if (FileUtil.isFileExists(file)) {
                try {
                    val intentData = FileProvider.getUriForFile(ZLibrary.getAppContext(), authority, file)
                    val mIntent = getIntent(Intent(Intent.ACTION_VIEW).apply {
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        setDataAndType(intentData, "application/vnd.android.package-archive")
                    }, isNewTask)
                    mIntent
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } else {
                null
            }
        }

        fun getUninstallAppIntent(packageName: String, isNewTask: Boolean): Intent? {
            if (!AppUtil.isAppInstalled(packageName)) return null
            return getIntent(Intent(Intent.ACTION_DELETE).apply {
                data = Uri.parse("package:$packageName")
            }, isNewTask)
        }

        fun getNotifyMediaStoreIntent(file: File): Intent {
            return Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file))
        }
    }
}