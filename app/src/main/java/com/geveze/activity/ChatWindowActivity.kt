package com.geveze.activity

import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.geveze.R
import com.geveze.adapters.WindowAdapter
import com.geveze.base.BaseAppCompatActivity
import com.geveze.models.Message
import com.geveze.utilities.Constants
import java.util.ArrayList

/**
 * Created by sametoztoprak on 17/12/2017.
 */
class ChatWindowActivity : BaseAppCompatActivity() {

    private lateinit var sendButton: Button
    private lateinit var chatVindowListView: ListView
    private lateinit var messages: ArrayList<Message>
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        bundle = intent.extras

        sendButton = findViewById<View>(R.id.sendButton) as Button
        chatVindowListView = findViewById<View>(R.id.chatWindowListView) as ListView
    }

    override fun onStart() {
        super.onStart()
        setTitle(bundle.getString(Constants.CHAT_OBJECT))

        messages = ArrayList<Message>();
        messages.add(Message(true, "Heyyy"))
        messages.add(Message(true, "What is up ?"))
        messages.add(Message(false, "good and you ?"))

        chatVindowListView.adapter = WindowAdapter(messages, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_chat, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.phone -> {

            }
        }// return super.onOptionsItemSelected(item);
        return false
    }

}