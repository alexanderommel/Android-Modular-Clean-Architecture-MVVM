package com.alexandersw.stores_ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.alexandersw.api.stores.StoreCategory
import com.alexandersw.ui_dandelion.ui.theme.AppTheme

@Composable
fun StoreCategoryRowView(categories: List<StoreCategory>, modifier: Modifier = Modifier) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier) {
        items(categories){ c ->
            StoreCategoryView(storeCategory = c)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreCategoryRowPreview(){
    AppTheme {
        StoreCategoryRowView(categories = categories)
    }
}

private val categories = listOf(
    StoreCategory(1,"Hamburguer","hamburger_logo"),
    StoreCategory(2,"Pizza","pizza_logo"),
    StoreCategory(3,"Ice cream","icecream_logo"),
    StoreCategory(4,"Chicken","chicken_logo"),
    StoreCategory(5,"Japanese food","japanesefood_logo"),
    StoreCategory(6,"Japanese food","japanesefood_logo"),
    StoreCategory(7,"Japanese food","japanesefood_logo"),
    StoreCategory(8,"Japanese food","japanesefood_logo"),
    StoreCategory(9,"Japanese food","japanesefood_logo")
)