package com.alexandersw.api.dto

data class ShoppingCartDto(
    val storeId: Int,
    val items: List<LineItemDto>
)
