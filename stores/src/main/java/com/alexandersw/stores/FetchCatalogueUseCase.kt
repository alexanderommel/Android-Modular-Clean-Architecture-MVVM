package com.alexandersw.stores

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.common.UseCaseResponse

class FetchCatalogueUseCase(private val repository: StoreRemoteRepository) {

    suspend fun invoke(storeId: Int): UseCaseResponse<Catalogue> {
        val res = repository.getCatalogue(storeId)

        return when (res) {
            is UseCaseResponse.Error -> UseCaseResponse.Error(res.failure)
            is UseCaseResponse.Success -> UseCaseResponse.Success(res.data)
        }
    }
}
