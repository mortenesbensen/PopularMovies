package com.mortenesbensen.popularmovis.view

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mortenesbensen.popularmovis.repository.MovieDbRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    val movieTitle = ObservableField<String>()
    val movieRating = ObservableField<String>()
    val movieOverview = ObservableField<String>()
    val movieGenres = ObservableField<String>()

    private val repository = MovieDbRepository.create()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            with(repository.getMovieDetails(movieId)) {
                movieTitle.set(title)
                movieRating.set(vote_average.toString())
                movieOverview.set(overview)
                movieGenres.set(genres.joinToString { it.name })
            }
        }
    }
}