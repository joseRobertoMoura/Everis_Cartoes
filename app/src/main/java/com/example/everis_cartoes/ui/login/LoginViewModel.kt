package com.example.everis_cartoes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.example.everis_cartoes.usecase.login.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

sealed class LoginActionView{
    object LoginSuccess : LoginActionView()
    class LoginError(val error:String):LoginActionView()
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


    fun init(login:LoginFireBaseModel) {
        executeLogin(login)
    }

    private fun executeLogin(login:LoginFireBaseModel) {
        mainDispatcher.launch {
            loginUseCase(login)
        }
    }

    private suspend fun loginUseCase(login:LoginFireBaseModel) {
        ioDispatcher.async {
            return@async useCase.executeLoginFireBase(login, ::success, ::error)
        }.await()
    }

    private fun success(){
        _loginActionView.postValue(LoginActionView.LoginSuccess)
        coroutineContext.cancelChildren()
    }

    private fun error(error: String){
       _loginActionView.postValue( LoginActionView.LoginError(error))
        coroutineContext.cancelChildren()
    }

}