package com.wit.voguely.ui.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.OneItemInCartBinding
import com.wit.voguely.ui.main.Products
import com.wit.voguely.ui.main.ProductsInCart

class CartRecyclerViewAdapter() :
    RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    var productsInCart = listOf<ProductsInCart>()
    var onItemClick: ((ProductsInCart) -> Unit)? = null


    inner class ViewHolder(val binding: OneItemInCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            OneItemInCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(productsInCart[position].image)
            .into(holder.binding.image)
        holder.binding.name.text = productsInCart[position].name
        holder.binding.price.text = productsInCart[position].price.toString()

    }

    override fun getItemCount(): Int {
        return productsInCart.size
    }

}