package com.wit.voguely.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.OneItemBinding
import com.wit.voguely.model.Products
import com.wit.voguely.ui.main.pdp.ProductsDiffCalback


class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var itemsList = listOf<Products>()
    var onItemClick: ((Products) -> Unit) = { }
    var dropDownClick: ((Products, View) -> Unit) = { _, _ -> }


    inner class ViewHolder(val binding: OneItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.image.setOnClickListener {
                onItemClick(itemsList[adapterPosition])
            }
            binding.dropdownMenu.setOnClickListener {
                dropDownClick(itemsList[adapterPosition], it)
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

    fun updateItems(products: List<Products>) {
        val diffCallback = ProductsDiffCalback(this.itemsList, products)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.itemsList = products
        diffResult.dispatchUpdatesTo(this)
    }
}