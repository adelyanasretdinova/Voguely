package com.wit.voguely.ui.main.pdp

import androidx.recyclerview.widget.DiffUtil
import com.wit.voguely.model.Products


class ProductsDiffCalback(
    private val oldList: List<Products>,
    private val newList: List<Products>
) :
    DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
 return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name === newList[newItemPosition].name
    }
}