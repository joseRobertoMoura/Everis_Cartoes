package com.example.everis_cartoes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.utils.StatusUserFireBase

sealed class HomeViewAction{
    object LogoutSuccess : HomeViewAction()
    class LogoutError(val error:String):HomeViewAction()
}

class HomeViewModel(
    private val statusUserFireBase: StatusUserFireBase
) : ViewModel() {

    private val _logoutStatus by lazy { MutableLiveData<HomeViewAction>() }
    val logoutStatus:LiveData<HomeViewAction>
        get() = _logoutStatus

    fun init() {
        executeLogout()
    }

    private fun executeLogout() {
        statusUserFireBase.logoutFireBase(::logout, :: errorLogout)
    }

    private fun logout(){
        _logoutStatus.postValue(HomeViewAction.LogoutSuccess)
    }

    private fun errorLogout(error:String){
        _logoutStatus.postValue(HomeViewAction.LogoutError(error))
    }
}