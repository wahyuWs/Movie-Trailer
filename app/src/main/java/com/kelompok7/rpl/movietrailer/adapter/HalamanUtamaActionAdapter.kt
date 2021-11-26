package com.kelompok7.rpl.movietrailer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kelompok7.rpl.movietrailer.databinding.ItemCardHalamanUtamaBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.ui.halamandetail.DetailActivity

class HalamanUtamaActionAdapter: RecyclerView.Adapter<HalamanUtamaActionAdapter.HalamanUtamaActionViewHolder>() {

    private val dataMovie = ArrayList<Movies>()

    fun setData(item: ArrayList<Movies>) {
        dataMovie.clear()
        dataMovie.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HalamanUtamaActionViewHolder {
        val itemCardHalamanUtamaBinding = ItemCardHalamanUtamaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HalamanUtamaActionViewHolder(itemCardHalamanUtamaBinding)
    }

    override fun onBindViewHolder(holder: HalamanUtamaActionViewHolder, position: Int) {
        holder.bind(dataMovie[position])
    }

    override fun getItemCount(): Int = 5

    class HalamanUtamaActionViewHolder(private val binding: ItemCardHalamanUtamaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movies) {
            Glide.with(itemView.context)
                .load(data.movieImage)
                .transform(RoundedCorners(18))
                .into(binding.imagePoster)

            binding.titleMovie.text = data.movieTitle

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}