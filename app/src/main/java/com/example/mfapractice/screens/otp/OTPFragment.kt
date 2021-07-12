package com.example.mfapractice.screens.otp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.chaos.view.PinView
import com.example.mfapractice.R
import com.example.mfapractice.util.PhoneNumberVerificationCallbacks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class OTPFragment : Fragment() {

    companion object {
        const val TAG = "OTPFragment"
        const val PHONE_NUMBER_KEY = "PhoneNumber"
    }

    private lateinit var pinView: PinView
    private lateinit var callbacks: PhoneNumberVerificationCallbacks
    private lateinit var button: Button
    private val fbAuth = FirebaseAuth.getInstance()
    private val model: OTPViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_otp, container, false)
        pinView = view.findViewById(R.id.otp_pinview)
        callbacks = PhoneNumberVerificationCallbacks(requireContext())
        sendCodeToUser(requireArguments().getString(PHONE_NUMBER_KEY, ""))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pinView.doOnTextChanged { text, _, _, _ ->
            if (text.toString().length == pinView.itemCount) {
                model.verifyCode(
                    verificationId = callbacks.getStoredVerificationId(),
                    code = text!!.toString(),
                    fbAuth = fbAuth
                )
            }
        }
    }

    private fun sendCodeToUser(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(fbAuth)
            .setPhoneNumber("+385$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)      // OnVerificationStateChangedCallbacks
            .build()
        Log.d("number", options.zzh()!!)
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}