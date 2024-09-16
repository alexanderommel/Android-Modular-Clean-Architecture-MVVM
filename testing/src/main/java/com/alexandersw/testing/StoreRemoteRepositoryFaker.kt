package com.alexandersw.testing

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreRemoteRepository
import com.alexandersw.common.BusinessRuleFailure
import com.alexandersw.common.Location
import com.alexandersw.common.UseCaseResponse

class StoreRemoteRepositoryFaker : StoreRemoteRepository {
    private val stores = test_data_stores
    private val catalogues = mapOf(
        1 to test_data_catalog,  // Catalogue for store 1
        2 to test_data_catalog   // Assuming same catalogue for both stores for simplicity
    )

    // Simulating a successful UseCaseResponse
    private fun <T : Any> successResponse(data: T): UseCaseResponse<T> {
        return UseCaseResponse.Success(data)
    }

    // Simulating a failed UseCaseResponse
    private fun <T : Any> errorResponse(message: BusinessRuleFailure): UseCaseResponse<T> {
        return UseCaseResponse.Error(message)
    }

    override suspend fun getStores(near: Location): UseCaseResponse<List<Store>> {
        // Return all stores as we are not implementing location-based filtering for now
        return successResponse(stores)
    }

    override suspend fun getCatalogue(storeId: Int): UseCaseResponse<Catalogue> {
        // Retrieve the catalogue for the specified store ID
        val catalogue = catalogues[storeId]
        return if (catalogue != null) {
            successResponse(catalogue)
        } else {
            errorResponse(BusinessRuleFailure.CustomErrorMessage("Catalogue not found for store ID: $storeId"))
        }
    }

    override suspend fun getStore(id: Int, bearerToken: String): UseCaseResponse<Store> {
        // Simulate token check (basic check for non-empty token)
        if (bearerToken.isBlank()) {
            return errorResponse(BusinessRuleFailure.CustomErrorMessage("Invalid token"))
        }

        // Find the store by ID
        val store = stores.find { it.storeId == id }
        return if (store != null) {
            successResponse(store)
        } else {
            errorResponse(BusinessRuleFailure.CustomErrorMessage("Store not found for ID: $id"))
        }
    }

    override suspend fun getProduct(id: Int, bearerToken: String): UseCaseResponse<Product> {
        // Simulate token check
        if (bearerToken.isBlank()) {
            return errorResponse(BusinessRuleFailure.CustomErrorMessage("Store not found for ID: $id"))
        }

        // Flatten all products from all categories in the catalogue
        val allProducts = test_data_productCategories.flatMap { it.products }

        // Find the product by ID
        val product = allProducts.find { it.productId == id }
        return if (product != null) {
            successResponse(product)
        } else {
            errorResponse(BusinessRuleFailure.CustomErrorMessage("Product not found for ID: $id"))
        }
    }
}
