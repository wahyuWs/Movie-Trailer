package com.kelompok7.rpl.movietrailer.resource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kelompok7.rpl.movietrailer.model.Movies

@Database(entities = [Movies::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movie_database")
                        .build()
                }
            }
            return INSTANCE as MovieDatabase
        }
    }
}