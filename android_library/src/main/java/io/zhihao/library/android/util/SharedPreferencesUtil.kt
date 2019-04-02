package io.zhihao.library.android.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import io.zhihao.library.android.kotlinEx.isNotNull

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-04-02 16:33

 */
class SharedPreferencesUtil {
    private var sharedPreferences: SharedPreferences? = null
    private var mContext: Context? = null
    private val sharedPreferencesFileName = "android_box"
    fun init(context: Context): SharedPreferences {
        this@SharedPreferencesUtil.mContext = context
        sharedPreferences = mContext!!.getSharedPreferences(sharedPreferencesFileName, MODE_PRIVATE)
        return sharedPreferences!!
    }

    fun putString(key: String, value: String): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences!!.edit {
            putString(key, value)
        }
        return this.getString(key, null).isNotNull()
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences!!.edit {
            putBoolean(key, value)
        }
        return this.getBoolean(key, !value) == value
    }

    fun putInt(key: String, value: Int): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences!!.edit {
            putInt(key, value)
        }
        return true
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences!!.getString(key, defaultValue)
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences!!.getBoolean(key, defaultValue)
        }
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences!!.getInt(key, defaultValue)
        }
    }
}

class SharedPreferencesNewUtil(context: Context, sharedPreferencesFileName: String) {
    private val sharedPreferences: SharedPreferences? =
        context.getSharedPreferences(sharedPreferencesFileName, MODE_PRIVATE)

    fun putString(key: String, value: String): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences.edit {
            putString(key, value)
        }
        return this.getString(key, null).isNotNull()
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences.edit {
            putBoolean(key, value)
        }
        return this.getBoolean(key, !value) == value
    }

    fun putInt(key: String, value: Int): Boolean {
        if (sharedPreferences == null) return false
        sharedPreferences.edit {
            putInt(key, value)
        }
        return true
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences.getString(key, defaultValue)
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences.getBoolean(key, defaultValue)
        }
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return if (sharedPreferences == null) {
            null
        } else {
            sharedPreferences.getInt(key, defaultValue)
        }
    }
}