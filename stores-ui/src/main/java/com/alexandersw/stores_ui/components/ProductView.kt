package com.alexandersw.stores_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandersw.api.stores.Product
import com.alexandersw.common.Money
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Black40
import com.alexandersw.ui_dandelion.ui.theme.Black80
import java.math.BigDecimal

@Composable
fun ProductView(product: Product, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(3.dp))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Text(
                text = product.description,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = Black80,
                modifier = Modifier.padding(top = 1.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Price: ",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    text = product.price.formattedAmount,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
        val image: Painter = painterResource(id = loadImageFromAssets(product.imageUrl))
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview(){
    AppTheme {
        ProductView(product = Product(1,"Hamburger of Gods","Es una hamburguesa muy sabrosa que deberías probar al menos una vez en tu vida o te arrepentirás.","hamburger",
            Money(BigDecimal.TEN,Money.Currency.USD)
        )
        )
    }
}