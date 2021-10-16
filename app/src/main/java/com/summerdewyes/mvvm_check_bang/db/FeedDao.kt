package com.summerdewyes.mvvm_check_bang.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.summerdewyes.mvvm_check_bang.models.Feed

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(feedItem: Feed): Long

    @Query("SELECT * FROM feedItems")
    fun getAllFeedItems(): LiveData<List<Feed>>

    @Delete
    suspend fun deleteFeedItems(feedItem: Feed)

}