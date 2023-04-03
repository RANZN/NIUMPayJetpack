package com.ranjan.niumpay_jetpack.ui.theme

fun String.getUserIconText(): String {
    val strArr = this.split(" ")
    return try {
        "${strArr[0][0]}${strArr[1][0]}"
    } catch (e: Exception) {
        "${strArr[0][0]}"
    }.uppercase()
}