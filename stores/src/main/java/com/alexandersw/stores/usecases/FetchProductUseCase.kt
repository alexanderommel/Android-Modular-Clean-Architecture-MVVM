package com.alexandersw.stores.usecases

import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.BusinessRuleFailure
import com.alexandersw.common.UseCaseResponse


class FetchProductUseCase(
    private val repository: StoreRemoteRepository,
    private val userApi: UserApiInteractor
) {
    suspend operator fun invoke(id: Int): UseCaseResponse<Product> {

        println("retrieving product with id $id")

        val auth = userApi.isAuthenticated()
        if (!auth) {
            return UseCaseResponse.Error(BusinessRuleFailure.NotAuthorized)
        }

        val token = userApi.getAuthenticatedUser().getBearerToken()

        when (val res = repository.getProduct(id, token)) {
            is UseCaseResponse.Error -> return res
            is UseCaseResponse.Success -> return res
        }

    }
}