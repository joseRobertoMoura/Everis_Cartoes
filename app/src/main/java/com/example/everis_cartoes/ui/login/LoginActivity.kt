package com.example.everis_cartoes.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import com.example.everis_cartoes.R

class LoginActivity : AppCompatActivity() {

    lateinit var edtUserName: AppCompatEditText
    lateinit var edtPassword: AppCompatEditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews() {
        edtUserName = findViewById(R.id.edt_user_name)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
    }
}