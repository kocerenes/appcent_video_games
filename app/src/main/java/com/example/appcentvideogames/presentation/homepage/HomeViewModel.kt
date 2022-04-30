package com.example.appcentvideogames.presentation.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcentvideogames.model.GameResponse
import com.example.appcentvideogames.utils.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel(){

    val gameResponse: MutableLiveData<GameResponse?> = MutableLiveData()
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getData(
        apiKey: String
    ) = viewModelScope.launch {
        val request = repository.getData(apiKey)
        when(request){
            is NetworkResult.Success ->{
                gameResponse.value = request.data
            }
            is NetworkResult.Error ->{
                onError.value = request.message
            }
        }
    }

}