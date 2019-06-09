package com.geveze.components

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.geveze.R
import android.text.SpannableString
import android.view.View


/**
 * Created by sametoztoprak on 15.02.2018.
 */
class SpinnerCustom : LinearLayout, View.OnClickListener {

    private lateinit var spinnerValue: TextViewCustom
    private lateinit var spinnerLabel: TextViewCustom
    private lateinit var layoutSpinner: LinearLayout
    private lateinit var mContext: Context

    private lateinit var label: String

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context!!

        val v = LayoutInflater.from(context).inflate(R.layout.custom_spinner, this, true);
        if (!isInEditMode()) {
            val array = context!!.obtainStyledAttributes(attrs, R.styleable.SpinnerCustom, 0, 0)
            label = array.getString(R.styleable.SpinnerCustom_label);
            val enable = array.getBoolean(R.styleable.SpinnerCustom_enabled, true);

            layoutSpinner = v.findViewById<LinearLayout>(R.id.layoutSpinner)

            spinnerLabel = v.findViewById<TextViewCustom>(R.id.spinnerLabel)
            spinnerLabel.setText(label);

            spinnerValue = v.findViewById<TextViewCustom>(R.id.spinnerValue)
            v.isEnabled = enable
            v.isClickable = enable;
            array.recycle();

            spinnerValue.setOnClickListener(this)
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, 0) {
    }

    fun setText(text: SpannableString) {
        spinnerValue.text = text
    }

    override fun setEnabled(enable: Boolean) {
        spinnerLabel.isEnabled = enable
        spinnerValue.isEnabled = enable
        layoutSpinner.isEnabled = !enable
        layoutSpinner.isEnabled = !enable
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.spinnerValue -> {
                val intent = Intent(mContext, SpinnerListActivity::class.java)
                intent.putExtra("label", label)
                mContext.startActivity(intent)
            }
        }
    }
}