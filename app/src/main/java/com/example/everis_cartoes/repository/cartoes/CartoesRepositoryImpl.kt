package com.example.everis_cartoes.repository.cartoes

import com.example.everis_cartoes.data.model.CartoesModel
import com.example.everis_cartoes.data.model.MockApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartoesRepositoryImpl(
    private val api: MockApi
) : CartoesRepository {
    override suspend fun getDataApiCartoes(
        onSuccess: (List<CartoesModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        val returnApi = api.getDataDemandaShadowApi()

        returnApi.enqueue(object : Callback<List<CartoesModel>>{
            override fun onResponse(
                call: Call<List<CartoesModel>>,
                response: Response<List<CartoesModel>>
            ) {
                if(response.isSuccessful){
                    onSuccess.invoke(response.body())
                }else{
                    onError.invoke("Error")
                }
            }

            override fun onFailure(call: Call<List<CartoesModel>>, t: Throwable) {
                onError.invoke(t.message.toString())
            }

        })
    }
}