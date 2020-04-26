package com.mortenesbensen.popularmovis.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mortenesbensen.popularmovis.R
import com.mortenesbensen.popularmovis.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMovieDetailBinding>(
                this,
                R.layout.activity_movie_detail
            )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }

    override fun onStart() {
        super.onStart()
        intent.getIntExtraOrNull(MOVIE_ID_EXTRA)?.let {
            viewModel.getMovie(it)
        }
    }

    private fun Intent.getIntExtraOrNull(name: String): Int? {
        return if (hasExtra(name)) {
            getIntExtra(name, -1)
        } else {
            null
        }
    }

    companion object {

        const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"

        fun getIntent(context: Context): Intent {
            return Intent(context, MovieDetailActivity::class.java)
        }
    }
}