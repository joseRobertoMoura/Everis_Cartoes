package com.example.everis_cartoes.di.cartoes

import com.example.everis_cartoes.usecase.cartoes.CartoesUseCase
import com.example.everis_cartoes.usecase.cartoes.CartoesUseCaseImpl
import org.koin.dsl.module

val cartoesUseCaseModule = module {
    factory<CartoesUseCase> { CartoesUseCaseImpl(   get()  ) }
}