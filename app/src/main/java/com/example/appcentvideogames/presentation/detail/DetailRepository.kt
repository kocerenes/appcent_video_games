package com.example.appcentvideogames.presentation.detail

import com.example.appcentvideogames.base.BaseRepository
import com.example.appcentvideogames.network.ApiFactory
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiFactory: ApiFactory): BaseRepository() {

    suspend fun getDetail(
        id: Int,
        apiKey: String
    ) = safeApiRequest{
        apiFactory.getGameDetail(id,apiKey)
    }

}