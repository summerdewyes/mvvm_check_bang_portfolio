package com.summerdewyes.mvvm_check_bang.repository

import com.summerdewyes.mvvm_check_bang.api.RetrofitInstance
import com.summerdewyes.mvvm_check_bang.db.BookDao
import com.summerdewyes.mvvm_check_bang.models.Item
import javax.inject.Inject

class BookRepository @Inject constructor(
    val bookDao: BookDao
) {

    suspend fun upsertBook(book: Item) = bookDao.upsert(book)

    suspend fun deleteBook(book: Item) = bookDao.deleteBookItems(book)

    suspend fun searchBook(searchQuery: String, displayNumber: Int) =
        RetrofitInstance.api.searchForBook(searchQuery, displayNumber)

    fun getAllBookItems(book: Item) = bookDao.getAllBookItems()


}