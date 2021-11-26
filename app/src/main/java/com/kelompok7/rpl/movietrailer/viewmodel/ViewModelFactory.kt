package com.kelompok7.rpl.movietrailer.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDetailFilm::class.java)) {
            return ViewModelDetailFilm(application) as T
        } else if (modelClass.isAssignableFrom(ViewModelFavorite::class.java))
            return ViewModelFavorite(application) as T

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}