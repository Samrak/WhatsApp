package com.geveze.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.geveze.R
import com.geveze.base.BaseAppCompatActivity
import com.geveze.components.EditTextCustom
import com.geveze.components.TextViewCustom
import com.geveze.utilities.Constants
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.FirebaseAuth


/**
 * Created by sametoztoprak on 14/12/2017.
 */
class VerificationActivity : BaseAppCompatActivity() {

    private var TIME_OUT: Long = 60

    private lateinit var phoneCode: String
    private lateinit var phoneNumber: String

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mVerificationId: String
    private lateinit var mResendToken: PhoneAuthProvider.ForceResendingToken

    private lateinit var activityVerificationPhone: TextViewCustom
    private lateinit var activityVerificationPhoneKey: TextViewCustom
    private lateinit var activationVerificationCode: EditTextCustom

    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val bundle: Bundle = intent.extras
        if (bundle != null) {
            phoneCode = bundle.getString(Constants.PHONE_CODE)
            phoneNumber = bundle.getString(Constants.PHONE_NUMBER)
        }

        activityVerificationPhone = findViewById<View>(R.id.activityVerificationPhone) as TextViewCustom
        activityVerificationPhoneKey = findViewById<View>(R.id.activityVerificationPhoneKey) as TextViewCustom
        activationVerificationCode = findViewById<View>(R.id.activationVerificationCode) as EditTextCustom

        activityVerificationPhoneKey.text = getString(R.string.phone_number) + " : ";

        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(globalClass.getLanguage())

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone phoneNumber can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("message", "onVerificationCompleted:" + credential)

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone phoneNumber format is not valid.
                Log.w("message", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    getExceptionDialog(R.string.Firebase_AuthInvalid_Credentials_Exception)
                } else if (e is FirebaseTooManyRequestsException) {
                    getExceptionDialog(R.string.Firebase_TooMany_Requests_Exception)
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
                // ...
            }

            override fun onCodeSent(verificationId: String?,
                                    token: PhoneAuthProvider.ForceResendingToken?) {
                // The SMS verification code has been sent to the provided phone phoneNumber, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("message", "onCodeSent:" + verificationId!!)

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId
                mResendToken = token!!

                // ...
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setTitle(getString(R.string.verification))
        activityVerificationPhone.text = phoneCode + phoneNumber

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                activityVerificationPhone.text.toString(),        // Phone phoneNumber to verify
                TIME_OUT,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        globalClass.prefSet(Constants.PHONE_CODE, phoneCode)
                        globalClass.prefSet(Constants.PHONE_NUMBER, phoneNumber)
                        Log.d("message", "signInWithCredential:success")
                        val user = task.result?.user

                        startActivity(Intent(this, ConfirmedActivity::class.java))
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("message", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                })
    }

    // [START resend_verification]
    private fun resendCode(phoneNumber: String,
                           token: PhoneAuthProvider.ForceResendingToken) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, // Phone number to verify
                TIME_OUT, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                mCallbacks, // OnVerificationStateChangedCallbacks
                token)             // ForceResendingToken from callbacks
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_verification, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.resend -> resendCode(activityVerificationPhone.text.toString(), mResendToken)
           // R.id.forward -> completedVerification(activationVerificationCode.text.toString())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun completedVerification(code: String) {

        verifyPhoneNumberWithCode(mVerificationId, code)
    }
}