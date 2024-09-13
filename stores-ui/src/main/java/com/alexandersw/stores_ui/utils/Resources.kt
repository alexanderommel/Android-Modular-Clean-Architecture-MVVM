package com.alexandersw.stores_ui.utils

import com.alexandersw.stores_ui.R

fun loadImageFromAssets(name: String): Int {
    // Assuming the images are placed in the res/drawable directory
    return when (name) {
        "hamburger" -> R.drawable.test_resource_hamburger
        "hamburger_logo" -> R.drawable.test_resource_hamburguer_logo
        "pizza_logo" -> R.drawable.test_resource_pizza_logo
        "icecream_logo" -> R.drawable.test_resource_icecream_logo
        "japanesefood_logo" -> R.drawable.test_resource_japanesefood_logo
        "chicken_logo" -> R.drawable.test_resource_chicken_logo
        "location_icon" -> R.drawable.location_icon
        "restaurant" -> R.drawable.location_icon
        "store2" -> R.drawable.test_resource_restaurant2
        else -> R.drawable.test_resource_kfc_store // Fallback to a default image
    }
}
