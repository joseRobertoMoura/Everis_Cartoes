package com.example.everis_cartoes.utils

import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StatusUserFireBase (
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) {
    private val job = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + job)
    private val ioDispatcher = CoroutineScope(ioDispatcher + job)

    fun logoutFireBase(
        callbackLogout:() -> Unit,
        callbackErrorLogout:(error:String) -> Unit
    ){
        try{
            mainDispatcher.launch {
                ioDispatcher.async {
                    return@async FirebaseAuth.getInstance().signOut()
                }.await()
            }
            callbackLogout.invoke()
        }catch(e: Exception){
            callbackErrorLogout.invoke(e.toString())
        }

    }

    fun verifySectionFireBase(
        callbackSuccessVerifySection: () -> Unit,
        callbackErrorVerifySection: () -> Unit
    ) {
        mainDispatcher.launch {
            ioDispatcher.async {
                val userLoged = FirebaseAuth.getInstance().currentUser
                if (userLoged != null){
                    callbackSuccessVerifySection.invoke()
                }else{
                    callbackErrorVerifySection.invoke()
                }
            }.await()
        }
    }
}