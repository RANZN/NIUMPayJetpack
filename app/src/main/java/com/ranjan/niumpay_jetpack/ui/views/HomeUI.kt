package com.ranjan.niumpay_jetpack.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import androidx.core.graphics.toColorInt
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ranjan.niumpay_jetpack.R
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard
import com.ranjan.niumpay_jetpack.ui.theme.birdFlower
import com.ranjan.niumpay_jetpack.ui.theme.getUserIconText
import com.ranjan.niumpay_jetpack.ui.theme.regalBlue
import com.ranjan.niumpay_jetpack.ui.theme.waterBlue
import java.util.*


@Composable
fun ActionIcons(
    icon: ImageVector,
    size: Dp = 36.dp,
    contentDescription: String = "",
    padding: Dp = 5.dp,
    onClickListener: (() -> Unit)? = null
) {
    val iconModifier = Modifier
        .clip(CircleShape)
        .size(size)
        .clickable {
            onClickListener?.invoke()
        }
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = iconModifier
            .padding(padding)
            .shadow(2.dp, CircleShape),
        tint = Color.White
    )
}

@Composable
fun ProfileIcon(userName: String, size: Dp = 40.dp, onClickListener: (() -> Unit)? = null) {
    val userChar = userName.getUserIconText()
    Box(modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .clickable {
            onClickListener?.invoke()
        }
        .background(Color((0..225).random(), (0..225).random(), (0..225).random()))
        .padding(8.dp)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = userChar,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
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
        backgroundColor = Color.Transparent, modifier = Modifier.fillMaxWidth(), elevation = 20.dp
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
                    }", color = Color.White
                )
            }
        }
    }
}


@Composable
fun CurrenciesUI(balance: Double, currencyType: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clip(
                RoundedCornerShape(
                    10.dp
                )
            )
            .background(Color.White)
    ) {
        Icon(painterResource(id = R.drawable.ic_wallet), "wallet")
        Column {
            Text(text = "$ $balance", fontWeight = FontWeight.Bold)
            Text(text = currencyType)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")

    }
}

@Composable
fun EmptyCardItem() {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.padding(bottom = 20.dp),
        elevation = 20.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(111 / 69f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            regalBlue.copy(alpha = 0.9f), waterBlue.copy(0.9f)
                        )
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LINK ADD-ON CARD",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .clickable {

                    }
                    .background(color = Color("#66b6d2".toColorInt()))
                    .padding(horizontal = 60.dp, vertical = 8.dp))
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
                iconId = R.drawable.ic_explore,
                contentDescription = "Explore",
            )
        }
    }
}

@Composable
fun QuickActionIcons(
    @DrawableRes iconId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClickListener: (() -> Unit)? = null,
) {
    val iconModifier = modifier
        .size(42.dp)
        .clip(CircleShape)
        .clickable {
            onClickListener?.invoke()
        }
        .background(color = birdFlower)
        .padding(8.dp)
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

@OptIn(ExperimentalFoundationApi::class)
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
            Text(text = "Show all", color = Color.Blue, modifier = Modifier
                .clickable {

                }
                .padding(horizontal = 10.dp, vertical = 2.dp))
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            transactionList.groupBy { it.transactionDate }.toSortedMap(Comparator.reverseOrder())
                .forEach { (date, transactionItem) ->
                    stickyHeader {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colors.background)
                        )
                    }
                    items(items = transactionItem) { item ->
                        TransactionItemUI(transaction = item)
                        Divider()
                    }
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
    val transactionItem =
        TransactionItem(12, "USD", "Debit", "Remittance", "ranjan", "Now", "Done", "")
    Column {
//        cardItem(card = userCard)
//        TransactionItemUI(transactionItem)
//        QuickActionsUI()
//        ActionIcons(Icons.Outlined.AccountBox, contentDescription = "hello")
        CurrenciesUI(143.32, "USD - United States Dollar")
    }
}

