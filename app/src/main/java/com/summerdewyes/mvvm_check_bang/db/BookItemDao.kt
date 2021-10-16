package com.summerdewyes.mvvm_check_bang.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.summerdewyes.mvvm_check_bang.models.Item

@Dao
interface BookItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(bookItem: Item): Long

    @Query("SELECT * FROM bookItems")
    fun getAllBookItems(): LiveData<List<Item>>

    @Delete
    suspend fun deleteBookItems(bookItem: Item)
}

