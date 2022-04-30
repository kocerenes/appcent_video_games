package com.example.appcentvideogames.utils


//api'den gelen cevabın error mu succesmi vb olduğunu handle edebileceğimiz sınıf
sealed class NetworkResult<T>(
    val data : T? = null,
    val message: String? = null,
    val networkError: Boolean = false
 ) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?,networkError: Boolean) : NetworkResult<T>(
        data = null,
        message = message,
        networkError = networkError
    )

}