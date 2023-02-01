package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URl
import com.wit.voguely.model.Products
import kotlinx.coroutines.tasks.await

class ProductsDataSource {
    suspend fun getProducts(): List<Products> {
        val result = Firebase.database(DATABASE_URl)
                .getReference("products")
                .get()
                .await()

        return result.children.mapNotNull { it.getValue(Products::class.java) }
    }
}