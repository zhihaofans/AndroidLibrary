package io.zhihao.library.android.kotlinEx

import android.R.layout
import android.widget.ArrayAdapter
import android.widget.ListView
import io.zhihao.library.android.ZLibrary

/**
 * Created by zhihaofans on 2018/8/26.
 */
fun ListView.init(listData: List<String>) {
    this.adapter = ArrayAdapter<String>(ZLibrary.getAppContext(), layout.simple_list_item_1, listData)
}

fun ListView.removeAllItems() {
    this.adapter = null
}
