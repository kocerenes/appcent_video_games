package com.example.appcentvideogames.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcentvideogames.model.GameDetail
import com.example.appcentvideogames.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository): ViewModel(){

    val detailResponse: MutableLiveData<GameDetail?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getDetail(
        id: Int,
        apiKey: String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getDetail(id,apiKey)
        when(request){
            is NetworkResult.Success -> {
                isLoading.value = false
                detailResponse.value = request.data
            }
            is NetworkResult.Error -> {
                isLoading.value = false
                onError.value = request.message
            }
        }
    }

}