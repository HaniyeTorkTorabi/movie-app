package com.example.movie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.data.LatestMovieDao
import com.example.movie.data.TopMovieDao
import com.example.movie.model.LatestMovieEntity
import com.example.movie.model.TopMovieEntity
import com.example.movie.model.UserProfileEntity
import com.example.movie.data.UserProfileDao


@Database(
    entities = [TopMovieEntity::class, LatestMovieEntity::class, UserProfileEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topMovieDao(): TopMovieDao
    abstract fun latestMovieDao(): LatestMovieDao
    abstract fun userProfileDao(): UserProfileDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration() // برای پاک‌کردن خودکار دیتابیس در تغییر نسخه
                    .build().also { INSTANCE = it }
            }
        }
    }
}

