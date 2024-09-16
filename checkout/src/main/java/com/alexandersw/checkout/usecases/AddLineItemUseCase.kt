package com.alexandersw.checkout.usecases
import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutLocalRepository
import com.alexandersw.api.checkout.LineItem
import com.alexandersw.api.checkout.ShoppingCart
import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.BusinessRuleFailure
import com.alexandersw.common.PaymentMethod
import com.alexandersw.common.UseCaseResponse
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers


class AddLineItemUseCase(
    private val localRepo: CheckoutLocalRepository,
    private val userApi: UserApiInteractor,
    private val storesApi: StoresApiInteractor
) {

    suspend fun invoke(lineItem: LineItemDto, storeId: Int): UseCaseResponse<Boolean> {
        println("AddLineItemUseCase: Adding an item of product id ${lineItem.productId} with storeId $storeId to the shopping cart.")
        println("Adding ${lineItem.quantity} items")

        return withContext(Dispatchers.IO) {
            val auth = userApi.isAuthenticated()
            if (!auth) {
                return@withContext UseCaseResponse.Error(BusinessRuleFailure.NotAuthorized)
            }

            val deliveryLocation = userApi.getUserPreferredDeliveryLocation() ?: return@withContext UseCaseResponse.Error(BusinessRuleFailure.LocationNotFound)

            val user = userApi.getAuthenticatedUser()
            val checkouts = localRepo.getAllCheckoutsByUserId(userId = user.id)

            println("AddLineItemUseCase: There are ${checkouts.size} checkouts in the database for user ${user.id}")

            val storeRes = storesApi.getStoreById(storeId)
            val productRes = storesApi.getProductById(lineItem.productId)

            val store = when (storeRes) {
                is UseCaseResponse.Error -> return@withContext storeRes
                is UseCaseResponse.Success -> storeRes.data
            }

            val product = when (productRes) {
                is UseCaseResponse.Error -> return@withContext productRes
                is UseCaseResponse.Success -> productRes.data
            }

            var checkout: Checkout? = null
            var exists = false

            for (c in checkouts) {
                if (c.store.storeId == storeId) {
                    exists = true
                    checkout = c
                    checkout.shoppingCart.items
                    checkout.shoppingCart.items.add(LineItem(0,product,lineItem.quantity))
                    break
                }
            }

            if (!exists) {
                println("AddLineItemUseCase: Creating a new checkout")
                checkout = Checkout("0",user.id,
                    ShoppingCart(mutableListOf(LineItem(0,product,lineItem.quantity)),store),
                    store,false,null,deliveryLocation,null,PaymentMethod.CASH,null)
                localRepo.insertCheckout(checkout, user.id)
            } else {
                println("AddLineItemUseCase: Updating the checkout")
                localRepo.updateCheckout(checkout!!, user.id)
            }

            println("AddLineItemUseCase: Success")
            UseCaseResponse.Success(data = true)
        }
    }
}