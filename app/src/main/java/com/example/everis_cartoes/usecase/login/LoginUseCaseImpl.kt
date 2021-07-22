package com.example.everis_cartoes.usecase.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginUseCaseImpl : LoginUseCase{

   override suspend fun loginFireBase(
       email: String,
       senha: String,
       callbackSuccess:() -> Unit,
       callbackError: (error:String) -> Unit){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                when{
                    it.isSuccessful -> {
                        callbackSuccess.invoke()
                    }
                }
            }.addOnFailureListener {
                when (it) {
                    is FirebaseAuthWeakPasswordException -> {
                        callbackError.invoke("Senha incorreta!")
                    }
                    else -> {
                        callbackError.invoke("Erro ao efetuar o login")
                    }
                }
            }
    }

    override suspend fun verifySectionFireBase(
        callbackSuccessVerifySection:() -> Unit,
        callbackErrorVerifySection: () -> Unit
    ){
        val userLoged = FirebaseAuth.getInstance().currentUser
        if (userLoged != null){
            callbackSuccessVerifySection.invoke()
        }else{
            callbackErrorVerifySection.invoke()
        }
    }

}