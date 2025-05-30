package com.example.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest_movies")
data class LatestMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val rating: Float,
    val genres: String,
    val description: String,
    val imageUrl : String
)

fun LatestMovieEntity.toModel(): Movie = Movie(
    id = id.toString(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl  = imageUrl
)

fun Movie.toLatestEntity(): LatestMovieEntity = LatestMovieEntity(
    id = id.toInt(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl  = imageUrl
)
