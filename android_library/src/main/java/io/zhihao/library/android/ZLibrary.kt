package io.zhihao.library.android

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.zhihao.library.android.BuildConfig

/**
 * Created by zhihaofans on 2019/4/4.
 */
class ZLibrary {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        fun init(context: Context) {
            this.mContext = context.applicationContext
            Log.d("ZLibrary.init.ver", getZLibraryVersion())
        }


        fun getAppContext(): Context = requireNotNull(mContext) { "请先调用 init() 方法" }

        fun getZLibraryVersion() = BuildConfig.LIB_VERSION
        fun isDebug() = BuildConfig.DEBUG
    }
}