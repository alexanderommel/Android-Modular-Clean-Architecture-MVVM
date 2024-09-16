package com.alexandersw.common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

@Serializable
data class Money(
    @Serializable(with = BigDecimalAsStringSerializer::class)
    val amount: BigDecimal=BigDecimal.ZERO,
    val currency: Currency = Currency.USD
) {
    enum class Currency {
        USD, EUR, GBP, CNY
    }


    val formattedAmount: String
        get() = "$${amount.toPlainString()}"

    fun addAndUpdate(plus: Money): Money {
        val updatedAmount = this.amount.add(plus.amount)
        return this.copy(amount = updatedAmount)
    }

    fun multiplyAndUpdate(factor: Money): Money {
        val updatedAmount = this.amount.multiply(factor.amount)
        return this.copy(amount = updatedAmount)
    }

    constructor() : this(BigDecimal.ZERO, Currency.USD)

}

object BigDecimalAsStringSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        return BigDecimal(decoder.decodeString())
    }
}