package com.alexandersw.stores_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexandersw.api.stores.Store
import com.alexandersw.stores_ui.components.CatalogueCategoryRowView
import com.alexandersw.stores_ui.components.ProductView
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.stores_ui.viewmodels.CatalogueViewModel
import com.alexandersw.testing.StoreApiInteractorFaker
import com.alexandersw.testing.test_data_productCategories
import com.alexandersw.testing.test_data_stores
import com.alexandersw.ui_dandelion.ui.theme.AppTheme

@Composable
fun StoreImage(storeImage: Int) {

}

@Composable
fun CatalogueScreen(
    store: Store,
    catalogueViewModel: CatalogueViewModel = hiltViewModel(),
    navController: NavController
) {
    val catalogueState by catalogueViewModel.catalogueState.collectAsState()

    // Equivalent of onAppear in SwiftUI
    LaunchedEffect(Unit) {
        catalogueViewModel.fetchCatalogueData(store)
    }

    if (false) {
        // Show loading screen (equivalent to IndeterminateScreenLoadingView)
        CircularProgressIndicator(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    } else {
        catalogueState?.let { catalogue ->
            Column {
                // Store Details and Image
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = loadImageFromAssets(store.storeImage)),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.Crop
                    )



                    // Back Button
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(top = 32.dp, start = 16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    // Store details
                    Column(
                        modifier = Modifier
                            .padding(top = 136.dp)
                            .align(Alignment.BottomStart)
                            .clip(RoundedCornerShape(16.dp))  // Apply rounded corners
                            .background(Color.White)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()  // Fills the width, similar to .frame(width: .infinity)
                                .height(0.dp)  // Sets the height to 136, like .frame(height: 136)
                        )
                        Row(modifier = Modifier.padding(start = 94.dp, top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(store.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color.Blue)
                            Text(store.rating.toString(), style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(imageVector = Icons.Default.Place, contentDescription = "Distance")
                            Text(store.distance, style = MaterialTheme.typography.bodySmall)
                        }
                        Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp) ,horizontalArrangement = Arrangement.spacedBy(8.dp) // This will space items evenly
                        ) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Address")
                            Text(store.location.address, style = MaterialTheme.typography.bodySmall)

                        }
                        Row(modifier = Modifier.padding(start = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Delivery Fee")
                            Text("El costo de envío es: ${store.deliveryFee}$", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    Image(
                        painter = painterResource(id = loadImageFromAssets(store.storeImage)),  // Load image from resources
                        contentDescription = null,
                        contentScale = ContentScale.Crop,  // Makes the image resizable and crops it to fit
                        modifier = Modifier


                            .padding(top = 108.dp, start = 20.dp)
                            .size(64.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape)
                    )



                }

                Divider(modifier = Modifier.padding(top = 16.dp), color = Color.Gray, thickness = 0.5.dp)

                CatalogueCategoryRowView(categories = test_data_productCategories, modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp))

                // Catalogue List
                LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                    items(catalogue.categories.size) { categoryIndex ->
                        val category = catalogue.categories[categoryIndex]
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Bold
                        )

                        var i=0
                        category.products.forEach { product ->
                            ProductView(
                                product = product,
                                onClick = {
                                    navController.navigate("productScreen/${product.productId}")
                                }
                            )

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        if (i < category.products.size - 1) {  // Skip divider for the last item
                            Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        i++
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogueScreenPreview(){
    val navController = rememberNavController()
    AppTheme {
        CatalogueScreen(store = test_data_stores[0], navController = navController, catalogueViewModel = CatalogueViewModel(StoreApiInteractorFaker()))
    }
}