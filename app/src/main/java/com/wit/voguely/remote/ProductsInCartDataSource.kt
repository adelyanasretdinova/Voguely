package com.wit.voguely.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.cart.CartResponse
import com.wit.voguely.ui.main.cart.ProductInCart
import kotlinx.coroutines.tasks.await

class ProductsInCartDataSource {
    private val productDataSource = ProductDataSource()
    suspend fun getProductsInCart(): List<ProductInCart> {

        val result =
            Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("carts")
                .child(FirebaseAuth.getInstance().currentUser!!.getUid())
                .get()
                .await()

        val resultAllCartProducts = result.children.mapNotNull { cartItem ->
            val cartItemWithId = cartItem.getValue(CartResponse::class.java)
            val product = cartItemWithId?.let { productDataSource.getProducts(it.productId) }
            val quantity = cartItemWithId?.quantity
            if (quantity != null && product != null) {
                ProductInCart(quantity, product)
            } else null
        }

        return resultAllCartProducts
    }
}