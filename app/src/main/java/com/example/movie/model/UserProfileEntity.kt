package com.example.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val email: String,
    val username: String,
    val birthDate: String,
    val phoneNumber: String,
    val averageRating: Float,
    val downloadedMovies: Int
)

fun UserProfileEntity.toModel(): Profile = Profile(
    email = email,
    username = username,
    birthDate = birthDate,
    phoneNumber = phoneNumber,
    averageRating = averageRating,
    downloadedMovies = downloadedMovies
)

fun Profile.toEntity(): UserProfileEntity = UserProfileEntity(
    email = email,
    username = username,
    birthDate = birthDate,
    phoneNumber = phoneNumber,
    averageRating = averageRating,
    downloadedMovies = downloadedMovies
)
