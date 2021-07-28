package com.example.everis_cartoes.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.everis_cartoes.R
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.utils.VerifyLoginField
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()

    lateinit var edtUserName: AppCompatEditText
    lateinit var edtPassword: AppCompatEditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initEvent()
    }

    private fun initEvent() {
        btnLogin.setOnClickListener {
            initViewModel()
        }
    }

    private fun initViewModel() {
        viewModel.init(VerifyLoginField.verifyLogin(
            edtUserName.text.toString(),
            edtPassword.text.toString(),
            this)
        )
        viewModel.loginActionView.observe(this, { state ->
            when(state){
                is LoginActionView.LoginSuccess -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                is LoginActionView.LoginError -> {
                    Toast.makeText(this,state.error,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initViews() {
        edtUserName = findViewById(R.id.edt_user_name)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
    }
}