package com.alexandersw.testing

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.ProductCategory
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoreCategory
import com.alexandersw.common.Location
import com.alexandersw.common.Money
import java.math.BigDecimal

class StoreTestData {
}

val test_data_stores = listOf(
    Store(1, "Kfc 6 de Diciembre", "30 mins", "5.00", "store1.jpg", "store1.jpg", 4.0,"5 km", Location("Quito 6 de Diciembre",
        1.0.toString(), 1.0.toString()
    )
    ),
    Store(2, "Purple House Restaurant", "20 mins", "3.70", "store2", "store2.jpg", 4.5, "4 km", Location("Ambato perimetral",1.0.toString(),1.0003.toString()))
)


val test_data_products1: List<Product> = listOf(
    Product(
        productId = 1,
        name = "Monster Hamburguer",
        description = "La hamburguesa monstruo X1 tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "hamburger",
        price = Money(amount = BigDecimal.valueOf(1.2), currency = Money.Currency.USD)
    ),
    Product(
        productId = 2,
        name = "Master's Hamburguer Gow",
        description = "La hamburguesa master X22222 tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "hamburger",
        price = Money(amount = BigDecimal.valueOf(10), currency = Money.Currency.USD)
    )
)

val test_data_products2: List<Product> = listOf(
    Product(
        productId = 3,
        name = "Funny Pizza",
        description = "La hamburguesa monstruo X1 tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "hamburger",
        price = Money(amount = BigDecimal.valueOf(12), currency = Money.Currency.USD)
    ),
    Product(
        productId = 4,
        name = "The Greatest's World Pizza",
        description = "La hamburguesa master X22222 tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "product1_pizza",
        price = Money(amount = BigDecimal.ONE, currency = Money.Currency.USD)
    )
)

val test_data_products3: List<Product> = listOf(
    Product(
        productId = 5,
        name = "Funny Pizza Alternative",
        description = "La hamburguesa monstruo Alternative tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "product1_pizza",
        price = Money(amount = BigDecimal.valueOf(12), currency = Money.Currency.USD)
    ),
    Product(
        productId = 6,
        name = "The Greatest's World Pizza Alternative",
        description = "La hamburguesa master Alternative tiene los siguientes ingredientes: carne, queso, tomates pequeños, aceitinas, queso parmesano, y lechuga.",
        imageUrl = "product1_pizza",
        price = Money(amount = BigDecimal.valueOf(25.50), currency = Money.Currency.USD)
    )
)

val test_data_restaurantCategories: List<StoreCategory> = listOf(
    StoreCategory(id = 1, name = "Pizza", imageName = "test_resource_pizza_logo"),
    StoreCategory(id = 2, name = "Ice Cream", imageName = "test_resource_icecream_logo"),
    StoreCategory(id = 3, name = "Hamburguer", imageName = "test_resource_hamburguer_logo"),
    StoreCategory(id = 4, name = "Asian Food", imageName = "test_resource_japanesefood_logo"),
    StoreCategory(id = 5, name = "Chicken", imageName = "test_resource_chicken_logo")
)

val test_data_productCategories: List<ProductCategory> = listOf(
    ProductCategory(id = 1, name = "Hamburguesas", products = test_data_products1),
    ProductCategory(id = 2, name = "Pizzas", products = test_data_products2),
    ProductCategory(id = 3, name = "Helados", products = test_data_products3)
)

var test_data_catalog = Catalogue(categories = test_data_productCategories)