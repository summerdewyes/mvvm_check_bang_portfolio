package com.summerdewyes.mvvm_check_bang.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.summerdewyes.mvvm_check_bang.models.Feed

@Database(
    entities = [Feed::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class FeedDatabase : RoomDatabase() {

    abstract fun getFeedItemDao(): FeedDao

}