package io.zhihao.library.android

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log


/**
 * Created by zhihaofans on 2019/4/4.
 */
class ZLibrary {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        fun init(context: Context) {
            this.mContext = context.applicationContext
            Log.d("ZLibrary.init", "yes")
        }

        fun getAppContext(): Context {
            if (mContext == null) {
                throw  NullPointerException("请先调用init()方法")
            } else {
                return mContext!!
            }
        }
    }
}