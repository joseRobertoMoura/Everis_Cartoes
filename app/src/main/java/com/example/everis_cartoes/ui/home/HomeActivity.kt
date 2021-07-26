package com.example.everis_cartoes.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.login.LoginActivity
import com.example.everis_cartoes.ui.login.LoginViewModel
import com.example.everis_cartoes.utils.LogoutFireBase
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.executeLogoutFireBase()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}