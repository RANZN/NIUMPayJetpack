package com.ranjan.niumpay_jetpack.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard

@Composable
@Preview(showBackground = true)
fun CardItemPreview() {
    val userCard = UserCard(1234, "23423", 321, "", "ranjan", "", "", "", "")
    val transactionItem = TransactionItem(12, "USD", "Debit", "Remittance", "ranjan", "Now", "Done")
    Column {
//        cardItem(card = userCard)
//        TransactionItemUI(transactionItem)
//        QuickActionsUI()
//        ActionIcons(Icons.Outlined.AccountBox, contentDescription = "asdcasd")
    }
}

@Composable
fun cardItem(card: UserCard): Dp {
    var imageHeight by remember { mutableStateOf(0.dp) }
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        val density = LocalDensity.current
        Image(
            painter = painterResource(id = card.cardImage),
            contentDescription = "cardImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged {
                    imageHeight = with(density) { it.height.toDp() }
                },
        )
        Column(
            modifier = Modifier
                .height(imageHeight)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(), text = "**** ${
                    card.cardNumber.substring(
                        card.cardNumber.length - 4, card.cardNumber.length
                    )
                }", color = Color.Red
            )

        }
    }
    return imageHeight
}


@Composable
fun EmptyCardItem(height: Dp) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LINK ADD-ON CARD",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {

                    }
                    .background(color = MaterialTheme.colors.primary)
                    .padding(horizontal = 25.dp, vertical = 8.dp))
        }
    }
}

@Composable
fun TransactionItemUI(transaction: TransactionItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Text(text = transaction.merchantName, color = Color.Black.copy(alpha = 0.6f))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${if (transaction.transactionType == "Debit") "-" else ""}${transaction.amount} ${transaction.amountCurrency}",
            )
        }
        Text(text = transaction.transactionCategory)
        Text(text = transaction.time, color = Color.Black.copy(0.9f))
    }
}

@Composable
fun QuickActionsUI() {
    Column {
        Text(
            text = "Quick Actions",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            QuickActionIcons(
                icon = Icons.Rounded.Send,
                contentDescription = "Send",
            )
            QuickActionIcons(
                icon = Icons.Default.AccountBox,
                contentDescription = "Exchange",
            )
            QuickActionIcons(
                icon = Icons.Rounded.AccountCircle,
                contentDescription = "Remittance",
            )
            QuickActionIcons(
                icon = Icons.Default.ExitToApp,
                contentDescription = "Explore",
            )
        }
    }
}

@Composable
fun QuickActionIcons(
    icon: ImageVector, contentDescription: String, onClickListener: (() -> Unit)? = null
) {
    val iconModifier = Modifier
        .clip(CircleShape)
        .clickable {
            onClickListener?.invoke()
        }
        .background(color = Color.Yellow)
        .padding(15.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon, contentDescription = contentDescription, modifier = iconModifier
        )
        Text(
            modifier = Modifier,
            text = contentDescription,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
fun ActionIcons(
    icon: ImageVector,
    contentDescription: String = "",
    padding: Dp = 5.dp,
    onClickListener: (() -> Unit)? = null
) {
    val iconModifier = Modifier
        .clip(CircleShape)
        .clickable {
            onClickListener?.invoke()
        }
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = iconModifier.padding(padding)
    )

}