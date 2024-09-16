package com.alexandersw.stores_ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandersw.api.stores.ProductCategory
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Black80

@Composable
fun CatalogueCategoryRowView(categories: List<ProductCategory>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 4.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Categories",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )

        Text(
            text = "We've got ${categories.size} categories for you!",
            color = Black80,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp, // Equivalent to .caption
            modifier = Modifier.padding(top = 4.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            items(categories){ category ->
                CatalogueProductCategoryView(category = category)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogueCategoryRowPreview(){
    AppTheme {
        CatalogueCategoryRowView(categories = product_sets)
    }
}

val product_sets = listOf(
    ProductCategory(1,"Hamburgers 1", products_set),
    ProductCategory(2,"Hamburgers 2", products_set),
    ProductCategory(3,"Hamburgers 3", products_set),
    ProductCategory(4,"Hamburgers 4", products_set)
)