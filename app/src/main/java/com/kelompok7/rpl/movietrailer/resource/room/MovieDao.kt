package com.kelompok7.rpl.movietrailer.resource.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kelompok7.rpl.movietrailer.model.Movies

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getFavorite(): LiveData<List<Movies>>

    @Query("SELECT * FROM movie WHERE movie_title = :title")
    fun cekFavorite(title: String): LiveData<Movies>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(data: Movies)

    @Delete
    fun deleteFavorite(data: Movies)
}