package com.example.everis_cartoes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.example.everis_cartoes.usecase.login.LoginUseCase
import com.example.everis_cartoes.utils.LogoutFireBase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

sealed class LoginActionView{
    object LoginSuccess : LoginActionView()
    class LoginError(val error:String):LoginActionView()
}

sealed class VerifyActionView{
    object verifySuccess: VerifyActionView()
    object verifyError: VerifyActionView()
}

class LoginViewModel(
    val useCase: LoginUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
    ) : ViewModel() {

    private val job = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + job)
    private val ioDispatcher = CoroutineScope(ioDispatcher + job)

    private val _loginActionView by lazy {MutableLiveData<LoginActionView>()}
    val loginActionView:LiveData<LoginActionView>
        get() = _loginActionView

    private val _verifyViewAction by lazy {MutableLiveData<VerifyActionView>()}
    val verifyActionView:LiveData<VerifyActionView>
        get() = _verifyViewAction

    fun init(login:LoginFireBaseModel) {
        executeLogin(login)
    }

    private fun executeLogin(login:LoginFireBaseModel) {
        mainDispatcher.launch {
            loginUseCase(login)
            coroutineContext.cancelChildren()
        }
    }

    fun verifyLogin() {
        mainDispatcher.launch {
            verifyLoginUseCase()
        }
    }

    fun executeLogoutFireBase(){
        mainDispatcher.launch {
            ioDispatcher.async {
                return@async LogoutFireBase.logoutFireBase()
            }.await()
        }
    }

    private suspend fun verifyLoginUseCase() {
        ioDispatcher.async {
            return@async useCase.executeVerifySectionFireBase(::successVerify, ::errorVerify)
        }.await()
    }

    private suspend fun loginUseCase(login:LoginFireBaseModel) {
        ioDispatcher.async {
            return@async useCase.executeLoginFireBase(login, ::success, ::error)
        }.await()
    }

    private fun success(){
        _loginActionView.postValue(LoginActionView.LoginSuccess)
    }

    private fun error(error: String){
       _loginActionView.postValue( LoginActionView.LoginError(error))
    }

    private fun successVerify(){
        _verifyViewAction.postValue(VerifyActionView.verifySuccess)
    }

    private fun errorVerify(){
        _verifyViewAction.postValue( VerifyActionView.verifyError)
    }

}