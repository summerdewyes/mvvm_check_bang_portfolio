package com.summerdewyes.mvvm_check_bang.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.summerdewyes.mvvm_check_bang.models.BookResponse
import com.summerdewyes.mvvm_check_bang.models.Item
import com.summerdewyes.mvvm_check_bang.repository.BookRepository
import com.summerdewyes.mvvm_check_bang.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    val bookRepository: BookRepository
) : ViewModel() {


    val searchBook: MutableLiveData<Resource<BookResponse>> = MutableLiveData()
    var searchBookDisplay = 10

    fun searchBook(searchQuery: String) = viewModelScope.launch {
        searchBook.postValue(Resource.Loading())
        val response = bookRepository.searchBook(searchQuery, searchBookDisplay)
        searchBook.postValue(handleSearchBookResponse(response))

    }

    private fun handleSearchBookResponse(response: Response<BookResponse>) : Resource<BookResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveBook(item: Item) = viewModelScope.launch {
        bookRepository.upsertBook(item)
    }

    fun deleteBook(item: Item) = viewModelScope.launch {
        bookRepository.deleteBook(item)
    }

    fun getSavedBook() = bookRepository.getAllBookItems()

}