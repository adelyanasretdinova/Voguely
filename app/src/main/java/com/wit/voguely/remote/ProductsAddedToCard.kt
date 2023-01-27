package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.Cart
import com.wit.voguely.ui.main.CartResponse
import kotlinx.coroutines.tasks.await

class AddToCartDataSource {

    suspend fun addProduct(id: String) {
        val productInCart =
            Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("carts")
                .child(FirebaseAuth.getInstance().currentUser!!.getUid())

        val carts = productInCart.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val cartResponse = carts.firstOrNull {
            it.cartResponse.productId == id
        }

        if (cartResponse == null) {
            productInCart.push().setValue(CartResponse(id, 1)).await()
        } else {
            productInCart.child(cartResponse.key)
                .setValue(CartResponse(id, cartResponse.cartResponse.quantity + 1)).await()
        }


    }
}