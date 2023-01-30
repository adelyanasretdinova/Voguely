package com.wit.voguely.ui.main.cart

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class CartResponse(
    val productId: String = "",
    val quantity: Int = 0
)