package com.ciscodeto.domain.item

import com.ciscodeto.domain.attributes.Attribute

data class Item(
    val name: String,
    val description: String,
    val price: Double,
    val attributes: List<Attribute>,
)
