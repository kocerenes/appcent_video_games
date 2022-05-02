package com.example.appcentvideogames.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.appcentvideogames.R
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.databinding.FragmentDetailBinding
import com.example.appcentvideogames.model.GameDetail
import com.example.appcentvideogames.utils.Constants.API_KEY
import com.example.appcentvideogames.utils.loadImage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding:: inflate
) {
    private val args by navArgs<DetailFragmentArgs>()
    override val viewModel by viewModels<DetailViewModel>()

    override fun onCreateFinished() {
        viewModel.getDetail(args.id,API_KEY)
    }

    override fun initializeListener() {

    }

    override fun observeEvents() {

        with(viewModel){
            detailResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    handleView(false)
                    binding.ivGameImage.loadImage(it.imageUrl.toString())
                    binding.tvGameName.text = it.name
                    binding.tvGameReleased.text = it.released
                    binding.tvGameMetacritic.text = it.metacritic
                    binding.tvGameDescription.text = it.description
                }
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                handleView(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }
    }

    /*private fun parseData(it: GameDetail?){
        val gson = Gson()
        val json = gson.toJson(it?.)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject[args.id.toString()] as JSONArray
        val game = gson.fromJson(jsonArray.getJSONObject(0).toString(),GameDetail::class.java)

        game?.let {
            with(binding){
                ivGameImage.loadImage(it.imageUrl.toString())
                tvGameName.text = it.name
                tvGameReleased.text = it.released
                tvGameMetacritic.text = it.metacritic
                tvGameDescription.text = it.description

            }
        }
    }*/

    private fun handleView(isLoading: Boolean = false){
        binding.detailGroup.isVisible = !isLoading
    }

}