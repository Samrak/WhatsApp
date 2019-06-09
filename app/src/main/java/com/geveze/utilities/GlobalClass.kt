package com.geveze.utilities

import android.content.Context
import android.telephony.TelephonyManager
import java.util.*
import android.content.SharedPreferences
import com.geveze.base.BaseAppCompatActivity


/**
 * Created by sametoztoprak on 17/09/2017.
 */

class GlobalClass(private val context: Context) {
    private val prefName: String = "com.geveze"
    private val emptyString: String = ""

    init {
        GlobalClass.context = context
        prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun getLanguage(): String {
        return Locale.getDefault().getLanguage();
    }

    fun prefSet(key: String, value: String) {
        with(prefs.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun prefGet(key: String): String {
        return prefs.getString(key, emptyString)
    }

    fun getPhoneNumber(): String {
        val tMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val mPhoneNumber = tMgr.line1Number
        return mPhoneNumber;
    }

    companion object {
        private var globalClass: GlobalClass? = null
        private lateinit var context: Context
        private lateinit var prefs: SharedPreferences

        fun getInstance(context: Context): GlobalClass {
            return if (globalClass == null)
                GlobalClass(context)
            else
                globalClass as GlobalClass
        }
    }
}
