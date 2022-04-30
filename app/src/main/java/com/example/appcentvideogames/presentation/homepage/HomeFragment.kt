package com.example.appcentvideogames.presentation.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.appcentvideogames.R
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // hilt için
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    //view model'ı tanımla
    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreateFinished() {
        TODO("Not yet implemented")
    }

    //tıklama listenerlarını yazacağımız fonksiyon
    override fun initializeListener() {
        TODO("Not yet implemented")
    }

    //livedataları gözlemleyeceğimiz fonksiyon
    override fun observeEvents() {
        TODO("Not yet implemented")
    }

}