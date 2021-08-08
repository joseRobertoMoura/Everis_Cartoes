package com.example.everis_cartoes.ui.cartoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everis_cartoes.data.model.CartoesModel
import com.example.everis_cartoes.usecase.cartoes.CartoesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

sealed class CartoesActionView{
    class SuccessCall(val listCartoes: List<CartoesModel>?) : CartoesActionView()
    class ErrorCall(val error:String):CartoesActionView()
}

class CartoesViewModel(
    val useCase: CartoesUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + job)
    private val ioDispatcher = CoroutineScope(ioDispatcher + job)

    private val _cartoesActionView by lazy { MutableLiveData<CartoesActionView>() }
    val cartoesActionView: LiveData<CartoesActionView>
        get() = _cartoesActionView


    init {
        executeLogin()
    }

    private fun executeLogin() {
        mainDispatcher.launch {
            loginUseCase()
        }
    }

    private suspend fun loginUseCase() {
        ioDispatcher.async {
            return@async useCase.getListCartoes(::success, ::error)
        }.await()
    }

    private fun success(listCartoes: List<CartoesModel>?){
        _cartoesActionView.postValue(CartoesActionView.SuccessCall(listCartoes))
        GlobalScope.coroutineContext.cancelChildren()
    }

    private fun error(error: String){
        _cartoesActionView.postValue( CartoesActionView.ErrorCall(error))
        GlobalScope.coroutineContext.cancelChildren()
    }

}