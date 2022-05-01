package com.example.appcentvideogames.network

import com.example.appcentvideogames.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {

    //https://api.rawg.io/api/  -> BASE_URL
    // games?key=cb3f44f97104493aadd2deaee997b33a

    @GET("games")
    suspend fun getData(
        @Query("key") apiKey: String
    ): GameResponse

    /*@GET("v1/cryptocurrency/listings/latest")
    suspend fun getData(
        @Header("X-CMC_PRO_API_KEY") apiKey:String,
        @Query("limit") limit : String
    ): CryptoResponse*/

}