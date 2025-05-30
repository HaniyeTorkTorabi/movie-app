package com.example.movie.data

import androidx.room.*
import com.example.movie.model.LatestMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LatestMovieDao {
    @Query("SELECT * FROM latest_movies")
    fun getAll(): Flow<List<LatestMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<LatestMovieEntity>)

    @Query("DELETE FROM latest_movies")
    suspend fun clear()
}
