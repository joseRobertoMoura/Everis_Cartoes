package com.example.everis_cartoes.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.utils.StatusUserFireBase

sealed class SplashScreenActionView{
    object SessionInitialized : SplashScreenActionView()
    object SessionNotInitialized : SplashScreenActionView()
}

class SplashScreenViewModel(
    private val statusUserFireBase: StatusUserFireBase
) : ViewModel() {

    private val _verifySession by lazy { MutableLiveData<SplashScreenActionView>() }
    val verifySession:LiveData<SplashScreenActionView>
        get() = _verifySession

    fun init() {
        executeLogout()
    }

    private fun executeLogout() {
        statusUserFireBase.verifySectionFireBase(::initialized, :: notInitialized)
    }

    private fun initialized(){
        _verifySession.postValue(SplashScreenActionView.SessionInitialized)
    }

    private fun notInitialized(){
        _verifySession.postValue(SplashScreenActionView.SessionNotInitialized)
    }
}