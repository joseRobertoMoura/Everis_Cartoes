package com.example.everis_cartoes.di.cartoes

import com.example.everis_cartoes.ui.cartoes.CartoesViewModel
import com.example.everis_cartoes.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartoesViewModelModule = module {
    viewModel{
        CartoesViewModel(
            useCase = get(),
            mainDispatcher = get(),
            ioDispatcher = get()
        )
    }
}