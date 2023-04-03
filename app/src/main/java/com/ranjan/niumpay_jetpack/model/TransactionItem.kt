package com.ranjan.niumpay_jetpack.model

data class TransactionItem(
    var amount: Int,
    val amountCurrency: String,
    val transactionType: String,
    val transactionCategory: String,
    val merchantName: String,
    val time: String,
    val status: String,
    val transactionDate: String
)

