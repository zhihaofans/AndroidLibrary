package io.zhihao.library.android.kotlinEx

import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission


/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 17:04

 */


fun Context.collapseNotificationBar() {
    //折叠通知栏
    //感谢：https://stackoverflow.com/questions/15568754
    //this.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
}