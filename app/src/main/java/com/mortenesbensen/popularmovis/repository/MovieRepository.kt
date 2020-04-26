package com.mortenesbensen.popularmovis.repository

import com.mortenesbensen.popularmovis.data.PopularMovies
import retrofit2.http.GET

interface MovieRepository {

    @GET("movie/popular")
    suspend fun getPopularMovies() : PopularMovies
}