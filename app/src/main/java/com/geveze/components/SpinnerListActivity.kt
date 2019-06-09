package com.geveze.components

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.geveze.R
import com.geveze.adapters.WindowAdapter
import com.geveze.models.Message
import com.geveze.utilities.CApplication
import com.geveze.utilities.Constants
import java.util.ArrayList

/**
 * Created by sametoztoprak on 24.02.2018.
 */
class SpinnerListActivity : AppCompatActivity() {

    private lateinit var activitySpinnerListView: ListView

    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_list)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        bundle = intent.extras

        activitySpinnerListView = findViewById<View>(R.id.activitySpinnerListView) as ListView
    }

    override fun onStart() {
        super.onStart()

        if (bundle != null) {
            val label = bundle.getString("label")
            setTitle(label)
        }

        var messages: ArrayList<Message> = ArrayList<Message>();
        messages.add(Message(true, "Heyyy"))
        messages.add(Message(true, "What is up ?"))
        messages.add(Message(false, "good and you ?"))

        activitySpinnerListView.adapter = WindowAdapter(messages, this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTitle(title: String) {
        var spannableString: SpannableString? = null
        spannableString = SpannableString(title)
        spannableString.setSpan(CApplication.getInstance().getFontTitle(), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar!!.setTitle(spannableString)
    }
}