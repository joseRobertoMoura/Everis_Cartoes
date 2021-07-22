package com.example.everis_cartoes.di

import com.example.everis_cartoes.usecase.login.LoginUseCase
import com.example.everis_cartoes.usecase.login.LoginUseCaseImpl
import org.koin.dsl.module

val loginUseCaseModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl() }
}