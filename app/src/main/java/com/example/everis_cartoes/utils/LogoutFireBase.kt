package com.example.everis_cartoes.utils

import com.google.firebase.auth.FirebaseAuth

object LogoutFireBase {
    suspend fun logoutFireBase(){
        FirebaseAuth.getInstance().signOut()
    }
}