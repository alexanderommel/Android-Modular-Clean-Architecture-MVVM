package com.alexandersw.common

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class PaymentCard(
    val id: String,
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
