package com.example.everis_cartoes.di

import com.example.everis_cartoes.di.home.homeViewModelModule

val appModule = listOf(
        loginUseCaseModule,
        loginViewModelModule,
        dispachersModule,
        loginRepositoryModule,
        statusUserFireBaseModule,
        homeViewModelModule,
        splashScreenViewModelModule
)