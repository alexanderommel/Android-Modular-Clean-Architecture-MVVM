package com.alexandersw.api.stores

import kotlinx.serialization.Serializable

@Serializable
data class Catalogue(
    val categories: List<ProductCategory>
)
