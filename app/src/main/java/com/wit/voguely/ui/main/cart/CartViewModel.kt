package com.wit.voguely.ui.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductsInCartDataSource
import com.wit.voguely.ui.main.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {

    private val _productinCart = MutableStateFlow<List<CartResponse>>(listOf())
    val productinCart = _productinCart.asStateFlow()
    private val _emptyCart = MutableStateFlow<Boolean>(false)
    val emptyCart = _emptyCart.asStateFlow()
    private val _totalAmount = MutableStateFlow<Int>(0)
    val totalAmount = _totalAmount.asStateFlow()
    val productsInCartDataSource = ProductsInCartDataSource()

    init {
        loadProductsInCart()
    }

    private fun loadProductsInCart() {
//        val products = listOf(
//            productCartOne,
//            productCartTwo,
//            productCartThree,
//            productCartFour
//        )

//        viewModelScope.launch {
//            _productinCart.update {productsInCartDataSource.getProductsInCart()}
//            _emptyCart.update { _productinCart.value.isEmpty() }
//            _totalAmount.update { products.sumOf { it.price.split(" ")[0].toInt() }}
//        }

    }
}