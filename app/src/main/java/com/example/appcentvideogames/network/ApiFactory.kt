package com.example.appcentvideogames.network

import androidx.databinding.Observable
import com.example.appcentvideogames.model.GameDetail
import com.example.appcentvideogames.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFactory {

    //https://api.rawg.io/api/  -> BASE_URL
    // games?key=cb3f44f97104493aadd2deaee997b33a

    @GET("games")
    suspend fun getData(
        @Query("key") apiKey: String
    ): GameResponse


    //https://api.rawg.io/api/
    // games/3498
    // ?key=cb3f44f97104493aadd2deaee997b33a
    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): GameDetail

}