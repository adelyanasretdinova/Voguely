package com.wit.voguely

import com.wit.voguely.model.Products
import com.wit.voguely.remote.ProductsDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataSourceTest {
    @Mock
    private lateinit var productsDataSource: ProductsDataSource

    @Test
    fun isMockCorrect() = runTest {
        val productList = listOf(Products())
    Mockito.`when`(productsDataSource.getProducts()).thenReturn(productList)
        val product = productsDataSource.getProducts()
        Assert.assertTrue(product.isNotEmpty())
    }
}