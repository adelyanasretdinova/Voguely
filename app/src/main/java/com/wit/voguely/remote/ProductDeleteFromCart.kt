package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.ProductInCart
import com.wit.voguely.ui.main.cart.Cart
import com.wit.voguely.ui.main.cart.CartResponse
import kotlinx.coroutines.tasks.await

class ProductDeleteFromCart {

    suspend fun deleteProduct(id: String) {
        val productInCart =
            Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("carts")
                .child(FirebaseAuth.getInstance().currentUser!!.getUid())

        val carts = productInCart.get().await().children.mapNotNull {
            val product = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart (key, product)
        }

        val cartResponse = carts.firstOrNull {
            it.cartResponse.productId == id
        }
        if (cartResponse != null) {
            if(cartResponse.cartResponse.quantity == 1 ) {
                productInCart.child(cartResponse.key).removeValue().await()
            } else {
                productInCart.child(cartResponse.key).setValue(CartResponse(id,cartResponse.cartResponse.quantity-1)).await()
            }
        }
    }
}