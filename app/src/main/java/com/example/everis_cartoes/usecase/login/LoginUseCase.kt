package com.example.everis_cartoes.usecase.login

import android.content.Context

interface LoginUseCase {
    suspend fun loginFireBase(
        email: String,
        senha: String,
        callbackSuccess:() -> Unit,
        callbackError: (error:String) -> Unit)
    suspend fun verifySectionFireBase(
        callbackSuccessVerifySection:() -> Unit,
        callbackErrorVerifySection: () -> Unit
    )
}