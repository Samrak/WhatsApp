package com.geveze.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.geveze.R
import com.geveze.base.BaseAppCompatActivity
import com.geveze.components.ButtonCustom

/**
 * Created by sametoztoprak on 14.02.2018.
 */

class ConfirmedActivity : BaseAppCompatActivity(), View.OnClickListener{


    private lateinit var activityConfirmedGoOnButton: ButtonCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmed)

        actionBar!!.hide()

        activityConfirmedGoOnButton = findViewById<View>(R.id.activityConfirmedGoOnButton) as ButtonCustom

        activityConfirmedGoOnButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.activityConfirmedGoOnButton -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}
