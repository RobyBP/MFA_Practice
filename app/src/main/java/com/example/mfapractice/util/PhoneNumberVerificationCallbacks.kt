package com.example.mfapractice.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken


class PhoneNumberVerificationCallbacks(private val context: Context) :
    PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    private var storedVerificationId: String? = null

    override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        Log.d("number", "onCodeSent:$verificationId")

        // Save verification ID and resending token so we can use them later
        storedVerificationId = verificationId
    }

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//        Log.d("Number", "onVerificationCompleted:$credential")
//        signInWithPhoneAuthCredential(credential)
    }

    override fun onVerificationFailed(p0: FirebaseException) {
        Toast.makeText(context, p0.message, Toast.LENGTH_LONG).show()
        Log.d("number", p0.message ?: "dunno man")
    }

    fun getStoredVerificationId(): String? = storedVerificationId
}
