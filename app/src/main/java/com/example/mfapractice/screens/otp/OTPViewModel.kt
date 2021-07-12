package com.example.mfapractice.screens.otp

import android.util.Log
import com.example.mfapractice.baseclasses.BaseViewModel
import com.example.mfapractice.navigation.Router
import com.example.mfapractice.navigation.RoutingActionSource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider

class OTPViewModel(mediator: RoutingActionSource): BaseViewModel(mediator) {

    fun verifyCode(verificationId: String?, code: String, fbAuth: FirebaseAuth) {
        if (verificationId == null) return
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signUpWithCredential(credential, fbAuth)
    }

    private fun signUpWithCredential(credential: AuthCredential, fbAuth: FirebaseAuth) {
        fbAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    mediator.dispatch(Router::goToHomeScreen)
                    Log.d("number", "signInWithCredential:success")
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("number", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.d("number", "The code was invalid")
                    }
                }
            }

    }
}