package com.alexandersw.api.stores

import com.alexandersw.common.Money
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Money
)
