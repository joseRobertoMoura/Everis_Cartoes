package com.example.everis_cartoes.di

import com.example.everis_cartoes.data.model.MockApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://608bf6279f42b20017c3d34c.mockapi.io/"

val netWorkModule = module {
    single {

        val client = OkHttpClient().newBuilder().build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}