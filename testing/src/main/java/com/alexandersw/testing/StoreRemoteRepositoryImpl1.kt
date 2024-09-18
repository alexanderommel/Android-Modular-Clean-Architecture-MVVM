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

class StoreRemoteRepositoryFaker5 : StoreRemoteRepository {
    override suspend fun getStores(near: Location): UseCaseResponse<List<Store>> {
        return UseCaseResponse.Success(test_data_stores)
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

    override suspend fun getStore(id: Int, bearerToken: String): UseCaseResponse<Store> {
        return UseCaseResponse.Success(test_data_stores[0])
    }

    override suspend fun getProduct(id: Int, bearerToken: String): UseCaseResponse<Product> {
        return UseCaseResponse.Success(test_data_products1[0])
    }
}
