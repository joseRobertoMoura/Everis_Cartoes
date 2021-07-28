package com.example.everis_cartoes.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.ui.login.LoginActivity
import com.example.everis_cartoes.ui.login.utils.AuthTags
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    val AUTH_TAG=AuthTags.AUTH.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            verifySection()
        },2500)
    }

    private fun verifySection() {
        viewModel.init()
        viewModel.verifySession.observe(this, {state ->
            when(state){
                is SplashScreenActionView.SessionInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                        .putExtra(AUTH_TAG,true))
                }

                is SplashScreenActionView.SessionNotInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                        .putExtra(AUTH_TAG, false))
                }
            }
        })
    }

}