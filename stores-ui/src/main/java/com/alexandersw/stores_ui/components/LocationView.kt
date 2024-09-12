package com.alexandersw.stores_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.alexandersw.stores_ui.R
import com.alexandersw.stores_ui.utils.loadImageFromAssets
import com.alexandersw.ui_dandelion.ui.theme.AppTheme
import com.alexandersw.ui_dandelion.ui.theme.Black40
import com.alexandersw.ui_dandelion.ui.theme.Green40
import com.alexandersw.ui_dandelion.ui.theme.Green80

@Composable
fun LocationView() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(40.dp),
                shape = RoundedCornerShape(10.dp),
                color = Green80
            ){
                val image2: Painter = painterResource(id = loadImageFromAssets("location_icon"))
                Image(
                    painter = image2,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                    )
            }
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(
                    text = "You are here",
                    color = Black40,
                    fontSize = 12.sp
                )
                Text(
                    text = "Quito 6 de diciembre y avellana",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationPreview(){
    AppTheme {
        LocationView()
    }
}