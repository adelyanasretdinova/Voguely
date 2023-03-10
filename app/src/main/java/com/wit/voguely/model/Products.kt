package com.wit.voguely.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Products(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val currency: String = "",
    val description: String = ""
)