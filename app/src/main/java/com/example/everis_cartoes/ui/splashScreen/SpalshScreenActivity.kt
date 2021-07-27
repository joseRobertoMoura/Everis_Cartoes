package com.example.everis_cartoes.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.ui.home.HomeViewModel
import com.example.everis_cartoes.ui.login.LoginActivity
import com.example.everis_cartoes.utils.StatusUserFireBase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpalshScreenActivity : AppCompatActivity() {

    private val viewModel by viewModel<SpalshScreenViewModel>()

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
                is SpalshScreenActionView.sessionInitialized -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                is SpalshScreenActionView.sessionNotInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        })
    }

}