package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.CartResponse
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.tasks.await

class AddToCartDataSource {

    suspend fun addProduct(id: String) {
        val productInCart = Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("carts")
            .child(FirebaseAuth.getInstance().currentUser!!.getUid())

        productInCart.push().setValue(CartResponse(id, 1)).await()
    }
}