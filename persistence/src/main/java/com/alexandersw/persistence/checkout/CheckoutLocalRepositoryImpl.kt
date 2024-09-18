package com.alexandersw.persistence.checkout

import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckoutLocalRepositoryImpl(
    private val checkoutDao: CheckoutDao
) : CheckoutLocalRepository {

    override suspend fun insertCheckout(c: Checkout, userId: String): Checkout {
        val entity = c.toEntity()
        checkoutDao.insertCheckout(entity)
        return c
    }

    override suspend fun updateCheckout(c: Checkout, userId: String): Checkout {
        val entity = c.toEntity()
        checkoutDao.updateCheckout(entity)
        return c
    }

    override suspend fun getAllCheckoutsByUserId(userId: String): List<Checkout> {
        return checkoutDao.getAllCheckoutsByUserId(userId).map { it.toDomain() }
    }

    override suspend fun flowOfAll(userId: String): Flow<List<Checkout>> {
        return checkoutDao.flowOfAll(userId).map { list ->
            list.map { it.toDomain() }
        }
    }

    private fun Checkout.toEntity(): CheckoutEntity {
        return CheckoutEntity(
            id = this.id,
            userId = this.userId,
            store = this.store,  // Store can be embedded directly
            deliveryLocation = this.deliveryLocation,  // Location can be embedded directly
            requestUtensils = this.requestUtensils,
            note = this.note,
            deliveryInstructions = this.deliveryInstructions,
            //paymentMethod = this.paymentMethod,  // PaymentMethod can be embedded
            paymentCard = this.paymentCard,  // PaymentCard can be embedded (nullable)
            shoppingCart = this.shoppingCart  // Will be converted using Gson TypeConverter
        )
    }

    private fun CheckoutEntity.toDomain(): Checkout {
        return Checkout(
            id = this.id,
            userId = this.userId,
            store = this.store,  // Store is embedded and doesn't need conversion
            shoppingCart = this.shoppingCart,  // Will be converted from Gson using TypeConverter
            requestUtensils = this.requestUtensils,
            note = this.note,
            deliveryLocation = this.deliveryLocation,  // Location is embedded
            deliveryInstructions = this.deliveryInstructions,
            //paymentMethod = this.paymentMethod,  // PaymentMethod is embedded
            paymentCard = this.paymentCard  // PaymentCard is embedded (nullable)
        )
    }

}
