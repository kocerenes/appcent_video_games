package com.example.appcentvideogames.presentation.homepage

import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.appcentvideogames.base.BaseFragment
import com.example.appcentvideogames.databinding.FragmentHomeBinding
import com.example.appcentvideogames.model.Game
import com.example.appcentvideogames.presentation.homepage.listener.ItemClickListener
import com.example.appcentvideogames.presentation.homepage.listener.ViewPagerListener
import com.example.appcentvideogames.utils.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // hilt için
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    //view model'ı tanımla
    override val viewModel by viewModels<HomeViewModel>()

    private lateinit var recyclerAdapter: HomeRecyclerAdapter

    //viewpager
    private lateinit var viewPagerAdapter: GameViewPagerAdapter
    private lateinit var viewPager2: ViewPager2


    override fun onCreateFinished() {
        Log.e("dene","oncreat")
        viewModel.getData(API_KEY)
    }

    //tıklama listenerlarını yazacağım fonksiyon
    override fun initializeListener() {

    }

    //livedataları gözlemleyeceğimiz fonksiyon
    override fun observeEvents() {

        with(viewModel){

            gameResponse.observe(viewLifecycleOwner, Observer {

                it?.let {
                    it.results?.let { it1 -> setRecycler(it1) }
                    it.results?.let { it2 -> setSearchView(it2) }
                    it.results?.let { it3 -> setViewPager(it3) }
                }
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setRecycler(data:List<Game>){
        recyclerAdapter = HomeRecyclerAdapter(object : ItemClickListener {
            override fun onItemClick(game: Game) {
                if (game.id != null){
                    val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game.id)
                    Navigation.findNavController(requireView()).navigate(navigation)
                }
            }

            override fun onFilteredNameOfGame(nameLength: Int) {
                if (nameLength == 0 && nameLength == null) {
                    binding.constraintLayout.visibility = View.VISIBLE
                }
                else {
                    binding.constraintLayout.visibility = View.GONE
                }
            }
        })

        viewPagerAdapter = GameViewPagerAdapter(object : ViewPagerListener {
            override fun onItemClickedViewPager(game: Game) {
                if (game.id != null){
                    val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game.id)
                    Navigation.findNavController(requireView()).navigate(navigation)
                }
            }

        })
        binding.rvHome.adapter = recyclerAdapter
        recyclerAdapter.setList(data)
    }

    private fun setSearchView(games: List<Game>) {
        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText?.length!! >= 3) {
                    binding.vp2Home.visibility = View.GONE
                    recyclerAdapter.setList(games)
                    recyclerAdapter.filter.filter(newText)
                }
                else if (newText.isEmpty()) {
                    binding.vp2Home.visibility = View.VISIBLE
                    recyclerAdapter.setList(games)
                }
                return true
            }
        })
    }

    private fun setViewPager(games: List<Game>){
        var topThreeGame = ArrayList<Game>()
        var newThreeGame = addTopThreeGame(games,topThreeGame)
        initViewPager(newThreeGame)
    }

    //ilk 3 oyunu al
    private fun addTopThreeGame(data:List<Game>,topThreeGame: ArrayList<Game>): ArrayList<Game>{
        topThreeGame.add(data[0])
        topThreeGame.add(data[1])
        topThreeGame.add(data[2])
        return topThreeGame
    }

    //viewpager'ı kur
    private fun initViewPager(newThreeGame: ArrayList<Game>){
        viewPager2 = binding.vp2Home
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPagerAdapter.setList(newThreeGame)
        viewPager2.adapter = viewPagerAdapter
    }


    private fun handleViews(isLoading : Boolean = false){
        binding.rvHome.isVisible = !isLoading
    }

}