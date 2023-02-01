package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URl
import com.wit.voguely.ui.main.cart.CartResponse
import com.wit.voguely.ui.main.cart.ProductInCart
import kotlinx.coroutines.tasks.await

class ProductsInCartDataSource {
    private val productDataSource = ProductDataSource()
    suspend fun getProductsInCart(): List<ProductInCart> {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()

        val result = Firebase.database(DATABASE_URl)
                .getReference("carts")
                .child(userId)
                .get()
                .await()

        val resultAllCartProducts = result.children.mapNotNull { cartItem ->
            val cartItemWithId = cartItem.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val product = productDataSource.getProducts(cartItemWithId.productId) ?: return@mapNotNull null
            val quantity = cartItemWithId.quantity
            ProductInCart(quantity, product)
        }

        return resultAllCartProducts
    }
}