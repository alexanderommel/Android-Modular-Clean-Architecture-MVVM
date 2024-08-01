package com.alexandersw.api.checkout

import com.alexandersw.api.stores.Store
import com.alexandersw.common.Money
import kotlinx.serialization.Serializable
import java.math.BigDecimal


@Serializable
data class ShoppingCart(
    val id: Int,
    val items: List<LineItem>,
    val store: Store
) {
    val totalAmount: Money
        get() {
            val total = items
                .map { it.totalAmount }
                .fold(BigDecimal.ZERO) { acc, amount -> acc.add(amount.amount) }
            return Money(amount = total, currency = Money.Currency.USD)
        }
}
