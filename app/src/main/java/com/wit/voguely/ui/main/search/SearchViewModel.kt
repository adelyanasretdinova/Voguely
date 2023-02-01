package com.wit.voguely.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductsDataSource
import com.wit.voguely.model.Products
import com.wit.voguely.ui.main.pdp.AddedSuccessfully
import com.wit.voguely.ui.main.pdp.ItemAdded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val addToCartDataSource = AddToCartDataSource()
    private val productsDataSource = ProductsDataSource()

    private val _searchedItems = MutableStateFlow<List<Products>>(listOf())
    val searchedItems = _searchedItems.asStateFlow()

    private val _noResult = MutableStateFlow(false)
    val noResult = _noResult.asStateFlow()

    private val _event = MutableSharedFlow<ItemAdded>()
    val event = _event.asSharedFlow()


    fun search(s: CharSequence) {
        viewModelScope.launch {
            _searchedItems.update {
                productsDataSource.getProducts().filter { it.name.contains(s, ignoreCase = true) }
            }
            _noResult.update { _searchedItems.value.isEmpty() }
        }
    }

    fun addProduct(id:String) {
        viewModelScope.launch (Dispatchers.IO)  {
            addToCartDataSource.addProduct(id)
            _event.emit(AddedSuccessfully)
        }
    }
}