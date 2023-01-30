package com.wit.voguely.ui.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductDeleteFromCart
import com.wit.voguely.remote.ProductsInCartDataSource
import com.wit.voguely.ui.main.ProductInCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {
    private val productsInCartDataSource = ProductsInCartDataSource()
    private val productDeleteFromCart = ProductDeleteFromCart()
    private val _productinCart = MutableStateFlow<List<ProductInCart>>(listOf())
    val productinCart = _productinCart.asStateFlow()
    private val _emptyCart = MutableStateFlow<Boolean>(false)
    val emptyCart = _emptyCart.asStateFlow()
    private val _totalAmount = MutableStateFlow<Int>(0)
    val totalAmount = _totalAmount.asStateFlow()


    fun loadProductsInCart() {

        viewModelScope.launch {
            _productinCart.update { productsInCartDataSource.getProductsInCart() }
            _emptyCart.update { _productinCart.value.isEmpty() }
            _totalAmount.update {

                _productinCart.value.sumOf { it.product.price * it.quantity }

            }
        }

    }


    fun deleteItemFromCart(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productDeleteFromCart.deleteProduct(id)
            loadProductsInCart()
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            productDeleteFromCart.deleteAll()
            loadProductsInCart()
        }
    }
}

