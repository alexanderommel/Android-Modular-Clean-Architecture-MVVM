package com.alexandersw.checkout.api

import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutApiInteractor
import com.alexandersw.api.checkout.CheckoutLocalRepository
import com.alexandersw.api.checkout.ShoppingCart
import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.checkout.usecases.AddLineItemUseCase
import com.alexandersw.checkout.usecases.FetchCheckoutsUseCase
import com.alexandersw.common.UseCaseResponse
import kotlinx.coroutines.flow.Flow

class CheckoutApiInteractorImpl(val storeApi: StoresApiInteractor, val userApi: UserApiInteractor, val checkoutLocalRepo: CheckoutLocalRepository): CheckoutApiInteractor {

    private val add_item_uc: AddLineItemUseCase
    private val fetchCheckoutsUseCase: FetchCheckoutsUseCase

    init {
        add_item_uc = AddLineItemUseCase(checkoutLocalRepo,userApi,storeApi)
        fetchCheckoutsUseCase = FetchCheckoutsUseCase(checkoutLocalRepo,userApi,storeApi)
    }

    override suspend fun addLineItem(
        lineItem: LineItemDto,
        storeId: Int
    ): UseCaseResponse<Boolean> {
        return add_item_uc.invoke(lineItem, storeId)
    }

    override suspend fun getAll(): Flow<List<Checkout>> {
        return fetchCheckoutsUseCase.invoke()
    }

}