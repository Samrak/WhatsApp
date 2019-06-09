package com.geveze.components

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet

import com.geveze.R
import com.geveze.utilities.CApplication


/**
 * Created by sametoztoprak on 17/09/2017.
 */

class EditTextCustom : AppCompatEditText {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (!isInEditMode) {

            val a = getContext().obtainStyledAttributes(attrs, R.styleable.attribute)
            val fontId = a.getInt(R.styleable.attribute_font, resources.getInteger(R.integer.font_Light))
            a.recycle()
            setTypeface(context, fontId)
        }
    }

    private fun setTypeface(context: Context, fontId: Int) {
        this.typeface = (context.applicationContext as CApplication).getFont(fontId)
    }
}
