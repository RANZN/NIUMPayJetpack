package com.ranjan.niumpay_jetpack.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ranjan.niumpay_jetpack.model.UserCard

@Composable
@Preview(showBackground = true)
fun CardItemPreview() {
    val userCard = UserCard(1234, "23423", 321, "", "ranjan", "", "", "", "")

    CardItem(card = userCard)


}

@Composable
fun CardItem(card: UserCard) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = card.cardImage),
            contentDescription = "cardImage",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "**** ${
                    card.cardNumber.substring(
                        card.cardNumber.length - 4, card.cardNumber.length
                    )
                }", color = Color.Red
            )
        }
    }
}

@Composable
fun EmptyCardItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LINK ADD-ON CARD",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(color = MaterialTheme.colors.primary)
                    .padding(horizontal = 25.dp, vertical = 8.dp))
        }
    }
}
