package com.example.everis_cartoes.usecase.login

import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.example.everis_cartoes.repository.login.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginUseCaseImpl(private val repository:LoginRepository) : LoginUseCase{

   override suspend fun executeLoginFireBase(
       dataLogin: LoginFireBaseModel,
       callbackSuccess:() -> Unit,
       callbackError: (error:String) -> Unit){
       repository.loginFireBase(dataLogin,callbackSuccess,callbackError)
    }

    override suspend fun executeVerifySectionFireBase(
        callbackSuccessVerifySection:() -> Unit,
        callbackErrorVerifySection: () -> Unit
    ){
        repository.verifySectionFireBase(callbackSuccessVerifySection,callbackErrorVerifySection)
    }

    override suspend fun executeLogoutFireBase(){
        repository.logoutFireBase()
    }

}