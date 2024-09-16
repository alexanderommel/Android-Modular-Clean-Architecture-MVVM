package com.alexandersw.api.checkout

import com.alexandersw.api.stores.Store
import com.alexandersw.common.Location
import com.alexandersw.common.PaymentCard
import com.alexandersw.common.PaymentMethod
import kotlinx.serialization.Serializable

@Serializable
data class Checkout(
    val id: String,
    val userId: String,
    val shoppingCart: ShoppingCart,
    val store: Store,
    val requestUtensils: Boolean=false,
    val note: String? = null,
    val deliveryLocation: Location = Location("","",""),
    val deliveryInstructions: String? = null,
    val paymentMethod: PaymentMethod = PaymentMethod.CASH,
    val paymentCard: PaymentCard? = null
)