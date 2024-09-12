package com.alexandersw.stores_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandersw.api.stores.StoreCategory
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.ui_dandelion.ui.theme.AppTheme

@Composable
fun StoreCategoryView(storeCategory: StoreCategory) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(modifier = Modifier
            .size(44.dp)
            .shadow(1.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White),
            shape = CircleShape) {
            val image: Painter = painterResource(id = loadImageFromAssets(storeCategory.imageName))
            Image(painter = image, contentDescription = null, modifier = Modifier
                .size(24.dp)
                .padding(10.dp))
        }
        Text(text = storeCategory.name, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun StoreCategoryPreview(){
    AppTheme {
        StoreCategoryView(storeCategory = cat1)
    }
}

private val cat1 = StoreCategory(1,"Hamburgers","hamburger_logo")