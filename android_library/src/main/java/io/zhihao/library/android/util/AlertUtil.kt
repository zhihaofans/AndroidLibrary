package io.zhihao.library.android.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class AlertUtil(context: Context) {
    private val _context = context
    fun showListAlert(
        title: String,
        itemList: Array<String>,
        onClick: (dialog: DialogInterface, index: Int) -> Unit
    ): AlertDialog {
        val builder = AlertDialog.Builder(_context)
        builder
            .setTitle(title)
//            .setPositiveButton("Positive") { dialog, which ->
//                // Do something.
//            }
//            .setNegativeButton("Negative") { dialog, which ->
//                // Do something else.
//            }
            .setItems(itemList) { dialog, which ->
                // Do something on item tapped.
                onClick.invoke(dialog, which)
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
        return dialog
    }
}