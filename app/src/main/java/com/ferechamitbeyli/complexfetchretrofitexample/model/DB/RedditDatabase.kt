package com.ferechamitbeyli.complexfetchretrofitexample.model.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ferechamitbeyli.complexfetchretrofitexample.model.Children
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data1
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data2
import com.ferechamitbeyli.complexfetchretrofitexample.model.Response

@Database(entities = arrayOf(Response::class, Data1::class, Children::class, Data2::class), version = 1)
@TypeConverters(Converters::class)
abstract class RedditDatabase: RoomDatabase() {
    abstract fun redditDao(): RedditDao

    companion object {
        @Volatile
        private var instance: RedditDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RedditDatabase::class.java,
            "redditdatabase"
        ).build()
    }

}