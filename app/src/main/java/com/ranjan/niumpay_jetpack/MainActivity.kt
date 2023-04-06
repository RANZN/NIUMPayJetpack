package com.ranjan.niumpay_jetpack

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard
import com.ranjan.niumpay_jetpack.ui.theme.NIUMPayJetpackTheme
import com.ranjan.niumpay_jetpack.ui.theme.regalBlue
import com.ranjan.niumpay_jetpack.ui.theme.waterBlue
import com.ranjan.niumpay_jetpack.ui.views.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NIUMPayJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    window.statusBarColor = regalBlue.toArgb()
                    val list = ArrayList<UserCard>()
                    val transactionList = ArrayList<TransactionItem>()
                    repeat(2) {
                        list.add(UserCard(1234, "234234234323", 321, "", "ranjan", "", "", "", ""))
                    }
                    repeat(100) {
                        transactionList.add(
                            TransactionItem(
                                (0..1000).random(),
                                arrayOf("USD", "IND").random(),
                                arrayOf("Debit", "Credit").random(),
                                "Remittance",
                                arrayOf("prakash", "ranjan").random(),
                                "8:40 PM",
                                "Done",
                                ('a'..'z').random().uppercase()
                            )
                        )
                    }
                    HomeUI(list, transactionList)
                }
            }
        }
    }
}

@Composable
fun HomeUI(
    cardList: ArrayList<UserCard>, transactionList: ArrayList<TransactionItem>
) {
    val refreshCoroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    SwipeRefresh(
        state = swipeRefreshState, onRefresh = {
            refreshCoroutineScope.launch {
                isRefreshing = true
                delay(2000L)
                isRefreshing = false
            }
        }, modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                regalBlue,
                                waterBlue,
                            )
                        )
                    )
            ) {
                val activity = LocalContext.current as Activity
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ActionIcons(Icons.Outlined.ExitToApp,
                        contentDescription = "Exit",
                        onClickListener = { activity.finishAffinity() })
                    Spacer(modifier = Modifier.weight(1f))
                    ActionIcons(Icons.Outlined.Notifications, contentDescription = "Notification")
                    ActionIcons(Icons.Outlined.Info, contentDescription = "Info")
                    ProfileIcon("Ranjan")
                }
                CardsUI(cardList)
            }
            Column(Modifier.padding(horizontal = 20.dp)) {
                QuickActionsUI()
                TransactionsUI(transactionList)
            }
        }
    }
}


