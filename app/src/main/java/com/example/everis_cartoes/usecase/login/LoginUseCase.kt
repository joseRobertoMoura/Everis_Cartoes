package com.example.everis_cartoes.usecase.login

import android.content.Context
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel

interface LoginUseCase {

    suspend fun executeLoginFireBase(
        dataLogin: LoginFireBaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error:String) -> Unit)

    suspend fun executeVerifySectionFireBase(
        callbackSuccessVerifySection:() -> Unit,
        callbackErrorVerifySection: () -> Unit
    )

}