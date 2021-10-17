package com.summerdewyes.mvvm_check_bang.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.summerdewyes.mvvm_check_bang.models.Item

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(bookItem: Item): Long

    @Delete
    suspend fun deleteBookItems(bookItem: Item)

    @Query("SELECT * FROM bookItems")
    fun getAllBookItems(): LiveData<List<Item>>
}

