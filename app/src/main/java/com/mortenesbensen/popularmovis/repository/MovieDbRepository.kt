package com.mortenesbensen.popularmovis.repository

import com.mortenesbensen.popularmovis.data.MovieDetails
import com.mortenesbensen.popularmovis.data.PopularMovies
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbRepository private constructor() {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val client = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieRepository::class.java)

    suspend fun getPopularMovies(): PopularMovies = client.getPopularMovies()

    suspend fun getMovieDetails(movieId: Int): MovieDetails = client.getMovieDetails(movieId)

    companion object {

        fun create(): MovieDbRepository {
            return MovieDbRepository()
        }

        private const val URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "0d769fc011223444a3a2aea3252aea57"
    }
}