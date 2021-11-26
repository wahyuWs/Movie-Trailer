package com.kelompok7.rpl.movietrailer.ui.halamandetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kelompok7.rpl.movietrailer.R
import com.kelompok7.rpl.movietrailer.databinding.ActivityDetailBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelDetailFilm
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var viewmodel: ViewModelDetailFilm

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val data = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        val factory = ViewModelFactory.getInstance(application)
        viewmodel = ViewModelProvider(this, factory)[ViewModelDetailFilm::class.java]

        if (data != null) {

            with(activityDetailBinding) {
                val youtubePlayerView: YouTubePlayerView = youtubeVideo
                lifecycle.addObserver(youtubePlayerView)

                youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId = data.movieTrailer
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                })

                movieTitle.text = data.movieTitle
                movieDescription.text = data.movieDescription
                movieRate.text = data.movieRate
                movieDuration.text = data.movieDuration
                movieGenre.text = data.movieGenre

                viewmodel.cekMovieFavorite(data.movieTitle).observe(this@DetailActivity, { statusFavorite ->
                    if (statusFavorite != null) {
                        btnAddFavorite.setImageResource(R.drawable.ic_favorite)
                        btnAddFavorite.setOnClickListener {
                            viewmodel.delete(statusFavorite)
                            Toast.makeText(this@DetailActivity, "Dihapus Dari Daftar Favorite", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        btnAddFavorite.setImageResource(R.drawable.favorite_border)
                        btnAddFavorite.setOnClickListener {
                            viewmodel.insert(data)
                            Toast.makeText(this@DetailActivity, "DiTambah Ke Daftar Favorite", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}