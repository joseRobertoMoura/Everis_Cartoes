package com.example.everis_cartoes.di

import com.example.everis_cartoes.di.cartoes.cartoesComponent
import com.example.everis_cartoes.di.home.homeViewModelModule
import com.example.everis_cartoes.di.network.networkComponent

val appModule = listOf(
        loginUseCaseModule,
        loginViewModelModule,
        dispachersModule,
        loginRepositoryModule,
        statusUserFireBaseModule,
        homeViewModelModule,
        splashScreenViewModelModule
) + networkComponent + cartoesComponent