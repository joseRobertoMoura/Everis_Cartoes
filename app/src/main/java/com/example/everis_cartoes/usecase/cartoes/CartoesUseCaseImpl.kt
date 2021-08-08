package com.example.everis_cartoes.usecase.cartoes

import com.example.everis_cartoes.data.model.CartoesModel
import com.example.everis_cartoes.repository.cartoes.CartoesRepository

class CartoesUseCaseImpl(
    val repository: CartoesRepository
) : CartoesUseCase {
    override suspend fun getListCartoes(
        onSuccess: (List<CartoesModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.getDataApiCartoes(onSuccess, onError)
    }
}