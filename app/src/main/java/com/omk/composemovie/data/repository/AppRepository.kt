package com.omk.composemovie.data.repository

import com.omk.composemovie.data.remote.DataSource
import com.omk.composemovie.data.remote.responses.CastResponse
import com.omk.composemovie.data.remote.responses.MovieDetailsResponse
import com.omk.composemovie.data.remote.responses.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getUpComingMovies(apiKey: String): Flow<DataSource<MoviesResponse>>
    suspend fun getPlayingNowMovies(apiKey: String): Flow<DataSource<MoviesResponse>>
    suspend fun getMoviesDetails(apiKey: String, movieId: String): Flow<DataSource<MovieDetailsResponse>>
    suspend fun getMoviesCast(apiKey: String, movieId: String): Flow<DataSource<CastResponse>>
}