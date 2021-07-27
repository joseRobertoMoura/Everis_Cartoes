package com.example.everis_cartoes.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.utils.StatusUserFireBase

sealed class SpalshScreenActionView{
    object sessionInitialized : SpalshScreenActionView()
    class sessionNotInitialized(val error:String):SpalshScreenActionView()
}

class SpalshScreenViewModel(
    private val statusUserFireBase: StatusUserFireBase
) : ViewModel() {

    private val _verifySession by lazy { MutableLiveData<SpalshScreenActionView>() }
    val verifySession:LiveData<SpalshScreenActionView>
        get() = _verifySession

    fun init() {
        executeLogout()
    }

    private fun executeLogout() {
        statusUserFireBase.logoutFireBase(::initialized, :: notInitialized)
    }

    private fun initialized(){
        _verifySession.postValue(SpalshScreenActionView.sessionInitialized)
    }

    private fun notInitialized(error:String){
        _verifySession.postValue(SpalshScreenActionView.sessionNotInitialized(error))
    }
}