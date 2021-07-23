package com.example.everis_cartoes.di

import com.example.everis_cartoes.repository.login.LoginRepository
import com.example.everis_cartoes.repository.login.LoginRepositoryImpl
import org.koin.dsl.module

val loginRepositoryModule = module{
    factory<LoginRepository> {  LoginRepositoryImpl() }
}