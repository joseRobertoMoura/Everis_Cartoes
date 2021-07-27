package com.example.everis_cartoes.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity: AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logoutApp()
    }

    private fun logoutApp(){
        initViewModel()
    }

    private fun initViewModel(){
        viewModel.init()
        viewModel.logoutStatus.observe(this, {state ->
            when(state){
                is HomeViewAction.LogoutSuccess -> {
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }

                is HomeViewAction.LogoutError -> {
                    Toast.makeText(this,state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}