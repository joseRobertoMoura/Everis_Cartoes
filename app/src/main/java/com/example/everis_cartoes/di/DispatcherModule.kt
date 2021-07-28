package com.example.everis_cartoes.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispachersModule = module{
    factory { Dispatchers.Main }
    factory { Dispatchers.IO }
}