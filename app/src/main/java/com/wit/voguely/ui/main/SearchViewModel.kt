package com.wit.voguely.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductsDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val productsDataSource = ProductsDataSource()
    private val _searchedItems = MutableStateFlow<List<Products>>(listOf())
    val searchedItems = _searchedItems.asStateFlow()
    private val _noResult = MutableStateFlow<Boolean>(false)
    val noResult = _noResult.asStateFlow()

//    init {
//        dataLoad()
//    }

    fun search(s: CharSequence) {

        viewModelScope.launch {

            _searchedItems.update {
                productsDataSource.getProducts().filter { it.name.contains(s, ignoreCase = true) }
            }
            _noResult.update { _searchedItems.value.isEmpty() }
        }
    }
}