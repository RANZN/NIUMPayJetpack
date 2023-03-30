package com.ranjan.niumpay_jetpack.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ranjan.niumpay_jetpack.R
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard

@Composable
fun ToolbarUI() {
    Row(modifier = Modifier.padding(10.dp)) {
        ActionIcons(Icons.Outlined.ExitToApp, contentDescription = "Exit")
        Spacer(modifier = Modifier.weight(1f))
        ActionIcons(Icons.Outlined.Notifications, contentDescription = "Notification")
        ActionIcons(Icons.Outlined.Info, contentDescription = "Info")
        ActionIcons(Icons.Outlined.AccountCircle, contentDescription = "Profile")
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
        modifier = iconModifier
            .padding(padding)
            .shadow(2.dp, CircleShape)
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardsUI(cardList: ArrayList<UserCard>) {
    HorizontalPager(
        count = cardList.size + 1,
        modifier = Modifier,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        itemSpacing = 10.dp,
        verticalAlignment = Alignment.Top
    ) {
        if (it < cardList.size) Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CardItem(card = cardList[it])
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Add To Google Pay")
            }
        }
        else EmptyCardItem()
    }
}

@Composable
fun CardItem(card: UserCard) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box {
            Image(
                painter = painterResource(id = card.cardImage),
                contentDescription = "cardImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
            Column(
                modifier = Modifier
                    .matchParentSize()
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
    }
}


@Composable
fun EmptyCardItem() {
    Card(
        shape = RoundedCornerShape(14.dp), backgroundColor = Color.LightGray.copy(alpha = 0.8f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(111 / 69f),
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
fun QuickActionsUI() {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Quick Actions",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionIcons(
                iconId = R.drawable.ic_transfer,
                contentDescription = "Send",
            )
            QuickActionIcons(
                iconId = R.drawable.ic_exchange_op,
                contentDescription = "Exchange",
            )
            QuickActionIcons(
                iconId = R.drawable.ic_remittance,
                contentDescription = "Remittance",
            )
            QuickActionIcons(
                iconId = R.drawable.ic_transfer,
                contentDescription = "Explore",
            )
        }
    }
}

@Composable
fun QuickActionIcons(
    @DrawableRes iconId: Int, contentDescription: String, onClickListener: (() -> Unit)? = null
) {
    val iconModifier = Modifier
        .size(41.60.dp)
        .clip(CircleShape)
        .clickable {
            onClickListener?.invoke()
        }
        .background(color = Color.Magenta)
        .padding(7.80.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(
            painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = iconModifier,
            tint = Color.Unspecified
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
fun TransactionsUI(transactionList: ArrayList<TransactionItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Latest transactions", fontWeight = FontWeight.Bold)
            Text(text = "Show all", color = Color.Blue)
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(items = transactionList) {
                TransactionItemUI(transaction = it)
                Divider()
            }
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
@Preview(showBackground = true)
fun CardItemPreview() {
    val userCard = UserCard(1234, "23423", 321, "", "ranjan", "", "", "", "")
    val transactionItem = TransactionItem(12, "USD", "Debit", "Remittance", "ranjan", "Now", "Done")
    Column {
//        cardItem(card = userCard)
//        TransactionItemUI(transactionItem)
//        QuickActionsUI()
//        ActionIcons(Icons.Outlined.AccountBox, contentDescription = "hello")
    }
}

