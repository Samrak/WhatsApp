package com.geveze.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.geveze.R
import com.geveze.base.BaseFragmentActivity
import com.geveze.fragment.ChatFragment
import com.geveze.fragment.StatusFragment
import com.geveze.fragment.CallsFragment
import com.geveze.models.Call
import com.geveze.models.Chat
import com.geveze.utilities.Constants
import com.geveze.utilities.GlobalClass
import kotlin.system.exitProcess

/**
 * Created by sametoztoprak on 25/09/2017.
 */

class MainActivity : BaseFragmentActivity(),
        View.OnClickListener,
        TabLayout.OnTabSelectedListener,
        ChatFragment.OnChatWindow,
        CallsFragment.OnCallWindow {

    private lateinit var tabLayout: TabLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var selectedTab: String
    private lateinit var chatTab: String
    private lateinit var statusTab: String
    private lateinit var callsTab: String

    private lateinit var globalClass: GlobalClass

    init {

    }

    override fun onCallWindowClicked(item: Call) {
        val intent = Intent(this, CallWindowActivity::class.java)
        intent.putExtra(Constants.CALL_OBJECT, item.name)
        startActivity(intent)
    }

    override fun onChatWindowClicked(item: Chat) {
        val intent = Intent(this, ChatWindowActivity::class.java)
        intent.putExtra(Constants.CHAT_OBJECT, item.name)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVaribles()

        globalClass = GlobalClass.getInstance(this)
        var phoneCode: String = globalClass.prefGet(Constants.PHONE_CODE)
        var phoneNumber: String = globalClass.prefGet(Constants.PHONE_NUMBER)

        if (phoneCode.isNullOrEmpty() || phoneNumber.isNullOrEmpty()) {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar));
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(false);

        var viewPager = findViewById<ViewPager>(R.id.viewpager)
        setupViewPager(viewPager)

        fab = findViewById<View>(R.id.fab) as FloatingActionButton
        setIcon(R.drawable.ic_chat)

        tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        fab.setOnClickListener(this)
        tabLayout.addOnTabSelectedListener(this)
    }

    private fun initVaribles() {
        chatTab = getString(R.string.chat)
        statusTab = getString(R.string.status)
        callsTab = getString(R.string.calls)
        selectedTab = chatTab
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }// return super.onOptionsItemSelected(item);
        return false
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(ChatFragment(), chatTab)
        adapter.addFragment(StatusFragment(), statusTab)
        adapter.addFragment(CallsFragment(), callsTab)
        viewPager!!.adapter = adapter
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> {
                when (selectedTab) {
                    chatTab -> {
                        var isChat = true
                        val intent = Intent(this, ContactsActivity::class.java)
                        intent.putExtra(Constants.IS_CHAT, isChat)
                        startActivity(intent)
                    }
                    statusTab -> {
                        Toast.makeText(this, "statusTab", Toast.LENGTH_LONG).show();
                    }
                    callsTab -> {
                        var isChat = false
                        val intent = Intent(this, ContactsActivity::class.java)
                        intent.putExtra(Constants.IS_CHAT, isChat)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_history, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true);
        exitProcess(-1)
    }

    private fun setIcon(icon: Int) {
        fab.setImageDrawable(ContextCompat.getDrawable(this, icon));
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {
        selectedTab = tab!!.text.toString()
        when (selectedTab) {
            chatTab -> setIcon(R.drawable.ic_chat)
            statusTab -> setIcon(R.drawable.ic_camera)
            callsTab -> setIcon(R.drawable.ic_phone)

        }
    }
}
