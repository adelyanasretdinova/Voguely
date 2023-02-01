package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URl
import com.wit.voguely.ui.main.cart.Cart
import com.wit.voguely.ui.main.cart.CartResponse
import kotlinx.coroutines.tasks.await

class AddToCartDataSource {

    suspend fun addProduct(id: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val productInCart = Firebase.database(DATABASE_URl)
                .getReference("carts")
                .child(userId)

        val carts = productInCart.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val cartResponse = carts.firstOrNull { it.cartResponse.productId == id }

        if (cartResponse == null) {
            productInCart.push().setValue(CartResponse(id, 1)).await()
        } else {
            productInCart
                .child(cartResponse.key)
                .setValue(CartResponse(id, cartResponse.cartResponse.quantity + 1))
                .await()
        }


    }
}