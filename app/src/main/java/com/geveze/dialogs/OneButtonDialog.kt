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
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat


/**
 * Created by sametoztoprak on 26/09/2017.
 */

class OneButtonDialog(activity: AppCompatActivity) : Dialog(activity), View.OnClickListener {

    private lateinit var dialogOneButtonTitle: TextView
    private lateinit var dialogOneButtonText: TextView
    private lateinit var dialogOneButtonOkButton: Button

    private var size: Int = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_one_button)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogOneButtonTitle = findViewById<View>(R.id.dialogOneButtonTitle) as TextView
        dialogOneButtonText = findViewById<View>(R.id.dialogOneButtonText) as TextView
        dialogOneButtonOkButton = findViewById<View>(R.id.dialogOneButtonOkButton) as Button

        dialogOneButtonOkButton.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        dialogOneButtonTitle.text = bundle.getString(Constants.DIALOG_TITLE)
        dialogOneButtonText.text = bundle.getString(Constants.DIALOG_TEXT)
        dialogOneButtonOkButton.text = bundle.getString(Constants.DIALOG_BUTTON_SINGLE)

        var icon = bundle.getInt(Constants.DIALOG_ICON)
        val iconDrawable = ContextCompat.getDrawable(context, icon);
        iconDrawable!!.setBounds(0, 0, size, size);
        dialogOneButtonTitle.setCompoundDrawables(iconDrawable, null, null, null)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialogOneButtonOkButton -> {
            }
            else -> {
            }
        }
        dismiss()
    }

    companion object {

        private var oneButtonDialog: OneButtonDialog? = null
        private lateinit var bundle: Bundle

        fun getInstance(activity: AppCompatActivity, bundle: Bundle): OneButtonDialog? {
            OneButtonDialog.bundle = bundle
            oneButtonDialog = if (oneButtonDialog == null) OneButtonDialog(activity) else oneButtonDialog
            return oneButtonDialog;
        }
    }
}
