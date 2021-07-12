package com.example.mfapractice.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.mfapractice.R
import com.example.mfapractice.screens.home.HomeFragment
import com.example.mfapractice.screens.login.LogInFragment
import com.example.mfapractice.screens.otp.OTPFragment

private const val MAIN_CONTAINER = R.id.fl_main
class RouterImpl(private val fragmentManager: FragmentManager): Router {

    override fun goToLoginScreen() {
        fragmentManager.beginTransaction().apply {
            replace(MAIN_CONTAINER, LogInFragment())
            commit()
        }
    }

    override fun goToTOPScreen(phoneNumber: String) {
        fragmentManager.beginTransaction().apply {
            val otpFragment = OTPFragment()
            val bundle = Bundle()
            bundle.putString(OTPFragment.PHONE_NUMBER_KEY, phoneNumber)
            otpFragment.arguments = bundle
            add(MAIN_CONTAINER, otpFragment, OTPFragment.TAG)
            addToBackStack(OTPFragment.TAG)
            commit()
        }
    }

    override fun goToHomeScreen() {
        fragmentManager.beginTransaction().apply {
            add(MAIN_CONTAINER, HomeFragment(), HomeFragment.TAG)
            commit()
        }
    }

    override fun goBack() {
        fragmentManager.popBackStack()
    }

}
