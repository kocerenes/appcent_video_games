package com.example.appcentvideogames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appcentvideogames.model.GameDetail

@Database(entities = [GameDetail::class], version = 1)
abstract class FavoriteGameDatabase: RoomDatabase() {

    abstract fun favoriteGameDAO() : FavoriteGameDAO

    //Singleton
    companion object{

        @Volatile private var instance : FavoriteGameDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoriteGameDatabase::class.java,
            "favoritegamedatabase"
        ).build()
    }

}