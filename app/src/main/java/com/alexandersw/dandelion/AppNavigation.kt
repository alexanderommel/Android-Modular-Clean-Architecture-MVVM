package com.alexandersw.dandelion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Store
import com.alexandersw.stores_ui.screens.CatalogueScreen
import com.alexandersw.stores_ui.screens.ProductDetailScreen
import com.alexandersw.stores_ui.screens.StoresScreen
import com.alexandersw.stores_ui.viewmodels.StoresViewModel
import com.alexandersw.testing.test_data_user1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val storesViewModel: StoresViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "storesScreen"
    ) {



        composable("storesScreen") {
            StoresScreen(navController = navController,user = test_data_user1, storesViewModel = storesViewModel)
        }
        composable("catalogueScreen") { backStackEntry ->
            CatalogueScreen(navController = navController, storesViewModel = storesViewModel)
        }
        composable("productScreen/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            ProductDetailScreen(navController,productId = productId ?: 0, storesViewModel = storesViewModel)
        }
    }
}
