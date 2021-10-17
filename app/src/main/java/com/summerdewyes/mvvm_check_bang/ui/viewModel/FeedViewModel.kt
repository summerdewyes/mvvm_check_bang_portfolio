package com.summerdewyes.mvvm_check_bang.ui.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.summerdewyes.mvvm_check_bang.models.Feed
import com.summerdewyes.mvvm_check_bang.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    val feedRepository: FeedRepository
) : ViewModel() {


    fun saveFeed(feed: Feed) = viewModelScope.launch {
        feedRepository.upsertFeed(feed)
    }

    fun deleteFeed(feed: Feed) = viewModelScope.launch {
        feedRepository.deleteFeed(feed)
    }

    fun getSavedFeed() = feedRepository.getAllFeedItems()

}