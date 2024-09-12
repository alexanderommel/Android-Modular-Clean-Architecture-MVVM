package com.alexandersw.stores_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.ProductCategory
import com.alexandersw.common.Money
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Green80
import java.math.BigDecimal

@Composable
fun CatalogueProductCategoryView(category: ProductCategory) {
    Box(
        modifier = Modifier
            .background(Green80, RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Text(
            text = category.name,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 14.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogueProductCategoryPreview(){
    AppTheme {
        CatalogueProductCategoryView(category = ProductCategory(1,"Hamburgers", products_set))
    }
}

val products_set = listOf(
    Product(1,"Hamburger of Gods 1","Es una hamburguesa muy sabrosa que deberías probar al menos una vez en tu vida o te arrepentirás.","hamburger",
        Money(BigDecimal.TEN, Money.Currency.USD)
    ),
    Product(2,"Hamburger of Gods 2","Es una hamburguesa muy sabrosa que deberías probar al menos una vez en tu vida o te arrepentirás.","hamburger",
        Money(BigDecimal.ONE, Money.Currency.USD)
    ),
    Product(3,"Hamburger of Gods 3","Es una hamburguesa muy sabrosa que deberías probar al menos una vez en tu vida o te arrepentirás.","hamburger",
        Money(BigDecimal.valueOf(5.50), Money.Currency.USD)
    )
)