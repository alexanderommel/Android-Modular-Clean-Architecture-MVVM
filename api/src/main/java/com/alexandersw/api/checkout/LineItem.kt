package com.alexandersw.api.checkout

import com.alexandersw.api.stores.Product
import com.alexandersw.common.Money
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class LineItem(
    val lineItemId: Int,
    val product: Product,
    val quantity: Int
) {
    val totalAmount: Money
        get() {
            val individualPrice = BigDecimal(product.price.amount.toString())
            val quantityBD = BigDecimal(quantity)
            val total = individualPrice.multiply(quantityBD)
            return Money(amount = total, currency = Money.Currency.USD)
        }
}
