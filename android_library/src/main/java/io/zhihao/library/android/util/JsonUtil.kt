package io.zhihao.library.android.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-06-28 14:52

 */
class JsonUtil {
    companion object {
        fun getMapFromJson(jsonString: String): Map<Any, Any> {
            return Gson().fromJson(jsonString, object : TypeToken<Map<Any, Any>>() {}.type)
        }
    }
}