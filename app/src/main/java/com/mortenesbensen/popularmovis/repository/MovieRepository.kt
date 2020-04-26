package com.mortenesbensen.popularmovis.repository

import com.mortenesbensen.popularmovis.data.MovieDetails
import com.mortenesbensen.popularmovis.data.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRepository {

    @GET("movie/popular")
    suspend fun getPopularMovies() : PopularMovies

    @GET("movie/{movie_id}")
    suspend fun  getMovieDetails(@Path("movie_id") movieId : Int) : MovieDetails
}