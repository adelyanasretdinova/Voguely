package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.tasks.await

class ProductDataSource {
    suspend fun getProducts(id: String): Products? {

        val result =
            Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("products")
                .get()
                .await()

        return result.child(id).getValue(Products::class.java)
    }
}