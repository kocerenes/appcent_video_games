package com.example.appcentvideogames.presentation.homepage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcentvideogames.model.GameResponse
import com.example.appcentvideogames.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
                println(request.message.toString())
                onError.value = request.message
            }
        }
    }

}