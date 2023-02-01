package com.wit.voguely.ui.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.OneItemInCartBinding


class CartRecyclerViewAdapter() :
    RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    var productsInCart = listOf<ProductInCart>()
    var onItemClick: ((ProductInCart) -> Unit)? = null


    inner class ViewHolder(val binding: OneItemInCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.delete.setOnClickListener {
                onItemClick?.invoke(productsInCart[adapterPosition])
            }
        }
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
            .load(productsInCart[position].product.image)
            .into(holder.binding.image)
        holder.binding.name.text = productsInCart[position].product.name
        val eur = productsInCart[position].product.currency
        val amount = productsInCart[position].product.price.toString()
        holder.binding.price.text = eur + amount
        holder.binding.itemsOrdered.text = "x" + productsInCart[position].quantity.toString()

    }

    override fun getItemCount(): Int {
        return productsInCart.size
    }

}