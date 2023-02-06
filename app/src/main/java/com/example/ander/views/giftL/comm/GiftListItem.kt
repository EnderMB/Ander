package com.example.ander.views.giftL.comm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.ander.R
import com.example.ander.gifts.Gift

@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun GiftListItem(
    gift: Gift,
    onItemClick: (String) -> Unit
) {
    Card{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { onItemClick(gift.id) }
        ){
            Image(
                painter = rememberImagePainter(R.drawable.gifticon),
                contentDescription = "",
                modifier = Modifier
                    .width(86.dp)
                    .height(120.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = gift.nom,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = gift.precio,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color(0xFF32175A)
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = gift.desc,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color(0xFF32175A)
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                }

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(28),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF6E3DE2)
                    )
                ) {
                    Text(
                        text = "Show",
                        color = Color(0xFF32175A)
                    )
                }
            }
        }
    }
}