package com.alexandersw.api.stores

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategory(
    val id: Int,
    val name: String,
    val products: List<Product>
)
