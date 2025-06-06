package com.example.movie.api

import android.content.Context
import com.example.movie.data.AppDatabase
import com.example.movie.model.LoginRequest
import com.example.movie.model.Movie
import com.example.movie.model.Profile
import com.example.movie.model.ProfileRequest
import com.example.movie.model.SignUpRequest
import com.example.movie.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.movie.data.MovieDao
import com.example.movie.data.UserProfileDao

object movieAPI {
    private const val BASE_URL = "https://mock.apidog.com/m1/909986-892297-default/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(MovieApiService::class.java)

    suspend fun getTopFiveMovies(): List<Movie> = withContext(Dispatchers.IO) {
        apiService.getTopFiveMovies()
    }

    suspend fun getLatestMovies(): List<Movie> = withContext(Dispatchers.IO) {
        apiService.getLatestMovies()
    }

    suspend fun getDiscoverMovies(category: String, searchQuery: String? = null): List<Movie> = withContext(Dispatchers.IO) {
        try {
            apiService.getDiscoverMovies(category, searchQuery)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getMovieById(id: String): Movie? = withContext(Dispatchers.IO) {
        try {
            apiService.getMovieById(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun login(emailOrUsername: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val response = apiService.login(LoginRequest(emailOrUsername, password))
        response.success
    }

    suspend fun signUp(
        email: String,
        password: String,
        username: String,
        birthDate: String,
        phoneNumber: String
    ): Boolean = withContext(Dispatchers.IO) {
        val response = apiService.signUp(SignUpRequest(email, password, username, birthDate, phoneNumber))
        response.success
    }

    suspend fun getProfile(email: String): Profile? = withContext(Dispatchers.IO) {
        try {
            val profile = apiService.getProfile(ProfileRequest(email))
            profile
        } catch (e: Exception) {
            null
        }
    }

    suspend fun fetchAndCacheTopFiveMovies(context: Context): List<Movie> {
        val remoteMovies = getTopFiveMovies()
        return remoteMovies
    }

}
