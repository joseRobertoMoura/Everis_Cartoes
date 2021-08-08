package com.example.everis_cartoes.repository.cartoes

import com.example.everis_cartoes.data.model.CartoesModel

interface CartoesRepository {
    suspend fun getDataApiCartoes(
        onSuccess: (List<CartoesModel>?) -> Unit,
        onError: (String) -> Unit
    )
}