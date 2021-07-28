package com.example.everis_cartoes.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            verifySection()
        },3500)
    }

    private fun verifySection() {
        viewModel.init()
        viewModel.verifySession.observe(this, {state ->
            when(state){
                is SplashScreenActionView.SessionInitialized -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                is SplashScreenActionView.SessionNotInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        })
    }

}