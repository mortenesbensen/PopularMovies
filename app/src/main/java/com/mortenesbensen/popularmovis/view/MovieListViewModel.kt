package com.mortenesbensen.popularmovis.view

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mortenesbensen.popularmovis.BR
import com.mortenesbensen.popularmovis.R
import com.mortenesbensen.popularmovis.data.Movie
import com.mortenesbensen.popularmovis.repository.MovieDbRepository
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding

class MovieListViewModel : ViewModel() {

    val items = ObservableArrayList<Movie>()
    val itemBinding = ItemBinding.of<Movie>(BR.movie, R.layout.view_movie_list_item)

    private val repository = MovieDbRepository.create()

    fun getMovies() {
        viewModelScope.launch {
            items.clear()
            items.addAll(repository.getPopularMovies().results)
        }
    }
}