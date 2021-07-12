package com.example.mfapractice

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.mfapractice.navigation.Router
import com.example.mfapractice.navigation.RouterImpl
import com.example.mfapractice.navigation.RoutingActionMediator
import com.example.mfapractice.navigation.RoutingActionSource
import com.example.mfapractice.screens.home.HomeViewModel
import com.example.mfapractice.screens.login.LogInViewModel
import com.example.mfapractice.screens.otp.OTPViewModel
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class OTPApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        startKoin {
            // declare used Android context
            androidContext(this@OTPApplication)
            // declare modules
            modules(createModule())
        }
    }

    private fun createModule() =

        module {
            single<RoutingActionSource> { RoutingActionMediator() }

            factory<Router> { (activity: AppCompatActivity) ->
                RouterImpl(activity.supportFragmentManager)
            }

            viewModel { LogInViewModel(mediator = get()) }
            viewModel { OTPViewModel(mediator = get()) }
            viewModel { HomeViewModel(mediator = get()) }
        }
}
