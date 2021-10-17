package com.summerdewyes.mvvm_check_bang.repository

import com.summerdewyes.mvvm_check_bang.db.FeedDao
import com.summerdewyes.mvvm_check_bang.models.Feed
import javax.inject.Inject

class FeedRepository @Inject constructor(
    val feedDao: FeedDao
) {

    suspend fun upsertFeed(feed: Feed) = feedDao.upsert(feed)

    suspend fun deleteFeed(feed: Feed) = feedDao.deleteFeedItems(feed)

    fun getAllFeedItems() = feedDao.getAllFeedItems()

}