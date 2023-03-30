package com.ranjan.niumpay_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ranjan.niumpay_jetpack.model.TransactionItem
import com.ranjan.niumpay_jetpack.model.UserCard
import com.ranjan.niumpay_jetpack.ui.theme.NIUMPayJetpackTheme
import com.ranjan.niumpay_jetpack.ui.views.CardsUI
import com.ranjan.niumpay_jetpack.ui.views.QuickActionsUI
import com.ranjan.niumpay_jetpack.ui.views.ToolbarUI
import com.ranjan.niumpay_jetpack.ui.views.TransactionsUI

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
                    repeat(20) {
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

@Composable
fun HomeUI(
    cardList: ArrayList<UserCard>, transactionList: ArrayList<TransactionItem>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue.copy(alpha = 0.8f))
        ) {
            ToolbarUI()
            CardsUI(cardList)
        }
        Column(Modifier.padding(horizontal = 20.dp)) {
            QuickActionsUI()
            TransactionsUI(transactionList)
        }
    }
}

