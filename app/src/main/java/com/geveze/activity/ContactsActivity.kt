package com.geveze.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.geveze.adapters.FriendAdapter
import com.geveze.base.BaseAppCompatActivity
import com.geveze.models.Friend
import java.util.ArrayList
import android.graphics.BitmapFactory
import android.view.Menu
import android.view.MenuItem
import com.geveze.R
import com.geveze.utilities.Constants
import com.geveze.utilities.ImageConverter

/**
 * Created by sametoztoprak on 16/12/2017.
 */
class ContactsActivity : BaseAppCompatActivity(), View.OnClickListener {

    private lateinit var activityFriendListView: ListView
    private lateinit var friends: ArrayList<Friend>
    private var isChat: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val bundle: Bundle = intent.extras
        if (bundle != null) {
            isChat = bundle.getBoolean(Constants.IS_CHAT)
        }

        activityFriendListView = findViewById<View>(R.id.activityFriendListView) as ListView
    }

    override fun onStart() {
        super.onStart()
        setTitle(getString(R.string.contacts))

        val icon = BitmapFactory.decodeResource(getResources(), R.mipmap.person)
        val bitmapdata = ImageConverter.bitmaptoByteArray(icon)

        friends = ArrayList<Friend>();
        friends.add(Friend(bitmapdata, "Samet Öztoprak", "Meşgul"))
        friends.add(Friend(bitmapdata, "Bilal Yanık", "Online"))
        friends.add(Friend(bitmapdata, "Mehmet Demirkıran", "Away"))
        friends.add(Friend(bitmapdata, "Murat Güney", "Away"))
        friends.add(Friend(bitmapdata, "Gökçe Kam", "Away"))

        activityFriendListView.adapter = FriendAdapter(friends, isChat, this)
        activityFriendListView.setOnItemClickListener { parent, view, position, l ->
            var item = parent.getItemAtPosition(position) as Friend
            val intent: Intent
            if (isChat) {
                intent = Intent(this, ChatWindowActivity::class.java)
                intent.putExtra(Constants.CHAT_OBJECT, item.name)
            } else {
                intent = Intent(this, CallWindowActivity::class.java)
                intent.putExtra(Constants.CALL_OBJECT, item.name)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_contancts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish();
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {

    }
}