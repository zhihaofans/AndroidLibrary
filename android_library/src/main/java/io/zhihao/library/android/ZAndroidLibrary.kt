package io.zhihao.library.android

import android.annotation.SuppressLint
import android.content.Context


/**
 * Created by zhihaofans on 2019/4/4.
 */
class ZAndroidLibrary {

    @SuppressLint("StaticFieldLeak")
    private var mContext: Context? = null

    fun init(context: Context) {
        this.mContext = context.applicationContext
    }

    fun getContext(): Context? {
        if (mContext == null) {
            throw  NullPointerException("请先调用init()方法")
        } else {
            return mContext
        }
    }
}