package com.alexandersw.stores

import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.BusinessRuleFailure
import com.alexandersw.common.UseCaseResponse

class FetchStoresUseCase(
    private val repository: StoreRemoteRepository,
    private val userApi: UserApiInteractor
) {

    suspend fun invoke(): UseCaseResponse<List<Store>> {
        val auth = userApi.isAuthenticated()
        if (!auth) {
            return UseCaseResponse.Error(BusinessRuleFailure.NotAuthorized)
        }

        val deliveryLoc = userApi.getUserPreferredDeliveryLocation() ?: return UseCaseResponse.Error(BusinessRuleFailure.LocationNotFound)

        return when (val res = repository.getStores(near = deliveryLoc)) {
            is UseCaseResponse.Error -> UseCaseResponse.Error(res.failure)
            is UseCaseResponse.Success -> UseCaseResponse.Success(res.data)
        }
    }
}
