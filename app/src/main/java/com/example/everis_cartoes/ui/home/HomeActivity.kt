package com.example.everis_cartoes.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.cartoes.CartoesFragment
import com.example.everis_cartoes.ui.splashScreen.SplashScreenActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity: AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        initFragments()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logoutApp()
    }

    private fun logoutApp(){
        initViewModel()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,CartoesFragment.newInstance())
            commit()
        }
    }

    private fun initViews() {
    }

    private fun initViewModel(){
        viewModel.init()
        viewModel.logoutStatus.observe(this, {state ->
            when(state){
                is HomeViewAction.LogoutSuccess -> {
                    startActivity(Intent(this,SplashScreenActivity::class.java))
                    finish()
                }

                is HomeViewAction.LogoutError -> {
                    Toast.makeText(this,state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}