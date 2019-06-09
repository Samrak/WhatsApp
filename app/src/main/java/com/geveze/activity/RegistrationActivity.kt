package com.geveze.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.geveze.R
import com.geveze.adapters.SpinnerAdapter
import com.geveze.base.BaseAppCompatActivity
import com.geveze.components.EditTextCustom
import com.geveze.dialogs.OneButtonDialog
import com.geveze.models.Country
import android.telephony.TelephonyManager
import android.telephony.PhoneNumberFormattingTextWatcher
import com.geveze.utilities.Constants

class RegistrationActivity : BaseAppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var countrySpinner: Spinner
    private lateinit var phoneNumber: EditTextCustom
    private lateinit var countryList: ArrayList<Country>

    private lateinit var countryCode: String

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, p1: View?, pos: Int, p3: Long) {
        val selected = parent.getItemAtPosition(pos) as Country
        countryCode = selected.countryCode.toString();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        actionBar!!.setDisplayHomeAsUpEnabled(false)
        actionBar!!.setDisplayShowHomeEnabled(false)

        countrySpinner = findViewById<View>(R.id.spinnerCountry) as Spinner
        phoneNumber = findViewById<View>(R.id.phoneNumber) as EditTextCustom

        countryList = ArrayList<Country>()
        countryList.add(Country("+1", "USA", "us"))
        countryList.add(Country("+1", "Canada", "ca"))
        countryList.add(Country("+7", "Russia", "ru"))
        countryList.add(Country("+7", "Kazakhstan", "kz"))
        countryList.add(Country("+20", "Egypt", "eg"))
        countryList.add(Country("+33", "France", "fr"))
        countryList.add(Country("+34", "Spain", "es"))
        countryList.add(Country("+39", "Italy", "it"))
        countryList.add(Country("+44", "United Kingdom", "gb"))
        countryList.add(Country("+49", "Germany", "de"))
        countryList.add(Country("+86", "China", "ch"))
        countryList.add(Country("+90", "Turkey", "tr"))
        countryList.add(Country("+91", "India", "in"))
        countryList.add(Country("+92", "Pakistan", "pk"))
        countryList.add(Country("+93", "Afghanistan", "af"))
        countryList.add(Country("+98", "Iran", "ir"))
        countryList.add(Country("+128", "Libya", "ly"))

        countrySpinner.onItemSelectedListener = this
        //countrySpinner.setOnItemSelectedListener(this)
        /*
        // Example of a call to a native method
        sample_text.text = stringFromJNI()
        */
    }

    override fun onStart() {
        super.onStart()
        setTitle(getString(R.string.registration))

        countrySpinner.adapter = SpinnerAdapter(this, R.layout.item_spinner_row, countryList)

        phoneNumber.setText("5543435267") //5302821704
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        countryList.indices
                .filter { countryList.get(it).countryISO.equals(getISOCode()) }
                .forEach { countrySpinner.setSelection(it, true) }

        //db.insert(EntityTest(0, "samet", "öztoprak", "data"), "testId")
        // var tests = db.select(EntityTest())
        // tests = db.select(EntityTest())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.forward -> {
                loginEntry()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {
        when (view.id) {

        }
    }

    private fun loginEntry() {

        if (checkFields()) {
            val intent = Intent(this, VerificationActivity::class.java)
            intent.putExtra(Constants.PHONE_CODE, countryCode)
            intent.putExtra(Constants.PHONE_NUMBER, getNumber())
            startActivity(intent)
        } else {
            var message: String = getString(R.string.dialog_phone_number_text)
            OneButtonDialog.getInstance(this, dObjects.getDialogWarningBundle(message))!!.show()
        }
    }

    private fun checkFields(): Boolean {
        var number: String = getNumber()

        return number.length == PHONE_SIZE
    }

    private fun getNumber(): String {
        var number: String = ""

        phoneNumber.text.toString().indices
                .asSequence()
                .filter { Character.isDigit(phoneNumber.text.toString().get(it)) }
                .forEach { number += phoneNumber.text.toString().get(it) }
        return number
    }

    private fun getISOCode(): String {
        val telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.getNetworkCountryIso();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */


}
