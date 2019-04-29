package io.zhihao.library.android.util

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import io.zhihao.library.android.ZLibrary
import io.zhihao.library.android.kotlinEx.isNotNull

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:33

 */
class SharedPreferencesUtil(sharedPreferencesFileName: String) {
    private val mSharedPreferences: SharedPreferences? =
        ZLibrary.getAppContext().getSharedPreferences(sharedPreferencesFileName, MODE_PRIVATE)

    fun putString(key: String, value: String): Boolean {
        if (mSharedPreferences == null) return false
        mSharedPreferences.edit {
            putString(key, value)
        }
        return this.getString(key, null).isNotNull()
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        if (mSharedPreferences == null) return false
        mSharedPreferences.edit {
            putBoolean(key, value)
        }
        return this.getBoolean(key, !value) == value
    }

    fun putInt(key: String, value: Int): Boolean {
        if (mSharedPreferences == null) return false
        mSharedPreferences.edit {
            putInt(key, value)
        }
        return true
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return mSharedPreferences?.getString(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        return mSharedPreferences?.getBoolean(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return mSharedPreferences?.getInt(key, defaultValue)
    }
}