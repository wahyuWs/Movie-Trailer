package com.kelompok7.rpl.movietrailer.ui.halamanfavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelompok7.rpl.movietrailer.adapter.HalamanFavoriteAdapter
import com.kelompok7.rpl.movietrailer.databinding.ActivityFavoriteBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelFactory
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelFavorite

class FavoriteActivity : AppCompatActivity(), ItemCallback {
    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding
    private lateinit var adapterFavorite: HalamanFavoriteAdapter
    private lateinit var viewmodelFavorite: ViewModelFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        supportActionBar?.title = "Favorite"

        adapterFavorite = HalamanFavoriteAdapter(this)
        val factory = ViewModelFactory.getInstance(application)
        viewmodelFavorite = ViewModelProvider(this, factory)[ViewModelFavorite::class.java]

        viewmodelFavorite.getDataFavorite().observe(this, { data ->
            if (data != null) {
                adapterFavorite.setData(data as ArrayList<Movies>)

                with(activityFavoriteBinding.rvFavorite) {
                    layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    adapter = adapterFavorite
                }
            } else {
                Toast.makeText(this, "Belum ada daftar fovorite", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStatusFavorite(movie: Movies) {
        viewmodelFavorite.deleteFavorite(movie)
    }
}