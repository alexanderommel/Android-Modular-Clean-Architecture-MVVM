package com.alexandersw.common

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val address: String,
    val latitude: String,
    val longitude: String
)
