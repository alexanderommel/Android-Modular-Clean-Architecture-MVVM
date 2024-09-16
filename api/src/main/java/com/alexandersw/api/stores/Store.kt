package com.alexandersw.api.stores

import com.alexandersw.common.Location
import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val storeId: Int,
    val name: String,
    val deliveryTime: String,
    val deliveryFee: String,
    val storeImage: String,
    val cartImage: String,
    val rating: Double,
    val distance: String,
    val location: Location
)
