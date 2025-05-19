package com.ciscodeto.domain.character

import com.ciscodeto.sinapsia.domain.character.Attributes

data class Item(
    val name: String,
    val description: String,
    val price: Int,
    val attributes: Attributes,
)