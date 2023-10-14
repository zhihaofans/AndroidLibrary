package io.zhihao.library.android.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType

import android.widget.EditText


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
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        return dialog
    }

    fun showInputAlert(
        title: String, inputText: String? = "",
        onClick: (text: String, dialog: DialogInterface) -> Unit
    ) {
        val builder = AlertDialog.Builder(_context)
        builder.setTitle(title)
        val input = EditText(_context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(inputText)
        builder.setView(input)
        builder.setPositiveButton("OK") { dialog, which ->
            onClick.invoke(
                input.text.toString(),
                dialog
            )
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }
}