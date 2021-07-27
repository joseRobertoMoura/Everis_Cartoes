package com.example.everis_cartoes.di

import com.example.everis_cartoes.ui.splashScreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenViewModelModule = module {
    viewModel{
        SplashScreenViewModel(
            statusUserFireBase = get()
        )
    }
}