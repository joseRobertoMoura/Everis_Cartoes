package com.example.everis_cartoes.repository.login

import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginRepositoryImpl : LoginRepository {

    override suspend fun loginFireBase(
        dataLogin: LoginFireBaseModel,
        callbackSuccess: () -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(dataLogin.userName, dataLogin.password)
            .addOnCompleteListener {
                when{
                    it.isSuccessful -> {
                        callbackSuccess.invoke()
                    }
                    else -> {
                        callbackError.invoke("Error!")
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
        callbackSuccessVerifySection: () -> Unit,
        callbackErrorVerifySection: () -> Unit
    ) {
        val userLoged = FirebaseAuth.getInstance().currentUser
        if (userLoged != null){
            callbackSuccessVerifySection.invoke()
        }else{
            callbackErrorVerifySection.invoke()
        }
    }

    override suspend fun logoutFireBase() {
        FirebaseAuth.getInstance().signOut()
    }
}