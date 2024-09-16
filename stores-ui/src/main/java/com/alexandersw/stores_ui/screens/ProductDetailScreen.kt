package com.alexandersw.stores_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.Store
import com.alexandersw.common.Money
import com.alexandersw.stores_ui.R
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.stores_ui.viewmodels.CatalogueViewModel
import com.alexandersw.stores_ui.viewmodels.ProductViewModel
import com.alexandersw.testing.CheckoutApiInteractorFaker
import com.alexandersw.testing.StoreApiInteractorFaker
import com.alexandersw.testing.test_data_products1
import com.alexandersw.testing.test_data_stores
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Green80
import java.math.BigDecimal

@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    product: Product,
    store: Store
) {
    // State for quantity
    var quantity by remember { mutableStateOf(1) }

    // Observe itemAdded state
    val itemAdded by viewModel.itemAdded.collectAsState()

    // Navigate back to root if itemAdded is true
    LaunchedEffect(itemAdded) {
        if (itemAdded) {
            navController.popBackStack(navController.graph.startDestinationId, false)
        }
    }

    // Handle UI
    Column(modifier = Modifier) {

        Text(text = "${product.name}",
            color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 6.dp, bottom = 12.dp, start = 20.dp, end = 20.dp))

        Image(
            painter = painterResource(id = loadImageFromAssets(product.imageUrl)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)

        )

        Row(modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = store.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(modifier = Modifier.padding(top = 2.dp, start = 20.dp, end = 20.dp)) {
            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${product.price.amount}$",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary // Replace with your color
            )
        }

        Text(
            text = product.description,
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                ,
            color = Color.Gray // Replace with your color
        )

        Row(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp, start = 20.dp, end = 20.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 1) quantity -= 1 }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_circle_24),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Gray // Replace with your color
                )
            }

            Text(
                text = quantity.toString(),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            IconButton(onClick = { quantity += 1 }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_24),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary// Replace with your color
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {
                    val lineItem = LineItemDto(productId = product.productId, quantity = quantity)
                    viewModel.addLineItemToShoppingCart(storeId = store.storeId, lineItem = lineItem)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                val individualPrice = product.price
                val factor = Money(quantity.toBigDecimal(),Money.Currency.USD)
                val price = individualPrice.multiplyAndUpdate(factor)
                Text(
                    text = "Agregar ${price.formattedAmount}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductDetailPreview(){
    val navController = rememberNavController()
    AppTheme {
        ProductDetailScreen(navController = navController, viewModel = ProductViewModel(CheckoutApiInteractorFaker()) , product = test_data_products1[0], store = test_data_stores[0])
    }
}