package com.alexandersw.dandelion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Store
import com.alexandersw.stores_ui.screens.CatalogueScreen
import com.alexandersw.stores_ui.screens.StoresScreen
import com.alexandersw.testing.test_data_user1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "storesScreen"
    ) {
        composable("storesScreen") {
            StoresScreen(navController = navController,user = test_data_user1)
        }
        composable("catalogueScreen") { backStackEntry ->
            val storeJson = backStackEntry.arguments?.getString("store")!!
            //val type = object: TypeToken<Catalogue>() {}.type
            val s = Gson().fromJson(storeJson, Store::class.java)!!
            CatalogueScreen(store = s, navController = navController)
        }
        //composable("productScreen") { DetailScreen(navController) }
    }
}
