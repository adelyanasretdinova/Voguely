package com.wit.voguely.ui.main.pdp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.ui.login.LoginEvent
import com.wit.voguely.ui.main.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PDPViewModel: ViewModel() {
    private val productDataSource = ProductDataSource()
    private val addToCartDataSource = AddToCartDataSource()
    private val _product = MutableStateFlow<Products?>(null)
    val product = _product.asStateFlow()
    private val _event = MutableSharedFlow<ItemAdded>()
    val event = _event.asSharedFlow()


    fun oneProductLoad(id:String) {

        viewModelScope.launch {
            _product.update { productDataSource.getProducts(id) }
        }
}
    fun addProduct(id:String) {
        viewModelScope.launch (Dispatchers.IO)  {
            addToCartDataSource.addProduct(id)
            _event.emit(AddedSuccessfully)
        }
    }


}