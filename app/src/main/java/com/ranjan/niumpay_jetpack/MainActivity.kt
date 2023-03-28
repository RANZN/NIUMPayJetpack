package com.ranjan.niumpay_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ranjan.niumpay_jetpack.model.UserCard
import com.ranjan.niumpay_jetpack.ui.theme.NIUMPayJetpackTheme
import com.ranjan.niumpay_jetpack.ui.views.CardItem
import com.ranjan.niumpay_jetpack.ui.views.EmptyCardItem
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
                    HomeUI()
                }
            }
        }
    }
}

@Composable
fun HomeUI() {
    val list = ArrayList<UserCard>()
    repeat(20) {
        list.add(UserCard(1234, "234234234323", 321,"", "ranjan", "", "", "", ""))
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(modifier = Modifier) {
            items(items = list) { userCardDetails ->
                CardItem(card = userCardDetails)
            }
            items(1) {
                EmptyCardItem()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val userCard = UserCard(1234, "23423", 321, "", "ranjan", "", "", "", "")
//    CardItem(card = userCard)
}
