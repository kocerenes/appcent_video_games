package com.example.appcentvideogames.database

import androidx.room.*
import com.example.appcentvideogames.model.GameDetail

@Dao
interface FavoriteGameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //çakışma olsa bile ekle
    fun insert(gameDetail: GameDetail)

    @Delete
    fun delete(gameDetail: GameDetail)

    @Query("SELECT * FROM GameDetail")
    fun getAllGamesDetail(): List<GameDetail>

}