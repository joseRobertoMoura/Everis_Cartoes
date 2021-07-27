package com.example.everis_cartoes.di

import com.example.everis_cartoes.ui.home.HomeViewModel
import com.example.everis_cartoes.ui.splashScreen.SpalshScreenActivity
import com.example.everis_cartoes.ui.splashScreen.SpalshScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenViewModelModule = module {
    viewModel{
        SpalshScreenViewModel(
            statusUserFireBase = get()
        )
    }
}