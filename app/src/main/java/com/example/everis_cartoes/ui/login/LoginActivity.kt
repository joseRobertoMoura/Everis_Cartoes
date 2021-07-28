package com.example.everis_cartoes.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.everis_cartoes.R
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.ui.login.utils.AuthTags
import com.example.everis_cartoes.utils.VerifyLoginField
import java.util.concurrent.Executor
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

   val AUTH_SUCCESS = AuthTags.SUCCESS.toString()
   val AUTH_KEY = AuthTags.AUTH.toString()

    private val viewModel by viewModel<LoginViewModel>()

    lateinit var edtUserName: AppCompatEditText
    lateinit var edtPassword: AppCompatEditText
    lateinit var btnLogin: Button

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricLoginButton: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initEvent()
        verifyAuth()
        initBiometricAuth()
    }

    private fun verifyAuth() {
        val extras = intent.extras
        if(extras != null){
            when(extras.get(AUTH_KEY)){
                true -> {
                    btnLogin.visibility = View.GONE
                }
                false ->{
                    biometricLoginButton.visibility = View.GONE
                }
            }
        }

    }

    private fun initBiometricAuth() {

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    btnLogin.visibility = View.VISIBLE
                    biometricLoginButton.visibility = View.GONE
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    changeScreen(AUTH_SUCCESS)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, getString(R.string.biometric_auth_fail),
                        Toast.LENGTH_SHORT)
                        .show()
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_title))
            .setNegativeButtonText(getString(R.string.biometric_user_password))
            .build()

    }

    private fun initEvent() {
        btnLogin.setOnClickListener {
            initViewModel()
        }
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
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
        biometricLoginButton = findViewById(R.id.biometric_login)
    }

    private fun changeScreen(activity:String){
        when(activity){
            AUTH_SUCCESS -> {
                startActivity(Intent(this,HomeActivity::class.java))
            }
        }
    }
}