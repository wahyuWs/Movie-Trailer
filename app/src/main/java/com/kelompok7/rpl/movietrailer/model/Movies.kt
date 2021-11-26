package com.kelompok7.rpl.movietrailer.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "movie")
data class Movies(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "image")
    var movieImage: Int,

    @ColumnInfo(name = "movie_title")
    var movieTitle: String,

    @ColumnInfo(name = "movie_description")
    var movieDescription: String,

    @ColumnInfo(name = "genre")
    var movieGenre: String,

    @ColumnInfo(name = "rate")
    var movieRate: String,

    @ColumnInfo(name = "duration")
    var movieDuration: String,

    @ColumnInfo(name = "trailer_link")
    var movieTrailer: String
): Parcelable
