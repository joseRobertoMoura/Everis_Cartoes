package com.example.everis_cartoes.data.model

import retrofit2.Call
import retrofit2.http.GET

interface MockApi {

    @GET("demandaShadow")
    fun getDataDemandaShadowApi(): Call<List<CartoesModel>>
}