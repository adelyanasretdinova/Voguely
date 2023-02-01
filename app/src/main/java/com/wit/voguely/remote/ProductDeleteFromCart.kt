package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URl
import com.wit.voguely.ui.main.cart.Cart
import com.wit.voguely.ui.main.cart.CartResponse
import kotlinx.coroutines.tasks.await

class ProductDeleteFromCart {

    suspend fun deleteProduct(id: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val productInCart =
            Firebase.database(DATABASE_URl)
                .getReference("carts")
                .child(userId)

        val carts = productInCart.get().await().children.mapNotNull {
            val product = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, product)
        }

        val cartResponse = carts.firstOrNull { it.cartResponse.productId == id }
        if (cartResponse != null) {
            if (cartResponse.cartResponse.quantity == 1) {
                productInCart.child(cartResponse.key)
                    .removeValue()
                    .await()
            } else {
                productInCart.child(cartResponse.key)
                    .setValue(CartResponse(id, cartResponse.cartResponse.quantity - 1))
                    .await()
            }
        }
    }

    suspend fun deleteAll() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val productInCart =
            Firebase.database(DATABASE_URl)
                .getReference("carts")
                .child(userId)

        productInCart.setValue(null).await()

    }
}