package com.example.everis_cartoes.repository.login

import com.example.everis_cartoes.data.model.login.LoginFireBaseModel

interface LoginRepository {
    suspend fun loginFireBase(
        dataLogin: LoginFireBaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error:String) -> Unit)

    suspend fun verifySectionFireBase(
        callbackSuccessVerifySection:() -> Unit,
        callbackErrorVerifySection: () -> Unit
    )
}