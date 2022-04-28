package com.example.appcentvideogames.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

//Bu classtan obje türetmiycez. Diğer sınıflardan bu sınıfı türeticez.
//Her fragment'ın kendine ait bir viewbindingi ve viewModel'ı olacak. Her birinin tipi farklı olduğu için generic tipte oluşturucaz.

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    //viewbindingin bağlama fonksiyonunu çalıştırabilmek için parametre olarak inflater vermemiz gerek
    private val viewBindingInflater: (inflater: LayoutInflater) -> VB
): Fragment() {

    private var _binding : VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel : VM
    protected abstract fun onCreateFinished()
    protected abstract fun initializeListener()
    protected abstract fun observeEvents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = viewBindingInflater.invoke(inflater)
        if(_binding == null){
            throw IllegalArgumentException("binding NULL!")
        }

        return binding.root
    }

    //view creted olduğunda ilgili fonksiyonları çalıştıracağız
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        onCreateFinished()
        initializeListener()
        observeEvents()

        super.onViewCreated(view, savedInstanceState)
    }

    //memory'i etkin kullanmak için (viewbinding)
    override fun onDestroy() {
        _binding= null
        super.onDestroy()
    }

}