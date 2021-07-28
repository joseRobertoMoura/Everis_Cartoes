package com.example.everis_cartoes.di

import com.example.everis_cartoes.utils.StatusUserFireBase
import org.koin.dsl.module

val statusUserFireBaseModule = module {
    factory { StatusUserFireBase(
        get(),
        get()
    ) }
}