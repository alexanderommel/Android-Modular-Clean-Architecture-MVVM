package com.alexandersw.testing

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.common.Location
import com.alexandersw.common.UseCaseResponse

class StoreApiInteractorFaker: StoresApiInteractor {

    val repo = StoreRemoteRepositoryFaker()

    override suspend fun getStores(): UseCaseResponse<List<Store>> {
        return repo.getStores(Location("","",""))
    }

    override suspend fun getCatalogue(from: Store): UseCaseResponse<Catalogue> {
        return repo.getCatalogue(from.storeId)
    }

    override suspend fun getStoreById(id: Int): UseCaseResponse<Store> {
        return  repo.getStore(id,"")
    }

    override suspend fun getProductById(id: Int): UseCaseResponse<Product> {
        return repo.getProduct(id,"")
    }
}