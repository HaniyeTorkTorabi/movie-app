package com.example.movie.data

import android.content.Context
import com.example.movie.api.movieAPI
import com.example.movie.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MovieRepository(context: Context) {
    private val db = AppDatabase.getInstance(context)
    private val topDao = db.topMovieDao()
    private val latestDao = db.latestMovieDao()

    fun getTopMovies(): Flow<List<Movie>> {
        return topDao.getAll().map { it.map { entity -> entity.toModel() } }
    }

    fun getLatestMovies(): Flow<List<Movie>> {
        return latestDao.getAll().map { it.map { entity -> entity.toModel() } }
    }

    suspend fun refreshTopMovies() {
        val remote = movieAPI.getTopFiveMovies()
        topDao.clear()
        topDao.insertAll(remote.map { it.toTopEntity() })
    }

    suspend fun refreshLatestMovies() {
        val remote = movieAPI.getLatestMovies()
        latestDao.clear()
        latestDao.insertAll(remote.map { it.toLatestEntity() })
    }



}
