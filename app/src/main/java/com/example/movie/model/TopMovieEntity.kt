package com.example.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_movies")
data class TopMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val rating: Float,
    val genres: String,
    val description: String,
    val imageUrl: String
)

fun TopMovieEntity.toModel(): Movie = Movie(
    id = id.toString(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl = imageUrl
)

fun Movie.toTopEntity(): TopMovieEntity = TopMovieEntity(
    id = id.toInt(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl = imageUrl
)
