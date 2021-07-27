package com.example.everis_cartoes.usecase.login

import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.google.firebase.auth.FirebaseAuth

interface LoginUseCase {

    suspend fun executeLoginFireBase(
        dataLogin: LoginFireBaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error:String) -> Unit)

}