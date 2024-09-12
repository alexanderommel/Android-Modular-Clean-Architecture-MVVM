package com.alexandersw.stores_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandersw.api.stores.Store
import com.alexandersw.common.Location
import com.alexandersw.stores_ui.R
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Black40
import com.alexandersw.ui_dandelion.ui.theme.Black80
import com.alexandersw.ui_dandelion.ui.theme.Green40

@Composable
fun StoreView(store: Store) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            ,
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            val image: Painter = painterResource(id = loadImageFromAssets(store.storeImage))
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = store.name,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = store.deliveryTime,
                    color = Black40,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Shipping fee: ${store.deliveryFee}",
                    color = Black40,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$",
                    color = Green40
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun StorePreview() {
    AppTheme {
        StoreView(store = store1)
    }
}

private val store1 = Store(1, "Store 1", "30 mins", "5.00", "store1.jpg", "store1.jpg", 4.0,"5 km", Location("Quito 6 de Diciembre",
1.0.toString(), 1.0.toString()
))