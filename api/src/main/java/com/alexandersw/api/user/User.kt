package com.alexandersw.api.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: String,
    var accessToken: String,
    var firstname: String,
    var email: String,
) {

    fun getBearerToken(): String{
        return "Bearer ${accessToken}"
    }

}