package com.geveze.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.geveze.R
import com.geveze.base.BaseAppCompatActivity
import com.geveze.components.TextViewCustom
import com.geveze.utilities.Constants

/**
 * Created by sametoztoprak on 14.01.2018.
 */
class CallWindowActivity : BaseAppCompatActivity(), View.OnClickListener {

    private lateinit var activityCallName: TextViewCustom
    private lateinit var activityCallHangUp: ImageView
    private lateinit var activityCallHangUpLayout: LinearLayout
    private lateinit var activityCallVolume: ImageView
    private lateinit var activityCallChat: ImageView
    private lateinit var activityCallMic: ImageView

    private lateinit var bundle: Bundle

    private var speaker: Boolean = true
    private var mic: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        actionBar!!.hide()

        bundle = intent.extras

        activityCallName = findViewById<View>(R.id.activityCallName) as TextViewCustom
        activityCallHangUp = findViewById<View>(R.id.activityCallHangUp) as ImageView
        activityCallHangUpLayout = findViewById<View>(R.id.activityCallHangUpLayout) as LinearLayout

        activityCallVolume = findViewById<View>(R.id.activityCallVolume) as ImageView
        activityCallChat = findViewById<View>(R.id.activityCallChat) as ImageView
        activityCallMic = findViewById<View>(R.id.activityCallMic) as ImageView

        activityCallHangUp.setOnClickListener(this)
        activityCallHangUpLayout.setOnClickListener(this)

        activityCallVolume.setOnClickListener(this)
        activityCallChat.setOnClickListener(this)
        activityCallMic.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        var v = bundle.getString(Constants.CALL_OBJECT)
        activityCallName.text = bundle.getString(Constants.CALL_OBJECT)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.activityCallVolume -> {
                var icon = if (speaker) R.drawable.ic_volume_off else R.drawable.ic_volume_on
                activityCallVolume.setImageResource(icon)
                speaker = !speaker
            }

            R.id.activityCallChat -> {

            }

            R.id.activityCallMic -> {
                var icon = if (mic) R.drawable.ic_mic_on else R.drawable.ic_mic_off
                activityCallMic.setImageResource(icon)
                mic = !mic
            }

            R.id.activityCallHangUp -> {
                finish()
            }

            R.id.activityCallHangUpLayout -> {
                finish()
            }
        }
    }
}