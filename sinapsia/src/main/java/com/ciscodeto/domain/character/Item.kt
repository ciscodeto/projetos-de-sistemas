package com.ciscodeto.domain.character

data class Item(
    val name: String,
    val description: String,
    val price: Double,
    val attributes: Attributes,
)