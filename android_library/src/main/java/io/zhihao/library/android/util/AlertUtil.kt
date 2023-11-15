package io.zhihao.library.android.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType

import android.widget.EditText
import android.widget.LinearLayout


class AlertUtil(context: Context) {
    private val _context = context

    fun showListAlert(
        title: String,
        itemList: Array<String>,
        setCanceledOnTouchOutside: Boolean = true,
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
        dialog.setCanceledOnTouchOutside(setCanceledOnTouchOutside)
        dialog.show()
        return dialog
    }

    fun showInputAlert(
        title: String,
        inputText: String? = "",
        maxLines: Int? = 1,
        onClick: (text: String, dialog: DialogInterface) -> Unit
    ) {
        val builder = AlertDialog.Builder(_context)
        builder.setTitle(title)
        val input = EditText(_context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.maxLines = maxLines ?: 1
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

    fun showLoginAlert(
        title: String,
        message: String = "",
        username: String = "",
        password: String = "",
        onClick: (username: String, password: String) -> Unit
    ) {
        val builder = AlertDialog.Builder(_context)
        builder.setTitle(title)
        builder.setMessage(message)
        val userInput = EditText(_context)
        userInput.hint = "Username"
        userInput.inputType = InputType.TYPE_CLASS_TEXT
        userInput.setText(username)
        val pwInput = EditText(_context)
        pwInput.hint = "Password"
        pwInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwInput.setText(password)
        val layout = LinearLayout(_context)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(userInput)
        layout.addView(pwInput)
        builder.setView(layout)
        builder.setPositiveButton("OK") { dialog, which ->
            onClick.invoke(
                userInput.text.toString(),
                pwInput.text.toString()
            )
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }
}