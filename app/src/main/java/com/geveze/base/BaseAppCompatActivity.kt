package com.geveze.base

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu

import com.geveze.R
import com.geveze.dao.SQLiteHelper
import com.samrak.sqlite.SQLiteAdapter
import android.text.Spannable
import android.text.SpannableString
import android.net.ConnectivityManager
import android.content.IntentFilter
import com.geveze.dialogs.OneButtonDialog
import com.geveze.utilities.*


/**
 * Created by sametoztoprak on 18/09/2017.
 */

open class BaseAppCompatActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    protected val PHONE_SIZE: Int = 10
    protected var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dObjects = DialogObjects.getInstance(this)
        globalClass = GlobalClass.getInstance(this)
        db = SQLiteAdapter(SQLiteHelper.getInstance(this))
    }

    protected fun setTitle(title: String) {
        var spannableString: SpannableString? = null
        spannableString = SpannableString(title)
        spannableString.setSpan(CApplication.getInstance().getFontTitle(), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        actionBar!!.setTitle(spannableString)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

        val connectivityReceiver = ConnectivityReceiver()
        registerReceiver(connectivityReceiver, intentFilter)
        CApplication.getInstance().setConnectivityListener(this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private fun showSnack(isConnected: Boolean) {
        if (!isConnected) {
            var message: String = getString(R.string.dialog_internet_connection_text)
            OneButtonDialog.getInstance(this, dObjects.getDialogWarningBundle(message))!!.show()
        }
    }

    protected fun getExceptionDialog(messageInt: Int) {
        var message: String = getString(messageInt)
        if (!message.isNullOrEmpty()) {
            var bundle: Bundle = dObjects.getDialogWarningBundle(message);
            OneButtonDialog.getInstance(this, bundle)!!.show()
        }
    }

    companion object {
        lateinit var dObjects: DialogObjects
        lateinit var globalClass: GlobalClass
        lateinit var db: SQLiteAdapter<SQLiteHelper>
    }
}
