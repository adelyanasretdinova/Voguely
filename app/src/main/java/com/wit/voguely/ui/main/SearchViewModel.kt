package com.wit.voguely.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchedItems = MutableStateFlow<List<Products>>(listOf())
    val searchedItems = _searchedItems.asStateFlow()
    private val _noResult = MutableStateFlow<Boolean>(false)
    val noResult = _noResult.asStateFlow()


    fun search(s: CharSequence) {

        viewModelScope.launch {

            _searchedItems.update {
                listOf(
                    productOne,
                    productTwo,
                    productThree,
                    productFour,
                    productFive,
                    productSix,
                    productSeven,
                    productEight,
                    productNine,
                    productTen
                ).filter { it.name.contains(s, ignoreCase = true) }
            }
                _noResult.update { _searchedItems.value.isEmpty()}
        }
    }
}