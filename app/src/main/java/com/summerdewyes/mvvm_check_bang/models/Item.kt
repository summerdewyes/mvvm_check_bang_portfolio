package com.summerdewyes.mvvm_check_bang.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookItems"
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val price: String,
    val pubdate: String,
    val publisher: String,
    val title: String
)