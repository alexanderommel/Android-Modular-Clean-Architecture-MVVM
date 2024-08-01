package com.alexandersw.api.stores

import com.alexandersw.common.Location
import com.alexandersw.common.UseCaseResponse

interface StoreRemoteRepository {
    suspend fun getStores(near: Location): UseCaseResponse<List<Store>>
    suspend fun getCatalogue(storeId: Int): UseCaseResponse<Catalogue>
}
