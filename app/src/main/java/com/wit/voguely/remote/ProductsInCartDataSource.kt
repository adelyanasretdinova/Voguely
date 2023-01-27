package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.CartResponse
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.tasks.await

class ProductsInCartDataSource {

//    suspend fun getProductsInCart(id:String):List<Products> {
//
//        val result = Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
//            .getReference("carts")
//            .get()
//            .await()
//
//        val productId = result.child(id).getValue(CartResponse::class.java)
//
//        val result2 = Firebase.database("https://voguely-cd971-default-rtdb.europe-west1.firebasedatabase.app")
//            .getReference("products")
////            .orderByChild(productId)
//            .equalTo(productId)
//            .get()
//            .await()
//
//
//        return result2.children.mapNotNull { it.getValue(Products::class.java) }
//    }
}