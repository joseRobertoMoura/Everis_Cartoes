package com.example.everis_cartoes.usecase.cartoes

import com.example.everis_cartoes.data.model.CartoesModel

interface CartoesUseCase {

    suspend fun getListCartoes(onSuccess: (List<CartoesModel>?) -> Unit,
                           onError: (String) -> Unit)
}