package com.example.gonistudy.Alert.Custom

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.gonistudy.R

class CustomDialog(context:Context) {

    private val dialog = Dialog(context)
    private lateinit var onClickListener : OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener){
        onClickListener = listener
    }

    fun showDialog(){
        dialog.setContentView(R.layout.item_custom_alert_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val editName = dialog.findViewById<EditText>(R.id.custom_alert_edit_text)
        val OKButton = dialog.findViewById<Button>(R.id.custom_alert_ok_button)
        val NOButton = dialog.findViewById<Button>(R.id.custom_alert_no_button)

        NOButton.setOnClickListener {
            dialog.dismiss()
        }

        OKButton.setOnClickListener {
            onClickListener.onClicked(editName.text.toString())
            dialog.dismiss()
        }
    }

    interface OnDialogClickListener{
        fun onClicked(name: String)
    }

}