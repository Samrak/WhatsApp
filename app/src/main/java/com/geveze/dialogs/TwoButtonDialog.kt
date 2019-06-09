package com.geveze.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView

import com.geveze.R
import com.geveze.utilities.Constants


/**
 * Created by sametoztoprak on 26/09/2017.
 */

class TwoButtonDialog(a: AppCompatActivity) : Dialog(a), View.OnClickListener {

    private lateinit var dialogTwoButtonTitle: TextView
    private lateinit var dialogTwoButtonText: TextView
    private lateinit var dialogTwoButtonButtonLeft: Button
    private lateinit var dialogTwoButtonButtonRight: Button

    private val onTwoDialogListener: TwoButtonDialog.OnTwoDialogListener? = null

    interface OnTwoDialogListener {
        fun onLeftClick(twoButtonDialog: TwoButtonDialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_two_button)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogTwoButtonTitle = findViewById<View>(R.id.dialogTwoButtonTitle) as TextView
        dialogTwoButtonText = findViewById<View>(R.id.dialogTwoButtonText) as TextView
        dialogTwoButtonButtonLeft = findViewById<View>(R.id.dialogTwoButtonButtonLeft) as Button
        dialogTwoButtonButtonRight = findViewById<View>(R.id.dialogTwoButtonButtonRight) as Button

        dialogTwoButtonButtonLeft.setOnClickListener(this)
        dialogTwoButtonButtonRight.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        dialogTwoButtonTitle.text = bundle.getString(Constants.DIALOG_TITLE)
        dialogTwoButtonText.text = bundle.getString(Constants.DIALOG_TEXT)
        dialogTwoButtonButtonLeft.text = bundle.getString(Constants.DIALOG_BUTTON_LEFT)
        dialogTwoButtonButtonRight.text = bundle.getString(Constants.DIALOG_BUTTON_RIGHT)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialogTwoButtonButtonLeft -> onTwoDialogListener!!.onLeftClick(this)
            R.id.dialogTwoButtonButtonRight -> dismiss()
            else -> {
            }
        }
        dismiss()
    }

    companion object {

        private var twoButtonDialog: TwoButtonDialog? = null
        private lateinit var bundle: Bundle

        fun getInstance(activity: AppCompatActivity, bundle: Bundle): TwoButtonDialog? {
            TwoButtonDialog.bundle = bundle
            twoButtonDialog = if (twoButtonDialog == null) TwoButtonDialog(activity) else twoButtonDialog
            return twoButtonDialog;
        }
    }
}
