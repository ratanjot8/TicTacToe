package com.example.tictactoe

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.databinding.FinishDialogLayoutBinding

class FinishDialog(context: Context, message: String, mainActivity: MainActivity): Dialog(context) {
    lateinit var binding: FinishDialogLayoutBinding
    var message: String
    var mainActivity: MainActivity

    init {
        this.message = message
        this.mainActivity = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.finish_dialog_layout, null, false)
        setContentView(binding.root)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.messageHereTextview.text = message
        binding.startAgainButton.setOnClickListener {
            mainActivity.restartMatch()
            dismiss()
        }
    }
}