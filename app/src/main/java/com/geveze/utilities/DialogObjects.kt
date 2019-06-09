package com.geveze.utilities

import android.content.Context
import android.os.Bundle


import com.geveze.R

/**
 * Created by sametoztoprak on 02/10/2017.
 */

class DialogObjects(context: Context) {

    init {
        DialogObjects.context = context
    }

    private fun getStr(str: Int): String {
        return context.getString(str)
    }

    fun getDialogWarningBundle(message: String): Bundle {
        dialogWarningBundle = if (dialogWarningBundle == null) Bundle() else dialogWarningBundle
        dialogWarningBundle!!.putInt(Constants.DIALOG_ICON, R.drawable.ic_warning)
        dialogWarningBundle!!.putString(Constants.DIALOG_TITLE, getStr(R.string.warning))
        dialogWarningBundle!!.putString(Constants.DIALOG_TEXT, message)
        dialogWarningBundle!!.putString(Constants.DIALOG_BUTTON_SINGLE, getStr(R.string.okay))

        return dialogWarningBundle as Bundle
    }
/*
    fun getFinishExamDialog(): Bundle {
        doubleButtonDialog = if (doubleButtonDialog == null) Bundle() else doubleButtonDialog
        doubleButtonDialog!!.putInt(Constants.DIALOG_ICON, R.drawable.ic_done)
        doubleButtonDialog!!.putString(Constants.DIALOG_TITLE,getStr(R.string.dialog_finish_exam_title))
        doubleButtonDialog!!.putString(Constants.DIALOG_TEXT,getStr(R.string.dialog_finish_exam_text))
        doubleButtonDialog!!.putString(Constants.DIALOG_BUTTON_LEFT,getStr(R.string.dialog_finish_exam_left))
        doubleButtonDialog!!.putString(Constants.DIALOG_BUTTON_RIGHT,getStr(R.string.dialog_finish_exam_right))
        return doubleButtonDialog as Bundle
    }*/

    companion object {
        private var dialogObjects: DialogObjects? = null
        private var dialogWarningBundle: Bundle? = null
        private var doubleButtonDialog: Bundle? = null
        private lateinit var context: Context

        fun getInstance(context: Context): DialogObjects {

            return if (dialogObjects == null)
                DialogObjects(context)
            else
                dialogObjects as DialogObjects
        }
    }
}
