package com.example.gonistudy.ListViewUpgrade

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.gonistudy.R

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog() {
        dialog.setContentView(R.layout.custom_alert_list)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val insertBtn = dialog.findViewById<Button>(R.id.data_insert_btn)
        val deleteBtn = dialog.findViewById<Button>(R.id.data_delete_btn)
        val name = dialog.findViewById<EditText>(R.id.name_custom_edit_text)
        val hobby = dialog.findViewById<EditText>(R.id.hobby_custom_edit_text)
        val age = dialog.findViewById<EditText>(R.id.age_custom_edit_text)
        insertBtn.setOnClickListener {
            onClickListener.onInsertClicked(
                name.text.toString(),
                hobby.text.toString(),
                age.text.toString().toInt()
            )
            dialog.dismiss()
        }
        deleteBtn.setOnClickListener {
            onClickListener.onDeleteClicked()
            dialog.dismiss()
        }
    }

    interface OnDialogClickListener {
        fun onInsertClicked(name: String, hobby: String, age: Int)
        fun onDeleteClicked()
    }
}