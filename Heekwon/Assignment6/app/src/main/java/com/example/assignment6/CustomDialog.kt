package com.example.assignment6

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment6.databinding.CustomDialogBinding


class CustomDialog(private val context : AppCompatActivity) {

    private lateinit var binding : CustomDialogBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = CustomDialogBinding.inflate(context.layoutInflater)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        binding.ok.setOnClickListener {

            onClickedListener.onClicked(binding.content.text.toString())

            dialog.dismiss()
        }

        binding.cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(myName: String)
    }

    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }
}