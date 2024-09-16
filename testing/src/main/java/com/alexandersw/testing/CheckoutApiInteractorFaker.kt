package com.alexandersw.testing

import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutApiInteractor
import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.common.UseCaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckoutApiInteractorFaker: CheckoutApiInteractor {
    override suspend fun addLineItem(
        lineItem: LineItemDto,
        storeId: Int
    ): UseCaseResponse<Boolean> {
        return UseCaseResponse.Success(true)
    }

    override suspend fun getAll(): Flow<List<Checkout>> {
        return flow {
            var data: List<Checkout> = ArrayList()
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}