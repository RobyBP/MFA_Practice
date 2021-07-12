package com.example.mfapractice.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mfapractice.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment(): Fragment() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var button: Button
    private val model: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        username = view.findViewById(R.id.login_username)
        password = view.findViewById(R.id.login_password)
        phoneNumber = view.findViewById(R.id.login_phone_number)
        button = view.findViewById(R.id.login_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            model.goToOTP(phoneNumber = phoneNumber.text.toString())
        }
    }
}