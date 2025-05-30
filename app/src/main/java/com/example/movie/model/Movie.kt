package com.example.movie.model

data class Movie(
    val id: String,
    val title: String,
    val rating: Float,
    val genres: String,
    val description: String,
    val imageUrl: String
)

fun Movie.toEntity(): MovieEntity = MovieEntity(
    id = id.toInt(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl = imageUrl
)

fun MovieEntity.toModel(): Movie = Movie(
    id = id.toString(),
    title = title,
    rating = rating,
    genres = genres,
    description = description,
    imageUrl = imageUrl
)
