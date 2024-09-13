package com.alexandersw.api.checkout

import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.common.UseCaseResponse
import kotlinx.coroutines.flow.Flow

interface CheckoutApiInteractor {
    suspend fun addLineItem(lineItem: LineItemDto, storeId: Int): UseCaseResponse<Boolean>
    suspend fun getAll(): Flow<List<Checkout>>
}
