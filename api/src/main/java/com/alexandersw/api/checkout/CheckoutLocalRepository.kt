package com.alexandersw.api.checkout

import kotlinx.coroutines.flow.Flow

interface CheckoutLocalRepository {
    suspend fun insertCheckout(c: Checkout, userId: String): Checkout
    suspend fun updateCheckout(c: Checkout, userId: String): Checkout
    suspend fun getAllCheckoutsByUserId(userId: String): List<Checkout>
    suspend fun flowOfAll(userId: String): Flow<List<Checkout>>
}

