package com.example.everis_cartoes.utils

import android.content.Context
import com.example.everis_cartoes.R
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel

object VerifyLoginField {
    fun verifyLogin(email:String, senha:String,context:Context):LoginFireBaseModel{
        return if(email.isNotEmpty() && senha.isNotEmpty()){
            LoginFireBaseModel(email,senha,"")
        }else {
            LoginFireBaseModel("", "", context.getString(R.string.msg_empity_field))
        }
    }
}