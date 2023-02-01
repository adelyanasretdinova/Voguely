package com.wit.voguely.ui.main.pdp

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class PDPViewModel() : ViewModel() {

    private val productDataSource = ProductDataSource()
    private val addToCartDataSource = AddToCartDataSource()

    private val _product = MutableStateFlow<Products?>(null)
    val product = _product.asStateFlow()

    private val _event = MutableSharedFlow<ItemAdded>()
    val event = _event.asSharedFlow()


    fun oneProductLoad(id: String) {
        viewModelScope.launch {
            _product.update { productDataSource.getProducts(id) }
        }
    }

    fun addProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            product.value?.id?.let {
                addToCartDataSource.addProduct(it)
                _event.emit(AddedSuccessfully)
            }
        }
    }

    fun savePhoto(url: String) {
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        viewModelScope.launch(Dispatchers.IO) {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                        // Do something with the bitmap
                    }
                }
            })
        }

    }
}