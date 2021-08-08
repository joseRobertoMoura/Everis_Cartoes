package com.example.everis_cartoes.di.cartoes

import com.example.everis_cartoes.repository.cartoes.CartoesRepository
import com.example.everis_cartoes.repository.cartoes.CartoesRepositoryImpl
import org.koin.dsl.module

val cartoesRepositoyModule = module{
    factory<CartoesRepository> {  CartoesRepositoryImpl( get()) }
}