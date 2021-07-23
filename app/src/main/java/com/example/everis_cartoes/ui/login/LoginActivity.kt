package com.example.everis_cartoes.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.everis_cartoes.R
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
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
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            val login = LoginFireBaseModel(
                edtUserName.text.toString(),
                edtPassword.text.toString()
            )
            initViewModel(login)
        }
    }

    private fun initViewModel(login:LoginFireBaseModel) {
        viewModel.init(login)
        viewModel.loginActionView.observe(this, { state ->
            when(state){
                is LoginActionView.LoginSuccess -> {
                    Toast.makeText(this,"Sucesso",Toast.LENGTH_LONG).show()
                }

                is LoginActionView.LoginError -> {
                    Toast.makeText(this,state.toString(),Toast.LENGTH_LONG).show()
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