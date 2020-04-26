package com.mortenesbensen.popularmovis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mortenesbensen.popularmovis.R
import com.mortenesbensen.popularmovis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.events.observe(this, Observer { event ->
            when (event) {
                is MovieListViewModel.Event.OpenDetailsScreen -> openDetailsActivity(event.movieId)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMovies()
    }

    private fun openDetailsActivity(movieId: Int) {
        val intent = MovieDetailActivity.getIntent(this).apply {
            putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movieId)
        }
        startActivity(intent)
    }
}
