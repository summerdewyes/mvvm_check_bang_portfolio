package com.summerdewyes.mvvm_check_bang.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.summerdewyes.mvvm_check_bang.models.Item

@Database(
    entities = [Item::class],
    version = 1
)

abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookItemDao(): BookDao

}