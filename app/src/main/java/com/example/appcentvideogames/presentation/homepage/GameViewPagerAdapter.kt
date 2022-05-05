package com.example.appcentvideogames.presentation.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcentvideogames.R
import com.example.appcentvideogames.model.Game
import com.example.appcentvideogames.presentation.homepage.listener.ViewPagerListener

class GameViewPagerAdapter(private val listener: ViewPagerListener) : RecyclerView.Adapter<GameViewPagerAdapter.ViewPagerViewHolder>() {

    private var games = ArrayList<Game>()

    class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val gameImage: ImageView = itemView.findViewById(R.id.viewPagerImageView)

        fun bind(listener: ViewPagerListener, game: Game) {
            Glide.with(itemView.context)
                .load(game.imageUrl)
                .into(gameImage)

            itemView.setOnClickListener {
                listener.onItemClickedViewPager(game)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewPagerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = layoutInflater.inflate(R.layout.vp_imageview, parent, false)
                return ViewPagerViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPagerViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) = holder.bind(listener,games[position])

    override fun getItemCount() = games.size

    fun setList(gamesListForViewPager: ArrayList<Game>) {
        games.clear()
        games.addAll(gamesListForViewPager)
        notifyDataSetChanged()
    }

}