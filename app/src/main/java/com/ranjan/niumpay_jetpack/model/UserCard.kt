package com.ranjan.niumpay_jetpack.model

import androidx.annotation.DrawableRes
import com.ranjan.niumpay_jetpack.R

data class UserCard(
    val cardHashId: Long,
    val cardNumber: String,
    val cvv: Int,
    val exp: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val email: String,
    val countryCode: String,
    @DrawableRes val cardImage: Int = R.drawable.card_back
)

