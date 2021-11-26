package com.kelompok7.rpl.movietrailer.ui.halamanfavorite

import com.kelompok7.rpl.movietrailer.model.Movies

interface ItemCallback {
    fun onStatusFavorite(movie: Movies)
}