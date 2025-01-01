package com.alexandersw.persistence.checkout

import androidx.room.*
import androidx.room.Entity
import com.alexandersw.api.checkout.ShoppingCart
import com.alexandersw.api.stores.Store
import com.alexandersw.common.Location
import com.alexandersw.common.PaymentCard
import com.alexandersw.common.PaymentMethod
import kotlinx.serialization.Serializable

@Entity(tableName = "checkout_table")
data class CheckoutEntity(
    @PrimaryKey val id: String,
    val userId: String,
    @Embedded val store: Store,
    @Embedded val deliveryLocation: Location= Location("","",""),
    val requestUtensils: Boolean=false,
    val note: String?,
    val deliveryInstructions: String?,
    //@Embedded val paymentMethod: PaymentMethod=PaymentMethod.CASH,
    @Embedded val paymentCard: PaymentCard?,
     val shoppingCart: ShoppingCart // Needs type converter
)

