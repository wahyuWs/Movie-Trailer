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

class HalamanUtamaComedyAdapter: RecyclerView.Adapter<HalamanUtamaComedyAdapter.HalamanUtamaComedyViewHolder>() {
    private val dataMovie = ArrayList<Movies>()

    fun setData(item: ArrayList<Movies>) {
        dataMovie.clear()
        dataMovie.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HalamanUtamaComedyViewHolder {
        val itemCardHalamanUtamaBinding = ItemCardHalamanUtamaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HalamanUtamaComedyViewHolder(itemCardHalamanUtamaBinding)
    }

    override fun onBindViewHolder(holder: HalamanUtamaComedyViewHolder, position: Int) {
        holder.bind(dataMovie[position])
    }

    override fun getItemCount(): Int = 5

    class HalamanUtamaComedyViewHolder(private val binding: ItemCardHalamanUtamaBinding): RecyclerView.ViewHolder(binding.root) {
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