package com.alexandersw.checkout.usecases

import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutLocalRepository
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.BusinessRuleFailure
import com.alexandersw.common.UseCaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchCheckoutsUseCase(
    private val localRepo: CheckoutLocalRepository,
    private val userApi: UserApiInteractor,
    private val storesApi: StoresApiInteractor
) {

    suspend fun invoke(): Flow<List<Checkout>>{

        val auth = userApi.isAuthenticated()
        if (!auth) {
            return emptyFlow()
        }

        val user = userApi.getAuthenticatedUser()


        return flow{
            while(true){
                val checkouts = localRepo.flowOfAll(user.id)
                checkouts.collect{ list ->
                    emit(list)
                }
            }
        }.flowOn(Dispatchers.IO)

    }
}