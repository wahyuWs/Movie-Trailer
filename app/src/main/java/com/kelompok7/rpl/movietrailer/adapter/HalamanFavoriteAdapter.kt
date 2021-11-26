package com.kelompok7.rpl.movietrailer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kelompok7.rpl.movietrailer.databinding.ItemCardHalamanFavoriteBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.ui.halamandetail.DetailActivity
import com.kelompok7.rpl.movietrailer.ui.halamanfavorite.ItemCallback

class HalamanFavoriteAdapter(private val callback: ItemCallback): RecyclerView.Adapter<HalamanFavoriteAdapter.HalamanFavoriteViewHolder>() {

    private val dataMovie = ArrayList<Movies>()

    fun setData(item: ArrayList<Movies>) {
        dataMovie.clear()
        dataMovie.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HalamanFavoriteViewHolder {
        val itemCardHalamanFavoriteBinding = ItemCardHalamanFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HalamanFavoriteViewHolder(itemCardHalamanFavoriteBinding)
    }

    override fun onBindViewHolder(holder: HalamanFavoriteViewHolder, position: Int) {
        holder.bind(dataMovie[position])
    }

    override fun getItemCount(): Int = dataMovie.size

    inner class HalamanFavoriteViewHolder(private val binding: ItemCardHalamanFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movies) {
            Glide.with(itemView.context)
                .load(data.movieImage)
                .transform(RoundedCorners(18))
                .into(binding.imagePoster)

            binding.titleMovie.text = data.movieTitle
            binding.kategoriFilm.text = data.movieGenre
            binding.duration.text = data.movieDuration
            binding.btnDeleteFavorite.setOnClickListener { callback.onStatusFavorite(data) }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}