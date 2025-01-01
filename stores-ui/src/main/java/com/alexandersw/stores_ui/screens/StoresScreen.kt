package com.alexandersw.stores_ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexandersw.api.stores.Store
import com.alexandersw.api.user.User
import com.alexandersw.stores_ui.components.LocationView
import com.alexandersw.stores_ui.components.StoreCategoryRowView
import com.alexandersw.stores_ui.components.StoreView
import com.alexandersw.stores_ui.components.categories
import com.alexandersw.stores_ui.viewmodels.StoresViewModel
import com.alexandersw.testing.CheckoutApiInteractorFaker
import com.alexandersw.testing.StoreApiInteractorFaker
import com.alexandersw.testing.UserApiInteractorFaker
import com.alexandersw.testing.test_data_stores
import com.alexandersw.testing.test_data_user1
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Black40
import com.alexandersw.ui_dandelion.ui.theme.Black80

@Composable
fun StoresScreen(navController: NavController, user: User, modifier: Modifier = Modifier, storesViewModel: StoresViewModel = hiltViewModel()){

    val checkouts by storesViewModel.checkoutsFlow.collectAsState(initial = emptyList())
    val stores by storesViewModel.stores.collectAsState(initial = emptyList())

    Column(modifier = modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(16.dp), horizontalAlignment = Alignment.Start)
        {

            Text(text = "Restaurants near you",
                color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)

        Text(text = "¡Hello ${user.firstname}!", modifier = Modifier.padding(top = 16.dp),
            color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Look what we've got for you.",
            color = Black80, fontSize = 16.sp, fontWeight = FontWeight.Medium)

        LocationView(modifier = Modifier.padding(top = 10.dp))

        Text(text = "Categories",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        StoreCategoryRowView(categories = categories, modifier = Modifier.padding(top = 20.dp))

        Text(text = "Restaurants close to you",
            modifier = Modifier.padding(top = 20.dp),
            color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(stores.size){ storeIndex ->
                Button(onClick = {
                    storesViewModel.setStorePicked(stores[storeIndex])
                    navController.navigate("catalogueScreen")
                }, shape = RectangleShape, elevation = null, border = null,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    StoreView(store = stores[storeIndex])
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (storeIndex < stores.size - 1) {  // Skip divider for the last item
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }

            if (checkouts.isNotEmpty()){
                Text(text = "Checkouts")
            }

    }
}


@Preview(showBackground = true)
@Composable
fun StoresScreenPreview(){
    val navController = rememberNavController()
    AppTheme {
        StoresScreen(navController=navController,user = test_data_user1, storesViewModel = StoresViewModel(
            StoreApiInteractorFaker(),CheckoutApiInteractorFaker()))
    }
}