package com.ciscodeto.domain.entities

data class Item(
    val name: String,
    val description: String,
    val price: Double,
    val attributes: List<Attribute>,
)
