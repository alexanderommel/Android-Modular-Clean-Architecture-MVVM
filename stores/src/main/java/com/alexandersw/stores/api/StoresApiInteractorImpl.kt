package com.alexandersw.stores.api

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.UseCaseResponse
import com.alexandersw.stores.usecases.FetchCatalogueUseCase
import com.alexandersw.stores.usecases.FetchProductUseCase
import com.alexandersw.stores.usecases.FetchStoreUseCase
import com.alexandersw.stores.usecases.FetchStoresUseCase


class StoresApiInteractorImpl(
    private val storeRemoteRepo: StoreRemoteRepository,
    private val userApiInteractor: UserApiInteractor
) : StoresApiInteractor {

    private val fetchStoresUseCase = FetchStoresUseCase(storeRemoteRepo, userApiInteractor)
    private val fetchCatalogueUseCase = FetchCatalogueUseCase(storeRemoteRepo)
    private val fetchStoreUseCase = FetchStoreUseCase(storeRemoteRepo, userApiInteractor)
    private val fetchProductUseCase = FetchProductUseCase(storeRemoteRepo, userApiInteractor)

    override suspend fun getStores(): UseCaseResponse<List<Store>> {
        return fetchStoresUseCase.invoke()
    }

    override suspend fun getCatalogue(store: Store): UseCaseResponse<Catalogue> {
        return fetchCatalogueUseCase.invoke(store.storeId)
    }

    override suspend fun getStoreById(id: Int): UseCaseResponse<Store> {
        return fetchStoreUseCase.invoke(id)
    }

    override suspend fun getProductById(id: Int): UseCaseResponse<Product> {
        return fetchProductUseCase.invoke(id)
    }
}