package com.alexandersw.persistence.converters

import androidx.room.TypeConverter
import com.alexandersw.api.checkout.LineItem
import com.alexandersw.api.checkout.ShoppingCart
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.ProductCategory
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreCategory
import com.alexandersw.common.Location
import com.alexandersw.common.Money
import com.alexandersw.common.PaymentMethod
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromShoppingCart(cart: ShoppingCart): String {
        return Gson().toJson(cart)
    }

    @TypeConverter
    fun toShoppingCart(cartString: String): ShoppingCart {
        val type = object : TypeToken<ShoppingCart>() {}.type
        return Gson().fromJson(cartString, type)
    }

    // Product Converters
    @TypeConverter
    fun fromProduct(product: Product): String {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun toProduct(productString: String): Product {
        val type = object : TypeToken<Product>() {}.type
        return Gson().fromJson(productString, type)
    }

    @TypeConverter
    fun fromLocation(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String): Location {
        val type = object : TypeToken<Product>() {}.type
        return Gson().fromJson(locationString, type)
    }

    // LineItem Converters
    @TypeConverter
    fun fromLineItem(lineItem: LineItem): String {
        return Gson().toJson(lineItem)
    }

    @TypeConverter
    fun toLineItem(lineItemString: String): LineItem {
        val type = object : TypeToken<LineItem>() {}.type
        return Gson().fromJson(lineItemString, type)
    }

    // Money Converters
    @TypeConverter
    fun fromMoney(money: Money): String {
        return Gson().toJson(money)
    }

    @TypeConverter
    fun toMoney(moneyString: String): Money {
        val type = object : TypeToken<Money>() {}.type
        return Gson().fromJson(moneyString, type)
    }

    // BigDecimal Converters (for Money.amount)
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
    }

    // Store Converters (if needed)
    @TypeConverter
    fun fromStore(store: Store): String {
        return Gson().toJson(store)
    }

    @TypeConverter
    fun toStore(storeString: String): Store {
        val type = object : TypeToken<Store>() {}.type
        return Gson().fromJson(storeString, type)
    }

    // StoreCategory Converters
    @TypeConverter
    fun fromStoreCategory(category: StoreCategory): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toStoreCategory(categoryString: String): StoreCategory {
        val type = object : TypeToken<StoreCategory>() {}.type
        return Gson().fromJson(categoryString, type)
    }

    // ProductCategory Converters
    @TypeConverter
    fun fromProductCategory(category: ProductCategory): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toProductCategory(categoryString: String): ProductCategory {
        val type = object : TypeToken<ProductCategory>() {}.type
        return Gson().fromJson(categoryString, type)
    }

    @TypeConverter
    fun fromPaymentMethod(paymentMethod: PaymentMethod): String {
        return paymentMethod.name
    }

    @TypeConverter
    fun toPaymentMethod(paymentMethodString: String): PaymentMethod {
        return PaymentMethod.valueOf(paymentMethodString)
    }

    // Add other converters for Product, LineItem, Money, etc.
}
