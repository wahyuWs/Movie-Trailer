package com.kelompok7.rpl.movietrailer.resource

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.resource.room.MovieDao
import com.kelompok7.rpl.movietrailer.resource.room.MovieDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieRepository(application: Application) {
    private val dao: MovieDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieDatabase.getDatabase(application)
        dao = db.movieDao()
    }

    fun getAllFavorite(): LiveData<List<Movies>> = dao.getFavorite()

    fun cekFavorite(title: String): LiveData<Movies> = dao.cekFavorite(title)

    fun insertData(data: Movies) {
        executorService.execute { dao.insertFavorite(data) }
    }

    fun deleteData(data: Movies) {
        executorService.execute { dao.deleteFavorite(data) }
    }
}