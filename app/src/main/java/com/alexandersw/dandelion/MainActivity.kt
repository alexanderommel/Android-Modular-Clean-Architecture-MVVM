package com.alexandersw.dandelion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.alexandersw.dandelion.ui.theme.DandelionTheme
import com.alexandersw.network.NetworkModule
import com.alexandersw.persistence.PersistenceModule
import com.alexandersw.stores_ui.screens.CatalogueScreen
import com.alexandersw.stores_ui.screens.ProductDetailScreen
import com.alexandersw.stores_ui.screens.StoresScreen
import com.alexandersw.testing.test_data_products1
import com.alexandersw.testing.test_data_stores
import com.alexandersw.testing.test_data_user1
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                    //StoresScreen(navController = rememberNavController(), user = test_data_user1)
                    //ProductDetailScreen(navController = rememberNavController(), product = test_data_products1[0], store = test_data_stores[0])
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Andr,    oid")
    }
}