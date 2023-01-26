package com.wit.voguely.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.OneItemBinding

class RecyclerViewAdapter() :

    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var itemsList = listOf<Products>()
    var onItemClick: ((Products) -> Unit)? = null
    var dropDownClick: ((Products, View) -> Unit)? = null

    inner class ViewHolder(val binding: OneItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.image.setOnClickListener {
                onItemClick?.invoke(itemsList[adapterPosition])
            }
            binding.dropdownMenu.setOnClickListener {
                dropDownClick?.invoke(itemsList[adapterPosition], it)
            }
        }
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
        val eur = itemsList[position].currency
        val amount = itemsList[position].price.toString()
        holder.binding.price.text = eur + amount
        holder.binding.rating.text = itemsList[position].rating.toString()
        holder.binding.review.text = itemsList[position].reviews.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}