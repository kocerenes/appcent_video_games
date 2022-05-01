package com.example.appcentvideogames.presentation.homepage

import com.example.appcentvideogames.base.BaseRepository
import com.example.appcentvideogames.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {

    suspend fun getData(
        apiKey: String
    ) = safeApiRequest {
        println("repository getdata")
        apiFactory.getData(apiKey)
    }

}