package com.summerdewyes.mvvm_check_bang

data class BookResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)