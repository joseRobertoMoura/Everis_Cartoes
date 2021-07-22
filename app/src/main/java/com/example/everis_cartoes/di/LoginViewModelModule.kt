package com.example.everis_cartoes.di

import com.example.everis_cartoes.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(
            useCase = get()
        )
    }
}