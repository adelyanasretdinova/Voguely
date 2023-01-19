package com.wit.voguely.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update


class HomeFragmentViewModel : ViewModel() {

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
            _productData.update { listOf(productOne,productTwo, productThree, productFour, productFive, productSix, productSeven, productEight, productNine, productTen) }
            _liveProgressBar.update { false }
        }
    }
}