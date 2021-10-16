package com.summerdewyes.mvvm_check_bang.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "feedItems"
)
data class Feed(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var timestamp: Long,
    var page: String,
    var content: String,
    var photo: Bitmap
)