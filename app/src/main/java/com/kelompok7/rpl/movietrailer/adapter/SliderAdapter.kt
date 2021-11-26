package com.kelompok7.rpl.movietrailer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kelompok7.rpl.movietrailer.databinding.ItemSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter: SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    private val image = ArrayList<Int>()

    fun setData(item: ArrayList<Int>) {
        image.addAll(item)
    }
    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val itemslider = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(itemslider)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.bind(image[position])
    }

    override fun getCount(): Int = image.size

    class SliderViewHolder(private val binding: ItemSliderBinding): SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(data: Int) {
            Glide.with(itemView.context)
                .load(data)
                .transform(RoundedCorners(30))
                .into(binding.banners)
        }
    }
}