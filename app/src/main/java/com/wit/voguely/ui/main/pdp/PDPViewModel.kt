package com.wit.voguely.ui.main.pdp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PDPViewModel: ViewModel() {
    private val productDataSource = ProductDataSource()
    private val _product = MutableStateFlow<Products?>(null)
    val product = _product.asStateFlow()


    fun oneProductLoad(id:String) {

        viewModelScope.launch {
            _product.update { productDataSource.getProducts(id) }
        }
}
}