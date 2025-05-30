package com.example.movie.data

import androidx.room.*
import com.example.movie.model.TopMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopMovieDao {
    @Query("SELECT * FROM top_movies")
    fun getAll(): Flow<List<TopMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TopMovieEntity>)

    @Query("DELETE FROM top_movies")
    suspend fun clear()
}
