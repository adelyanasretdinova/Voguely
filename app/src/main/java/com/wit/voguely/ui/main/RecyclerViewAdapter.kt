package com.wit.voguely.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.OneItemBinding

class RecyclerViewAdapter() :

    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var itemsList = listOf<Products>()

    inner class ViewHolder(val binding: OneItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(itemsList[position].image)
            .into(holder.binding.image)
        holder.binding.name.text = itemsList[position].name
        holder.binding.price.text = itemsList[position].price
        holder.binding.rating.text = itemsList[position].rating
        holder.binding.review.text = itemsList[position].review
    }

    override fun getItemCount(): Int {
       return itemsList.size
    }
}