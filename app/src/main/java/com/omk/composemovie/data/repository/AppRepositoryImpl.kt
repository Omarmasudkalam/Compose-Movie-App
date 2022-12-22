package com.omk.composemovie.data.repository

import android.util.Log
import com.omk.composemovie.data.remote.ApiService
import com.omk.composemovie.data.remote.DataSource
import com.omk.composemovie.data.remote.responses.CastResponse
import com.omk.composemovie.data.remote.responses.MovieDetailsResponse
import com.omk.composemovie.data.remote.responses.MoviesResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

@DelicateCoroutinesApi
class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AppRepository {
    override suspend fun getUpComingMovies(apiKey: String): Flow<DataSource<MoviesResponse>> {
        return flow {
            try {
                val result = apiService.getUpComingMovies(apiKey = apiKey)
                if(result.results.isEmpty()) {
                    emit(DataSource.empty<MoviesResponse>())
                } else {
                    emit(DataSource.success(result))
                }
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

    override suspend fun getPlayingNowMovies(apiKey: String): Flow<DataSource<MoviesResponse>> {
        return flow {
            try {
                val result = apiService.getNowPlayingMovies(apiKey = apiKey)
                if(result.results.isEmpty()) {
                    emit(DataSource.empty<MoviesResponse>())
                } else {
                    emit(DataSource.success(result))
                }
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    Log.d("API ERROR", e.localizedMessage)
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

    override suspend fun getMoviesDetails(apiKey: String, movieId: String): Flow<DataSource<MovieDetailsResponse>> {
        return flow {
            try {
                val result = apiService.getMovieDetails(movie_id = movieId, apiKey = apiKey)
                emit(DataSource.success(result))
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    Log.d("Movies-Details-Error", e.localizedMessage.toString())
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

    override suspend fun getMoviesCast(
        apiKey: String,
        movieId: String
    ): Flow<DataSource<CastResponse>> {
        return flow {
            try {
                val result = apiService.getMovieCast(movie_id = movieId, apiKey = apiKey)
                emit(DataSource.success(result))
            } catch (e: Exception) {
                if(e is IOException) {
                        emit(DataSource.error("No Internet Connection", null))
                } else {
                    Log.d("Movies-Cast-Error", e.localizedMessage.toString())
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

}