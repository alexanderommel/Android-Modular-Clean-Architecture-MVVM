package com.alexandersw.testing

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.ProductCategory
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.common.Location
import com.alexandersw.common.Money
import com.alexandersw.common.UseCaseResponse
import java.math.BigDecimal

class StoreRemoteRepositoryImpl1 : StoreRemoteRepository {
    override suspend fun getStores(near: Location): UseCaseResponse<List<Store>> {
        val stores = listOf(
            Store(1, "Store 1", "30 mins", "5.00", "store1.jpg", "store1.jpg", 4.0,"5 km", Location("Quito 6 de Diciembre",
                1.0.toString(), 1.0.toString()
            )
            ),
            Store(2, "Store 2", "20 mins", "3.00", "store2.jpg", "store2.jpg", 4.5, "4 km", Location("Ambato perimetral",1.0.toString(),1.0003.toString()))
        )
        return UseCaseResponse.Success(stores)
    }

    override suspend fun getCatalogue(storeId: Int): UseCaseResponse<Catalogue> {
        val products = listOf(
            Product(1, "Product 1", "Description 1", "url1", Money(BigDecimal("10.00"), Money.Currency.USD)),
            Product(2, "Product 2", "Description 2", "url2", Money(BigDecimal("15.00"), Money.Currency.USD))
        )
        val productCategory = ProductCategory(1, "Category 1", products)
        val catalogue = Catalogue(listOf(productCategory))
        return UseCaseResponse.Success(catalogue)
    }
}
