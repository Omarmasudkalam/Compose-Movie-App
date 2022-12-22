package com.omk.composemovie.data.remote.responses

data class MovieDetailsResponse(
    var adult: Boolean,
    var backdrop_path: String,
    var genres: List<Genres>,
    var id: String,
    var vote_count: String,
    var original_title: String,
    var overview: String,
    val original_language: String,
    var runtime: String,
    var poster_path: String,
    var release_date: String,
    var vote_average: String
)

data class Genres(
    var id: String,
    var name: String
)
