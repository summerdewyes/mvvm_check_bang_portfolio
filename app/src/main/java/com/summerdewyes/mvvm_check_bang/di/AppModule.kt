package com.summerdewyes.mvvm_check_bang.di

import android.content.Context
import androidx.room.Room
import com.summerdewyes.mvvm_check_bang.db.BookDatabase
import com.summerdewyes.mvvm_check_bang.db.FeedDatabase
import com.summerdewyes.mvvm_check_bang.util.Constants.Companion.BOOK_ITEM_DATABASE_NAME
import com.summerdewyes.mvvm_check_bang.util.Constants.Companion.FEED_ITEM_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBookItemDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app, BookDatabase::class.java,
            BOOK_ITEM_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideBookItemDao(db: BookDatabase) = db.getBookItemDao()

    @Singleton
    @Provides
    fun provideFeedItemDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app, FeedDatabase::class.java,
            FEED_ITEM_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideFeedItemDao(db: FeedDatabase) = db.getFeedItemDao()


}