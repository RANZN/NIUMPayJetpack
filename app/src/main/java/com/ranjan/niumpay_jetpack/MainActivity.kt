package com.ranjan.niumpay_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard
import com.ranjan.niumpay_jetpack.ui.theme.NIUMPayJetpackTheme
import com.ranjan.niumpay_jetpack.ui.views.*
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NIUMPayJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val list = ArrayList<UserCard>()
                    val transactionList = ArrayList<TransactionItem>()
                    repeat(10) {
                        list.add(UserCard(1234, "234234234323", 321, "", "ranjan", "", "", "", ""))
                        transactionList.add(
                            TransactionItem(
                                (0..1000).random(),
                                arrayOf("USD", "IND").random(),
                                arrayOf("Debit", "Credit").random(),
                                "Remittance",
                                arrayOf("prakash", "ranjan").random(),
                                "8:40 PM",
                                "Done"
                            )
                        )
                    }
                    HomeUI(list, transactionList)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeUI(
    cardList: ArrayList<UserCard>, transactionList: ArrayList<TransactionItem>
) {
    var height by remember { mutableStateOf(230.dp) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.background(color = Color.Blue.copy(alpha = 0.8f))) {
            Row(modifier = Modifier.padding(10.dp)) {
                ActionIcons(Icons.Outlined.ExitToApp, contentDescription = "Exit")
                Spacer(modifier = Modifier.weight(1f))
                ActionIcons(Icons.Outlined.Notifications, contentDescription = "Notification")
                ActionIcons(Icons.Outlined.Info, contentDescription = "Info")
                ActionIcons(Icons.Outlined.AccountCircle, contentDescription = "Profile")
            }

            HorizontalPager(
                count = cardList.size + 1,
                modifier = Modifier.padding(top = 5.dp),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
                itemSpacing = 10.dp
            ) {
                if (it < cardList.size) Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    height = cardItem(card = cardList[it])
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Add To Google Pay")
                    }
                }
                else EmptyCardItem(height = height)
            }
        }
        QuickActionsUI()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items = transactionList) {
                TransactionItemUI(transaction = it)
                Divider()
            }
        }
    }
}

