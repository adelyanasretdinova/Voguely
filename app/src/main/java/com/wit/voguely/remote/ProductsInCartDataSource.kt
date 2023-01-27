package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.CartResponse
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.tasks.await

class ProductsInCartDataSource {

    suspend fun getProductsInCart():List<CartResponse> {

        val result = Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("carts")
            .get()
            .await()

        return result.children.mapNotNull { it.getValue(CartResponse::class.java) }
    }
}