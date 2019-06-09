package com.geveze.utilities

import android.app.Application
import android.graphics.Typeface
import android.util.Log

import com.geveze.R
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



/**
 * Created by sametoztoprak on 26/09/2017.
 */

class CApplication : Application() {

    private var cookies: Map<String, String>? = null
    private var fontMedium: Typeface? = null
    private var fontRegular: Typeface? = null
    private var fontLight: Typeface? = null

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(true)           // Enables Crashlytics debugger
                .build()
        Fabric.with(fabric)
        obj = this
    }

    fun setCookies(cookies: Map<String, String>) {
        this.cookies = cookies
    }

    fun getFontTitle(): CustomTypefaceSpan? {

        if (fontMedium == null) {
            fontMedium = getFont(resources.getInteger(R.integer.font_Medium))
        }

        return CustomTypefaceSpan(fontMedium!!)
    }

    fun getFont(fontId: Int): Typeface? {

        val resources = resources

        if (fontId == resources.getInteger(R.integer.font_Medium)) {
            if (fontMedium == null) {
                fontMedium = Typeface.createFromAsset(assets, "fonts/GT-Eesti-Display-Medium.otf")
            }
            return fontMedium

        } else if (fontId == resources.getInteger(R.integer.font_Regular)) {
            if (fontRegular == null) {
                fontRegular = Typeface.createFromAsset(assets, "fonts/GT-Eesti-Display-Regular.otf")
            }
            return fontRegular

        } else if (fontId == resources.getInteger(R.integer.font_Light)) {
            if (fontLight == null) {
                fontLight = Typeface.createFromAsset(assets, "fonts/GT-Eesti-Display-Light.otf")
            }
            return fontLight

        } else {
            return fontRegular
        }
    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }

    companion object {

        private val TAG = CApplication.toString()
        private var obj: CApplication? = null

        fun getInstance(): CApplication {
            if (obj == null) {
                Log.v(TAG, "instance is null!!!")
                obj = CApplication()
            }
            return obj as CApplication
        }
    }
}
