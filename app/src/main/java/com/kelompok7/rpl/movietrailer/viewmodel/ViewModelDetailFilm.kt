package com.kelompok7.rpl.movietrailer.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.resource.MovieRepository

class ViewModelDetailFilm(application: Application): ViewModel() {
    private val movieRepository: MovieRepository = MovieRepository(application)

    fun cekMovieFavorite(title: String): LiveData<Movies> = movieRepository.cekFavorite(title)

    fun insert(data: Movies) {
        movieRepository.insertData(data)
    }

    fun delete(data: Movies) {
        movieRepository.deleteData(data)
    }
}