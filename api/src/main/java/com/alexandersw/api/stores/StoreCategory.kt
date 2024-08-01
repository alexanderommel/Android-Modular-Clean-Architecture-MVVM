package com.alexandersw.api.stores

import kotlinx.serialization.Serializable

@Serializable
data class StoreCategory(
    val id: Int,
    val name: String,
    val imageName: String
)
