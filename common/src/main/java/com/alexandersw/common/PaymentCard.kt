package com.alexandersw.common

import kotlinx.serialization.Serializable

@Serializable
data class PaymentCard(
    val paymentCardId: String,
    val remoteId: String,
    val nickname: String?,
    val lastFourDigits: String,
    val bank: String,
    val expirationMonth: Int,
    val expirationYear: Int
) {
    val name: String
        get() = nickname ?: "$bank **** $lastFourDigits"
}
