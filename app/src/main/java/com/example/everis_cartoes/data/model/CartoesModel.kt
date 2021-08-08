package com.example.everis_cartoes.data.model

import com.google.gson.annotations.SerializedName

data class CartoesModel(
    @SerializedName("id") val id:String?,
    @SerializedName("name") val name:String,
    @SerializedName("CardNumber") val CardNumber:String,
    @SerializedName("code") val code:String,
    @SerializedName("date") val date:String
)