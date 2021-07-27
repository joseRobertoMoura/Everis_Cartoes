package com.example.everis_cartoes.di.home

import com.example.everis_cartoes.ui.home.HomeViewModel
import com.example.everis_cartoes.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel{
        HomeViewModel(
            statusUserFireBase = get()
        )
    }
}