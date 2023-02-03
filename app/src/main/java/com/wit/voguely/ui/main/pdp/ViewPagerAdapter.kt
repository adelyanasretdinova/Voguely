package com.wit.voguely.ui.main.pdp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.ImageForViewpagerBinding

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    var data = listOf<String>()

    inner class ImageViewHolder(val binding: ImageForViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ImageForViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(data[position])
            .into(holder.binding.individualPhoto)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}