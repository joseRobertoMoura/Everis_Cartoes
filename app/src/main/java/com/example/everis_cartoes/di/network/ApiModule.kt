package com.example.everis_cartoes.di

import com.example.everis_cartoes.data.model.MockApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val apiModule = module{
    factory {  get<Retrofit>().create(MockApi::class.java)}
}