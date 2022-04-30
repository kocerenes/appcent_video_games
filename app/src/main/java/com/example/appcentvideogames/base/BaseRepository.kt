package com.example.appcentvideogames.base

import com.example.appcentvideogames.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    // her http isteği attığımızda bu fonksiyonu kullanıcaz.
    // isteklerin başarılı mı basarısız mı olduğunu vb ayıklamalar yapmak için kullanıcaz
    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                NetworkResult.Error(throwable.localizedMessage, true)
            }
        }
    }
}