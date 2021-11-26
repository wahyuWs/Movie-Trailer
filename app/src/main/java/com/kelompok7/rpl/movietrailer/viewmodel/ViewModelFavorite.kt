package com.kelompok7.rpl.movietrailer.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.resource.MovieRepository

class ViewModelFavorite(application: Application): ViewModel() {
    private val favorite: MovieRepository = MovieRepository(application)

    fun getDataFavorite(): LiveData<List<Movies>> = favorite.getAllFavorite()

    fun deleteFavorite(data: Movies) {
        favorite.deleteData(data)
    }
}