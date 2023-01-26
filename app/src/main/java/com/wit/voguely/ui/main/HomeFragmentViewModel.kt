package com.wit.voguely.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductsDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeFragmentViewModel : ViewModel() {
    private val productsDataSource = ProductsDataSource()
    private val _productData = MutableStateFlow<List<Products>>(listOf())
    val productData = _productData.asStateFlow()
    private val _liveProgressBar = MutableStateFlow<Boolean>(false)
    val liveProgressBar = _liveProgressBar.asStateFlow()

    init {
        dataLoad()
    }

    private fun dataLoad() {

        viewModelScope.launch {
            _liveProgressBar.update { true }
            _productData.update { productsDataSource.getProducts() }
            _liveProgressBar.update { false }
        }
    }
}