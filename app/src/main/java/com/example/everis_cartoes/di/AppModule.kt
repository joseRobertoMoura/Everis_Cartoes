package com.example.everis_cartoes.di

import org.koin.dsl.module

val appModule = listOf(
        loginUseCaseModule,
        loginViewModelModule
)